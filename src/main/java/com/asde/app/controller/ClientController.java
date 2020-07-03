package com.asde.app.controller;

import com.asde.app.domain.Client;
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
import java.util.List;

/**
 * Este controlador permitira manejar las peticiones para los clientes,
 * en el se podrá acceder a la lista de todos los clientes registrados,
 * acceder a los detalles de uno en especifico ademas de actualizar y eliminar
 * la información.
 */

@Controller
@RequestMapping("/clientes")
public class ClientController {

    /* ~    PROPERTIES
    --------------------------------------------------- */
    private IClientService clientService;
    private IRepresentantService representantService;



    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public ClientController(IClientService clientService, IRepresentantService representantService){
        this.clientService = clientService;
        this.representantService = representantService;
    } // end constructor



    /* ~    MODELS
    --------------------------------------------------- */

    /**
     * Método que permite extraer por medio de la capa de servicio todos los registros
     * de la tabla <b>empresa</b> de la BD, para poder usarlos dentro de las plantillas
     * de thymeleaf que pertenescan a la ruta /clientes/*.
     * @return List<Client> lista de todos los registros de la table empresa.
     */
    @ModelAttribute(name = "clients")
    public List<Client> clients() { return clientService.getAllClients(); }

    @ModelAttribute(name = "estados")
    public Client.ACTIVE_T[] estados() { return Client.ACTIVE_T.values(); }


    /* ~    CONTROLLERS
    -------------------------------------------------------------------------- */

    /**
     * Método que maneja la petición a la ruta absoluta del controlador (/clientes)
     * mandando al usuario a la lista de todos los clientes registrados.
     * @param model Enviar datos a la plantilla <b>homeClient</b>
     * @return String sera la plantilla <b>homeClient</b> que mostrara al acceder a este método.
     */
    @GetMapping
    public String clientIndex(Model model) {
        model.addAttribute("title", "Clientes");
        model.addAttribute("subtitle", "Mi lista de clientes");

        return "/clients/homeClient";
    } // end clientes/


    /**
     * Método para manejar las peticiones cuando el usuario quiera crear un nuevo cliente,
     * este mostrara el formulario (plantilla) en HTML.
     * @param model Enviar datos a la plantilla <b>formClient</b>
     * @return plantilla <b>formClient</b> en formato HTML.
     */
    @GetMapping("/crear")
    public String formNewClient(Model model) {
        model.addAttribute("title", "Nuevo Cliente");
        model.addAttribute("subtitle", "Registrando nuevo cliente");
        model.addAttribute("client", new Client());

        return "clients/formClient";
    } // end clientes formulario


    /**
     * Método que menejara el envio de datos por medio del formulario <b>formClient</b> (POST) el cual permitira
     * registrar en la BD un nuevo cliente, validando su contenido y realizando el guardado.
     * @param client Objeto enviado desde el formulario.
     * @param errors Objeto creado en caso de algún error en la validación de la entidad.
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @param model Permite enviar datos a la plantilla thymeleaf.
     * @return Redirecciona a la página de representantes para agregar por lo menos a uno.
     */
    @PostMapping("/crear")
    public String saveClient(@Valid Client client, Errors errors, RedirectAttributes msjHeader, Model model) {
        if(errors.hasErrors()) { // redirigir al formulario para solucionar sus errores
            model.addAttribute("title", "Nuevo Cliente");
            model.addAttribute("subtitle", "Corrija los siguientes campos para registrar al cliente");

            return "clients/formClient";
        } // end if to validation and redirect

        client.setActive(Client.ACTIVE_T.ACTIVO); // Set your state to active
        if(clientService.getClientByRfc(client.getRfc()) != null) {
            model.addAttribute("error_duplicate", "El RFC "
                    .concat(client.getRfc())
                    .concat(" ya esta asignado a un cliente, intente con otro"));
            return "clients/formClient";
        }
        clientService.saveClient(client);         // Save client

        // Define message to realize the save
        msjHeader.addFlashAttribute("success", "El cliente " + client.getName() + " se ha " +
                "guardado correctamente");

        return "redirect:/representantes/crear/"+client.getIdEmpresa();
    } // end formulario para guardar


    /**
     * Método que menejara las peticiones cuando un usuario desee ver los detalles de un cliente en especifico.
     * @param rfc String Define la clave del cliente del cual desea ver sus detalles.
     * @param model Enviar datos a la plantilla <b>details</b>
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return plantilla <b>formClient</b> en formato HTML.
     */
    @GetMapping("/detalles/{rfc}")
    public String detailsClient(@PathVariable String rfc, Model model, RedirectAttributes msjHeader) {
        Client client = clientService.getClientByRfc(rfc);

        if(client == null){ // Validate if client exist, if not redirect to list
            msjHeader.addFlashAttribute("error", "No se encuentra el cliente con el RFC "+rfc);
            return "redirect:/clientes";
        } // end if validate

        model.addAttribute("title", "Detalles de "+rfc);
        model.addAttribute("subtitle", client.getName() +" ("+client.getRfc()+")");
        model.addAttribute("client", client);

        return "/clients/details";
    }


    /**
     * Método que menejara las peticiones del cliente cuando requiera actualizar los datos de un registro
     * en especifico.
     * @param rfc String Define la clave del cliente del cual desea actualizar.
     * @param model Enviar datos a la plantilla <b>formUpdate</b>
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return plantilla <b>formUpdate</b> en formato HTML.
     */
    @GetMapping("/editar/{rfc}")
    public String formUpdateClient(@PathVariable String rfc, Model model, RedirectAttributes msjHeader) {
        Client client = clientService.getClientByRfc(rfc);
        if (client == null) {
            msjHeader.addFlashAttribute("error", "No se puede acceder al cliente con el RFC "+
                    rfc+ " intente con uno valido");

            return "redirect:/clientes";
        } // end if validate
        model.addAttribute("title", "Modificar "+client.getName());
        model.addAttribute("subtitle", "Modificado a "+client.getName());
        model.addAttribute("client", client);

        return "clients/formUpdate";
    }


    /**
     * Método (POST) que manejara el envío de datos por medio del formulario de la plantilla <b>formUpdate</b>
     * el cual permitira guardar los cambios realizados al registro.
     * @param client Objeto que sera almacenado en la BD.
     * @param errors Objeto creado en caso de algún error en la validación de la entidad.
     * @param msnHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return Redirecciona a la página de los detalles del cliente.
     */
    @PostMapping("/actualizar")
    public String updateClient(@Valid Client client, Errors errors, RedirectAttributes msnHeader) {
        if (errors.hasErrors()) {
            return "clients/formUpdate";
        }

        client.setRepresentants(clientService.getClientByRfc(client.getRfc()).getRepresentants());
        clientService.saveClient(client);
        msnHeader.addFlashAttribute("success", client.getName()+" se ha actualizado correctamente");

        return "redirect:/clientes/detalles/"+client.getRfc();
    }


    /**
     * Método que meneja la eliminación de un registro en especifico dado su clave.
     * @param idEmpresa Clave para determinar el registro a eliminar de la BD.
     * @param msnHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return Redirecciona a la lista de clientes.
     */
    @GetMapping("/eliminar/{idEmpresa}")
    public String deleteClient(@PathVariable Integer idEmpresa, RedirectAttributes msnHeader) {
        if (clientService.getClientById(idEmpresa) == null){
            msnHeader.addFlashAttribute("error","El cliente no se encuentra en el registro, no se puede eliminar");
        }else{
            clientService.deleteClientById(idEmpresa);
            msnHeader.addFlashAttribute("success","Cliente eliminado correctamente");
        }

        return "redirect:/clientes";
    } // end delete cliente



    /* ~    ERROR HANDLER
     --------------------------------------------------- */

    /**
     * Método que permite interceptar excepciones <b>MethodArgumentTypeMismatchException</b> cuando se pasa por
     * parámetro tipos de datos no soportados por los métodos.
     * @param ex Excepción.
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return Redirecciona a la lista de clientes.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String errorTypeParamHandler(MethodArgumentTypeMismatchException ex, RedirectAttributes msjHeader){
        msjHeader.addFlashAttribute("error", "No se puede acceder al cliente, intentelo con uno valido");

        return "redirect:/clientes";
    }

} // end of controller
