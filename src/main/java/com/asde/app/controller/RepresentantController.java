package com.asde.app.controller;

import com.asde.app.domain.Representant;
import com.asde.app.service.IClientService;
import com.asde.app.service.IRepresentantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/representantes")
public class RepresentantController {

    private IRepresentantService repreService;
    private IClientService clientService;
    @Autowired
    public RepresentantController(IRepresentantService repreService, IClientService clientService){
        this.repreService = repreService;
        this.clientService = clientService;
    }

    @GetMapping("/crear/{idEmpresa}")
    public String asignRepresentantForm(@PathVariable Integer idEmpresa, Model model) {
        Representant representant = new Representant();
        representant.setClient(clientService.getClientById(idEmpresa));
        model.addAttribute("title", "Asignar representante");
        model.addAttribute("subtitle", "Asignar a "+representant.getClient().getName()+" un representante");
        model.addAttribute("representant", representant);

        return "representants/formRepresentant";
    }

    @PostMapping("/crear")
    public String saveRepresentant(@Valid Representant representant, Errors errors, Model model, RedirectAttributes notify) {
        if (errors.hasErrors()) { // existen errores en el formulario
            model.addAttribute("title", "Asigna representante");
            model.addAttribute("subtitle", "Corrija los siguientes campos para asignar al representante");

            return "/representants/formRepresentant";
        }
        repreService.saveRepresentant(representant);
        notify.addFlashAttribute("success", "Se ha asignado a " + representant + " correctamente " +
                "a " + representant.getClient().getName());

        return "redirect:/representantes/crear/"+representant.getClient().getIdEmpresa();
    }



}
