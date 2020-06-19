package com.asde.app.controller;

import com.asde.app.domain.Client;
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
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientController {

    /* ~    AUTOWIRED
    --------------------------------------------------- */
    private IClientService clientService;
    private IRepresentantService representantService;



    /* ~    MODELS
    --------------------------------------------------- */
    @ModelAttribute(name = "clients")
    public List<Client> clients() { return clientService.getAllClients(); }



    /* ~    CONSTRUCTORS
    --------------------------------------------------- */
    @Autowired
    public ClientController(IClientService clientService, IRepresentantService representantService){
        this.clientService = clientService;
        this.representantService = representantService;
    } // end constructor



    /* ~    CONTROLLERS
    --------------------------------------------------- */
    @GetMapping // Ruta absoluta de constrolador
    public String homeClients (Model model) {
        model.addAttribute("title", "Clientes");
        model.addAttribute("subtitle", "Lista de todos mis clientes registrados");

        return "/clients/homeClient";
    } // end clientes/


    @GetMapping("/crear")
    public String createNewClient (Model model) {
        model.addAttribute("title", "Nuevo Cliente");
        model.addAttribute("subtitle", "Agregue a un nuevo cliente");
        model.addAttribute("client", new Client());

        return "clients/formClient";
    } // end clientes formulario


    @PostMapping("/crear")
    public String saveClient (@Valid Client client, Errors errors, RedirectAttributes notify, Model model) {
        if(errors.hasErrors()) { // redirigir al formulario para solucionar sus errores
            model.addAttribute("title", "Nuevo Cliente");
            model.addAttribute("subtitle", "Corrija los siguientes campos para crear al nuevo usuario");

            return "clients/formClient";
        }
        client.setActive(Client.ACTIVE_T.ACTIVO); // Establecer su estado a activo
        clientService.saveClient(client);         // Guardar al cliente
        notify.addFlashAttribute("success", "El cliente " + client.getName() + " se ha " +
                "guardado correctamente");

        return "redirect:/representantes/crear/"+client.getIdEmpresa();
    } // end formulario para guardar

    @GetMapping("/detalles/{rfc}")
    public String showDetailsClient (@PathVariable String rfc, Model model, RedirectAttributes notify) {
        Client client = clientService.getClientByRfc(rfc);

        if(client == null){
            notify.addFlashAttribute("error", "No se encuentra el cliente con el RFC "+rfc);
            return "redirect:/clientes";
        }
        model.addAttribute("title", "Detalles de "+rfc);
        model.addAttribute("subtitle", "Detalles de "+client.getName());
        model.addAttribute("client", client);

        return "/clients/details";
    }

    @GetMapping("/editar/{rfc}")
    public String modifyClient (@PathVariable String rfc, Model model, RedirectAttributes notify) {
        Client client = clientService.getClientByRfc(rfc);
        if (client == null) {
            notify.addFlashAttribute("error", "No se puede acceder al cliente con el RFC "+
                    rfc+ " intente con uno valido");

            return "redirect:/clientes";
        }
        model.addAttribute("title", "Modificar "+client.getName());
        model.addAttribute("subtitle", "Modificado a "+client.getName());
        model.addAttribute("client", client);

        return "clients/formUpdate";
    }

    @PostMapping("/actualizar")
    public String updateClient (@Valid Client client, Errors errors, RedirectAttributes notify) {
        if (errors.hasErrors()) {
            return "clients/formUpdate";
        }
        clientService.saveClient(client);
        notify.addFlashAttribute("success", client.getName()+" se ha actualizado correctamente");

        return "redirect:/clientes/editar/"+client.getRfc();
    }


    @GetMapping("/eliminar/{idEmpresa}")
    public String deleteClient (@PathVariable Integer idEmpresa, RedirectAttributes notify) {
        if (clientService.getClientById(idEmpresa) == null){
            notify.addFlashAttribute("error","El cliente no se encuentra en el registro, no se puede eliminar");
        }else{
            clientService.deleteClientById(idEmpresa);
            notify.addFlashAttribute("success","Cliente eliminado correctamente");
        }

        return "redirect:/clientes";
    } // end delete cliente



    /* ~    ERROR HANDLER
     --------------------------------------------------- */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String errorParamForDeleteProcedure(MethodArgumentTypeMismatchException ex, Model model, RedirectAttributes notify){
        notify.addFlashAttribute("error", "No se puede acceder al cliente, intentelo con uno valido");

        return "redirect:/clientes";
    }

} // end of controller
