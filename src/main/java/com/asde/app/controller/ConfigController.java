package com.asde.app.controller;

import com.asde.app.service.IAsdeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/configuracion")
public class ConfigController {
    private IAsdeService asdeService;

    @Autowired
    public ConfigController(IAsdeService asdeService) {
        this.asdeService = asdeService;
    }

    @GetMapping
    public String homeConfig(Model model) {
        model.addAttribute("title", "Configuración");
        return "/config/homeConfig";
    }

    @GetMapping("/perfil")
    public String perfil(Model model){
        model.addAttribute("asde", asdeService.findAsde().get(0));

        return "/config/perfil";
    }

} // end controller
