package com.asde.app.controllerException;


import com.asde.app.domain.*;
import com.asde.app.exceptions.ProcedureNameDuplicateException;
import com.asde.app.service.dependence.IDependenceService;
import com.asde.app.service.procedure.IProcedureService;
import com.asde.app.service.requirement.IRequirementService;
import com.asde.app.service.signature.ISignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ControllerAdvice
public class ErrorHandlerProcedures {
    private IProcedureService procedureRepo;
    private IDependenceService dependenceRepo;
    private IRequirementService requirementRepo;
    private ISignatureService signatureRepo;


    @Autowired
    public ErrorHandlerProcedures(IProcedureService procedureRepo, IDependenceService dependenceRepo,
                                  IRequirementService requirementRepo, ISignatureService signatureRepo) {
        this.procedureRepo = procedureRepo;
        this.dependenceRepo = dependenceRepo;
        this.requirementRepo = requirementRepo;
        this.signatureRepo = signatureRepo;
    }


    @ExceptionHandler(ProcedureNameDuplicateException.class)
    public String exceptionDuplicateNameProcedure (ProcedureNameDuplicateException ex, Model model) {
        model.addAttribute("name_duplicate", "El nombre del trámite ya existe");
        model.addAttribute("procedure", new Procedure());
        model.addAttribute("dependencies", dependenceRepo.getAllDependecies());
        model.addAttribute("requirements", requirementRepo.getAllRequirements());
        model.addAttribute("signatures", signatureRepo.getAllSignatures());
        model.addAttribute("quantities", IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList()));
        model.addAttribute("title", "Crear un nuevo trámite");

        return "procedures/formProcedure";
    }

}
