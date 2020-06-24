package com.asde.app.controller;

import com.asde.app.domain.*;
import com.asde.app.exceptions.ProcedureNameDuplicateException;
import com.asde.app.service.dependence.IDependenceService;
import com.asde.app.service.procedure.IProcedureService;
import com.asde.app.service.requirement.IRequirementService;
import com.asde.app.service.signature.ISignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Este controlador permitirá manejar todas la peticiones entrantes a la ruta<b>/tramites/*</b> que gestiona los
 * trámites realizados por el usuario, en el se podrá acceder a la lista de todos los que ha registrado en la BD,
 * ademas de acceder a los detalles de uno en especifico, de actualizar y eliminar la información.
 */
@Controller
@RequestMapping("/tramites")
public class ProcedureController {

    /* ~    PROPERTIES
    --------------------------------------------------- */
    private IProcedureService procedureRepo;
    private IDependenceService dependenceRepo;
    private IRequirementService requirementRepo;
    private ISignatureService signatureRepo;



    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public ProcedureController (IProcedureService procedureRepo, IDependenceService dependenceRepo,
                               IRequirementService requirementRepo, ISignatureService signatureRepo) {
        this.procedureRepo = procedureRepo;
        this.dependenceRepo = dependenceRepo;
        this.requirementRepo = requirementRepo;
        this.signatureRepo = signatureRepo;
    }



    /* ~    MODELS
    --------------------------------------------------- */

    /**
     * Método que permite extraer por medio de la capa de servicio todos los registros
     * de la tabla <b>tramites</b> de la BD, para poder usarlos dentro de las plantillas
     * de thymeleaf que pertenescan a la ruta /tramites/*.
     * @return Lista de objetos Procedure.
     */
    @ModelAttribute(name = "procedures")
    public List<Procedure> procedures() { return procedureRepo.getAllProcedures(); }

    /**
     * Método que permite extraer por medio de la capa de servicio todos los registros
     * de la tabla <b>tramites</b> de la BD, para poder usarlos dentro de las plantillas
     * de thymeleaf que pertenescan a la ruta /tramites/*.
     * @return Lista de objetos Dependence.
     */
    @ModelAttribute(name = "dependencies")
    public List<Dependence> dependence () { return dependenceRepo.getAllDependecies(); }

    /**
     * Método que permite extraer por medio de la capa de servicio todos los registros
     * de la tabla <b>tramites</b> de la BD, para poder usarlos dentro de las plantillas
     * de thymeleaf que pertenescan a la ruta /tramites/*.
     * @return Lista de objetos Requirement.
     */
    @ModelAttribute(name = "requirements")
    public List<Requirement> requirements () { return requirementRepo.getAllRequirements(); }

    /**
     * Método que permite extraer por medio de la capa de servicio todos los registros
     * de la tabla <b>tramites</b> de la BD, para poder usarlos dentro de las plantillas
     * de thymeleaf que pertenescan a la ruta /tramites/*.
     * @return Lista de objetos Signature.
     */
    @ModelAttribute(name = "signatures")
    public List<Signature> signatures () { return signatureRepo.getAllSignatures(); }

    /**
     * Genera una lista de enteros del rango de 1 a 9, para poder usarlos dentro de las plantillas
     * de thymeleaf que pertenescan a la ruta /tramites/*.
     * @return Lista de objetos Integer.
     */
    @ModelAttribute(name = "quantities")
    public List<Integer> quantities () { return IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList()); }




    /* ~    CONTROLLERS
    -------------------------------------------------------------------------- */

    /**
     * Método que manejara las peticiones de la ruta absoluta del controlador mostrando al usuario la lista de todos
     * los trámites registrados en la BD.
     * @param model Enviar datos a la plantilla <b>homeProcedure</b>
     * @return plantilla <b>homeProcedure</b> en formato HTML.
     */
    @GetMapping
    public String procedureIndex(Model model) {
        model.addAttribute("title", "Trámites");

        return "procedures/homeProcedure";
    }


    /**
     * Método que manejará las peticiones del usuario cuando necesite crear un nuevo trámite, enviandolo a un
     * formulario para poder hacerlo.
     * @param model Enviar datos a la plantilla <b>formProcedure</b>
     * @return plantilla <b>formProcedure</b> en formato HTML.
     */
    @GetMapping("/crear")
    public String viewFormCreateProcedure(Model model){
        model.addAttribute("procedure", new Procedure());
        model.addAttribute("title", "Nuevo trámite");
        model.addAttribute("subtitle", "Crea un nuevo trámite");
        model.addAttribute("types", Procedure.TYPE_P.values());

        return "procedures/formProcedure";
    }


    /**
     * Método que menejara el envio de datos por medio del formulario <b>formProcedure</b> (POST) el cual permitira
     * registrar en la BD un nuevo trámite, validando su contenido y realizando el guardado.
     * @param procedure Objeto Procedure enviado desde el formulario para su almacenamiento.
     * @param error Objeto creado en caso de algún error en la validación de la entidad.
     * @param model Enviar datos a la plantilla <b>formProcedure</b> en caso de algún error.
     * @return Redirecciona a la lista de trámites.
     */
    @PostMapping("/crear")
    public String saveProcedure (@Valid Procedure procedure, Errors error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("title", "Nuevo trámite");
            model.addAttribute("subtitle", "Corrige los errores para guardaar tu trámite");
            return "procedures/formProcedure";
        }

        procedure.setExpiration( formatExpiration(procedure.getExpiration()) );

        // Validation
        if(procedure.getIdProcedure() != null){ // valid procedure to modify (procedure exist)
            procedure = clearSignaturesIfIsNull(procedure);
        }else{ // valid the name of new procedure do not exist
            validIfNameProcedureExist(procedure.getName());
        }

        procedureRepo.saveProcedure(procedure);

        return "redirect:/tramites";
    }


    /**
     * Método que permite mostrar los detalles de un trámite en especifico dado su clave única como parámetro.
     * @param procedureName String nombre del trámite a ver sus detalles.
     * @param model Enviar datos a la plantilla <b>formProcedure</b>
     * @return plantilla <b>formProcedure</b> en formato HTML.
     */
    @GetMapping("/actualizar/{procedureName}")
    public String viewFormUpdateProcedure(@PathVariable String procedureName, Model model) {
        Procedure procedure = procedureRepo.findProcedureByName(procedureName);
        model.addAttribute("procedure", procedure);
        model.addAttribute("title", "Modificar el trámite "+procedure.getName());

        return "procedures/formProcedure";
    }


    /**
     * Método que permite eliminar un registro (trámite) en especifico de la BD, dado su clave única como parámetro.
     * @param idProcedure Integer clave primaria.
     * @return String Redirecciona a la lista de trámites.
     */
    @GetMapping("/eliminar/{idProcedure}")
    public String deleteProcedure (@PathVariable Integer idProcedure) {
        Procedure procedure = procedureRepo.findProcedureById(idProcedure);
        if (procedure != null)
            procedureRepo.deleteProcedureById(idProcedure);
        return "redirect:/tramites";
    }


    /**
     * Método que manejara las peticiones para que el usuario pueda acceder a los detalles de un trámite en
     * especifico dando como parámetro la clave única.
     * @param idProcedure Integer clave primaria.
     * @param model Enviar datos a la plantilla <b>profileProcedure</b>
     * @return plantilla <b>profileProcedure</b> en formato HTML.
     */
    @GetMapping("/requisitos/{idProcedure}")
    public String viewProcedureProfile(@PathVariable Integer idProcedure, Model model) {
        Procedure procedure = procedureRepo.findProcedureById(idProcedure);
        model.addAttribute("title","Requisitos de trámite");
        model.addAttribute("subtitle","Estos son los requisitos para " + procedure.getName());
        model.addAttribute("procedure", procedure);

        return "procedures/profileProcedure";
    }



    /* ~    UTILITIES
    --------------------------------------------------- */

    /**
     * Método que permite extraer la duración (meses, años o permanente) de la fecha de vigencia de un trámite,
     * para poder almacenar en el campo 1[meses|años] o permanente, dando un formato aceptable.
     * @param date String valor obtenido del formulario (input).
     * @return String la fecha formateada.
     */
    private String formatExpiration(String date) {
        return date.replace(",",
                (date.equals("permanente,,") ? "" : " "));
    }


    /**
     * Método que elimina los valores almacenados en la lista de firmas de un trámite, ya que un trámite normal no
     * conlleva firmas, este método solo se ejecuta cuando el usuario actualiza algún trámite.
     * @param procedure Procedure trámite del cual se limpiaran las firmas.
     * @return Procedure con la lista de firmas vacía.
     */
    private Procedure clearSignaturesIfIsNull(Procedure procedure) {
        if(procedure.getType().name().equals("NORMAL"))
            procedure.setSignatures(null);

        return procedure;
    }


    /**
     * Método que permite validar la existencia sobre el nombre de un trámite que se intente guardar en la BD,
     * en el cual lanzara una excepción personalizada notificando al usuario que deberá cambiar el nombre.
     * @param nameProcedure String nombre del trámite a validar en la BD.
     */
    private void validIfNameProcedureExist (String nameProcedure) {
        Procedure nameDb = procedureRepo.findProcedureByName(nameProcedure.toUpperCase());
        if (nameDb != null && nameProcedure.toUpperCase().equals(nameDb.getName())) {
            throw new ProcedureNameDuplicateException(nameProcedure);
        }
    }


    /* ~    ERROR HANDLER
    --------------------------------------------------- */

    /**
     * Método que permite interceptar excepciones <b>MethodArgumentTypeMismatchException</b> cuando se pasa por
     * parámetro tipos de datos no soportados por los métodos.
     * @param ex Excepción lanzada.
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return String Redirecciona a la lista de trámites.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String errorParamForDeleteProcedure(MethodArgumentTypeMismatchException ex,RedirectAttributes msjHeader){
        msjHeader.addFlashAttribute("error", "No se puede eliminar el tramite, intentelo con uno valido");

        return "redirect:/tramites";
    }


} // end of controller
