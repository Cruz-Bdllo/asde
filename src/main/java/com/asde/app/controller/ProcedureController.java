package com.asde.app.controller;

import com.asde.app.domain.*;
import com.asde.app.exceptions.ProcedureNameDuplicateException;
import com.asde.app.service.IDependenceService;
import com.asde.app.service.IProcedureService;
import com.asde.app.service.IRequirementService;
import com.asde.app.service.ISignatureService;
import org.dom4j.rule.Mode;
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


@Controller
@RequestMapping("/tramites")
public class ProcedureController {

    /* ~    PROPERTIES
    --------------------------------------------------- */
    private IProcedureService procedureRepo;
    private IDependenceService dependenceRepo;
    private IRequirementService requirementRepo;
    private ISignatureService signatureRepo;



    /* ~    AUTOWIRED WITH CONSTRUCTOR
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
//    @ModelAttribute(name = "procedures")
//    public List<Procedure> procedures () {
//        List<Procedure> pros = procedureRepo.getAllProcedures();
//        return pros;
//    }
    @ModelAttribute(name = "procedures")
    public List<Procedure> procedures() { return procedureRepo.getAllProcedures(); }
    @ModelAttribute(name = "dependencies")
    public List<Dependence> dependence () { return dependenceRepo.getAllDependecies(); }

    @ModelAttribute(name = "requirements")
    public List<Requirement> requirements () { return requirementRepo.getAllRequirements(); }

    @ModelAttribute(name = "signatures")
    public List<Signature> signatures () { return signatureRepo.getAllSignatures(); }

    @ModelAttribute(name = "quantities")
    public List<Integer> quantities () { return IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList()); }




    /* ~    CONTROLLERS
    --------------------------------------------------- */
    @GetMapping
    public String homeProcedures (Model model) {
        model.addAttribute("title", "Trámites");

        return "/procedures/homeProcedure";
    }

    @GetMapping("/crear")
    public String createProcedures(Model model){
        model.addAttribute("procedure", new Procedure());
        model.addAttribute("title", "Nuevo trámite");
        model.addAttribute("subtitle", "Crea un nuevo trámite");
        model.addAttribute("types", Procedure.TYPE_P.values());

        return "procedures/formProcedure";
    }

    // also can update
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

    @GetMapping("/actualizar/{name_procedure}")
    public String updateProcedure (@PathVariable String name_procedure, Model model) {
        Procedure procedure = procedureRepo.findProcedureByName(name_procedure);
        model.addAttribute("procedure", procedure);
        model.addAttribute("title", "Modificar el trámite "+procedure.getName());

        return "procedures/formProcedure";
    }


    @GetMapping("/eliminar/{idProcedure}")
    public String deleteProcedure (@PathVariable Integer idProcedure) {
        Procedure procedure = procedureRepo.findProcedureById(idProcedure);
        if (procedure != null)
            procedureRepo.deleteProcedureById(idProcedure);
        return "redirect:/tramites";
    }


    @GetMapping("/requisitos/{idProcedure}")
    public String profileProcedure (@PathVariable Integer idProcedure, Model model) {
        Procedure procedure = procedureRepo.findProcedureById(idProcedure);
        model.addAttribute("title","Requisitos de trámite");
        model.addAttribute("subtitle","Estos son los requisitos para " + procedure.getName());
        model.addAttribute("procedure", procedure);

        return "procedures/profileProcedure";
    }



    /* ~    UTILITIES
    --------------------------------------------------- */
    private String formatExpiration(String date) {
        return date.replace(",",
                (date.equals("permanente,,") ? "" : " "));

    }

    private Procedure clearSignaturesIfIsNull(Procedure procedure) {
        if(procedure.getType().name().equals("NORMAL"))
            procedure.setSignatures(null);

        return procedure;

    }

    private void validIfNameProcedureExist (String nameProcedure) {
        Procedure nameDb = procedureRepo.findProcedureByName(nameProcedure.toUpperCase());
        if (nameDb != null && nameProcedure.toUpperCase().equals(nameDb.getName())) {
            throw new ProcedureNameDuplicateException(nameProcedure);
        }
    }


    /* ~    ERROR HANDLER
    --------------------------------------------------- */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String errorParamForDeleteProcedure(MethodArgumentTypeMismatchException ex, Model model, RedirectAttributes notify){
        notify.addFlashAttribute("error", "No se puede eliminar el tramite, intentelo con uno valido");

        return "redirect:/tramites";
    }


} // end of controller
