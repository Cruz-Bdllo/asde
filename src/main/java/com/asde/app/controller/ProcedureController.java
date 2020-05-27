package com.asde.app.controller;

import com.asde.app.domain.Dependence;
import com.asde.app.domain.Procedure;
import com.asde.app.domain.Requirement;
import com.asde.app.domain.Signature;
import com.asde.app.service.IDependenceService;
import com.asde.app.service.IProcedureService;
import com.asde.app.service.IRequirementService;
import com.asde.app.service.ISignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

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
    @ModelAttribute(name = "procedures")
    public List<Procedure> procedures () {return procedureRepo.getAllProcedures(); }

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
        return "/procedures/homeProcedure";
    }

    @GetMapping("/crear")
    public String createProcedures(Model model){
        model.addAttribute("procedure", new Procedure());
        model.addAttribute("title", "Crear un nuevo trámite");

        return "procedures/formProcedure";
    }

    // also can update
    @PostMapping("/crear")
    public String saveProcedure (@Valid Procedure procedure, BindingResult s) {
        if (s.hasErrors()) {

            return "procedures/formProcedure";
        }
        procedure.setExpiration( formatExpiration(procedure.getExpiration()) );

        // if exists alrady procedure
        if(procedure.getIdProcedure() != null){
            procedure = clearProcedureUpdate(procedure);
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



    /* ~    UTILITIES
    --------------------------------------------------- */
    private String formatExpiration(String date) {
        return date.replace(",",
                (date.equals("permanente,,") ? "" : " "));

    }

    private Procedure clearProcedureUpdate (Procedure procedure) {
        if(procedure.getType().name().equals("NORMAL"))
            procedure.setSignatures(null);

        return procedure;

    }


} // end of controller
