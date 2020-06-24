package com.asde.app.controller;

import com.asde.app.domain.Representant;
import com.asde.app.service.client.IClientService;
import com.asde.app.service.representant.IRepresentantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Este controlador permitirá manejar todas la peticiones entrantes a la ruta<b>/representantes/*</b> que gestiona a los
 * representantes de los clientes realizados por el usuario, en el se podrá acceder a la lista de todos los que ha
 * registrado en la BD, ademas de acceder a los detalles de uno en especifico, de actualizar y eliminar la información.
 */

@Controller
@RequestMapping("/representantes")
public class RepresentantController {

    /* ~    PROPERTIES
    --------------------------------------------------- */
    private IRepresentantService repreService;
    private IClientService clientService;


    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public RepresentantController(IRepresentantService repreService, IClientService clientService){
        this.repreService = repreService;
        this.clientService = clientService;
    }



    /* ~    CONTROLLERS
    -------------------------------------------------------------------------- */

    /**
     * Método que manejará las peticiones del usuario cuando necesite asignar a un nuevo representante legal a un
     * cliente justo despues de crear al cliente, enviandolo a un formulario para poder hacerlo.
     * @param idEmpresa Clave única del cliente a quien asignar al representante.
     * @param model Enviar datos a la plantilla <b>formRepresentant</b>
     * @return plantilla <b>formRepresentant</b> en formato HTML.
     */
    @GetMapping("/crear/{idEmpresa}")
    public String viewFormAsignNewRepresentant(@PathVariable Integer idEmpresa, Model model) {
        Representant representant = new Representant();
        representant.setClient(clientService.getClientById(idEmpresa));
        model.addAttribute("title", "Asignar representante");
        model.addAttribute("subtitle", "Asignar a "+representant.getClient().getName()+" un representante");
        model.addAttribute("representant", representant);

        return "representants/formRepresentant";
    }


    /**
     * Método que menejara el envio de datos por medio del formulario <b>formRepresentant</b> (POST) el cual permitirá
     * asignar y registrar en la BD un nuevo representante, validando su contenido y realizando el guardado.
     * @param representant
     * @param errors Objeto creado en caso de algún error en la validación de la entidad.
     * @param model Enviar datos a la plantilla <b>formRepresentant</b> en caso de algún error.
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return Redirecciona a la misma página para asignar a otro representante al mismo cliente.
     */
    @PostMapping("/crear")
    public String saveRepresentant(@Valid Representant representant, Errors errors,
                                   Model model, RedirectAttributes msjHeader) {
        if (errors.hasErrors()) { // existen errores en el formulario
            model.addAttribute("title", "Asigna representante");
            model.addAttribute("subtitle", "Corrija los siguientes campos para asignar al representante");

            return "/representants/formRepresentant";
        }
        repreService.saveRepresentant(representant);
        msjHeader.addFlashAttribute("success", "Se ha asignado a " + representant + " correctamente " +
                "a " + representant.getClient().getName());

        return "redirect:/representantes/crear/"+representant.getClient().getIdEmpresa();
    }


    /**
     * Método que permite eliminar un registro (representante) en especifico de la BD, dado su clave única como
     * parámetro.
     * @param idRepresentant Clave única del registro.
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return Redirecciona a la página para editar al cliente y eliminar a más representantes.
     */
    @GetMapping("/eliminar/{idRepresentant}")
    public String deleteRepresentant (@PathVariable Integer idRepresentant, RedirectAttributes msjHeader) {
        Representant representant = repreService.getRepresentantById(idRepresentant);
        if (representant == null) {
            msjHeader.addFlashAttribute("error", "El representante no existe, pruebe con uno valido");
            return "redirect:/clientes";
        }
        msjHeader.addFlashAttribute("success", representant
                + " se ha eliminado como representante de "+representant.getClient().getName());
        repreService.deleteRepresentantById(idRepresentant);

        return "redirect:/clientes/editar/"+representant.getClient().getRfc();
    }



    /* ~    ERROR HANDLER
     --------------------------------------------------- */

    /**
     * Método que permite interceptar excepciones <b>MethodArgumentTypeMismatchException</b> cuando se pasa por
     * parámetro tipos de datos no soportados por los métodos.
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return Redirecciona a la lista de clientes.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String errorParamDeleteRepresentant(RedirectAttributes msjHeader) {
        msjHeader.addFlashAttribute("error", "No se ha podido eliminar al representante" +
                " intente con uno valido");

        return "redirect:/clientes";
    }


}
