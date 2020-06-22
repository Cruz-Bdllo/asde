package com.asde.app.controller;

import com.asde.app.domain.Alcaldia;
import com.asde.app.domain.Client;
import com.asde.app.domain.Income;
import com.asde.app.domain.Procedure;
import com.asde.app.service.IAlcaldiaService;
import com.asde.app.service.IClientService;
import com.asde.app.service.IIncomeService;
import com.asde.app.service.IProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/ingresos")
public class IncomeController {
    private IAlcaldiaService alcaldiaService;
    private IClientService clientService;
    private IProcedureService procedureService;
    private IIncomeService incomeService;

    @Autowired
    public IncomeController(IAlcaldiaService alcaldiaService, IClientService clientService, IProcedureService procedureService, IIncomeService incomeService) {
        this.alcaldiaService = alcaldiaService;
        this.clientService = clientService;
        this.procedureService = procedureService;
        this.incomeService = incomeService;
    }


    @ModelAttribute(name = "alcaldias")
    public List<Alcaldia> alcaldias() { return alcaldiaService.getAllAlcaldias(); }
    @ModelAttribute(name = "incomes")
    public List<Income> incomes(){ return incomeService.getAllIncomes(); }
    @ModelAttribute(name = "clients")
    public List<Client> clients(){ return clientService.getAllClients(); }
    @ModelAttribute(name = "procedures")
    public List<Procedure> procedures(){ return procedureService.getAllProcedures(); }


    @GetMapping
    public String homeIncomes(Model model) {
        model.addAttribute("title", "Ingresos");
        model.addAttribute("subtitle", "Lista de ingresos");
        model.addAttribute("formatDate", new SimpleDateFormat("EEEE dd MMMMM, YYYY",
                new Locale("es", "ES")));

        return "/incomes/homeIncome";
    }

    @GetMapping("/crear")
    public String formNewIncome (Model model) {
        model.addAttribute("title", "Nuevo ingreso");
        model.addAttribute("subtitle", "Registra un ingreso que hayas hecho");
        model.addAttribute("income", new Income());

        return "/incomes/formIncome";
    }

    @PostMapping("/guardar")
    public String saveIncome(@Valid Income income, Errors errors, Model model, RedirectAttributes notify) {
        if ( errors.hasErrors() ) {
            model.addAttribute("title", "Nuevo ingreso");
            model.addAttribute("subtitle", "Corrija los siguientes campos para guardar el ingreso");

            return "/incomes/formIncome";
        } // end if error
        // set status
        income.setStatus(Income.TYPE_STATUS.INGRESADO);
        income.setDelivered(Income.TYPE_DELIVERED.NO_ENTREGADO);

        // Save
        incomeService.saveIncome(income);
        notify.addFlashAttribute("success", "El trámite "+income.getProcedure().getName()+
                " se ha guardado correctamente para "+income.getClient().getName());


        return "redirect:/ingresos";
    }


    @GetMapping("/eliminar/{codIncome}")
    public String deleteIncome(@PathVariable String codIncome, RedirectAttributes notify) {
        Income income = incomeService.getIncomeByCodIncome(codIncome);
        if (income == null ) {
            notify.addFlashAttribute("error", "El folio "+codIncome+" no existe, intentelo con uno valido");

        }else{
            incomeService.deleteIncomeById(income.getIdIncome());
            notify.addFlashAttribute("success", "El acuse con el folio "+codIncome+" ha sido eliminado correctamente");
        }

        return "redirect:/ingresos";
    }

    @GetMapping("/cambiar-estado/{codIncome}")
    public String changeStatus(@PathVariable String codIncome, Model model, RedirectAttributes notify) {
        Income income = incomeService.getIncomeByCodIncome(codIncome);
        if (income == null) {
            notify.addFlashAttribute("error", "El acuse con el folio "+codIncome+
                    " no existe, intente con uno valido");
            return "redirect:/ingresos";
        }
        model.addAttribute("income", income);
        model.addAttribute("title", "Cambiar estado");
        model.addAttribute("statues", Income.TYPE_STATUS.values());
        model.addAttribute("subtitle", "Cambiar estado del ingreso con el folio "+income.getCodIncome());

        return "/incomes/changeStatusForm";
    }


    @PostMapping("/guardar-estado")
    public String saveChangeStatus (@Valid Income income, Errors errors, Model model, RedirectAttributes notify) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Nuevo ingreso");
            model.addAttribute("subtitle", "Llene los campos obligatorios");

            return "/incomes/formIncome";
        }

        Income incomeDB = incomeService.getIncomeByCodIncome(income.getCodIncome());
        incomeDB.setStatus(income.getStatus());
        incomeDB = changeStatus(incomeDB);
//        incomeService.saveIncome(incomeDB);
        notify.addFlashAttribute("success", "Se ha guardado correctamente el nuevo ingreso");

        return "redirect:/ingresos";
    }


    private Income changeStatus(Income incomeStatus) {
        if(incomeStatus.getStatus() == Income.TYPE_STATUS.COMPLETADO) {
            incomeStatus.setExpirate(true);

            // cambiar las fechas
            // Obtener la fecha de vigencia
            Procedure procedure = incomeStatus.getProcedure();

            // Crear fecha de vigencia
            Calendar calendarExpiration = Calendar.getInstance();
            calendarExpiration.setTime(incomeStatus.getEndDate()); // tiene la fecha de ingreso

            if (procedure.getExpiration().contains("años")) {
                int anios = Integer.parseInt(procedure.getExpiration().substring(0,1));
                calendarExpiration.add(Calendar.YEAR, anios);
            }else if (procedure.getExpiration().contains("meses")){
                int meses = Integer.parseInt(procedure.getExpiration().substring(0,1));
                calendarExpiration.add(Calendar.MONTH, meses);
            }

            incomeStatus.setExpirateDate(calendarExpiration.getTime());
        } else if (incomeStatus.getStatus() == Income.TYPE_STATUS.COMPLETADO) {
            incomeStatus.setExpirateDate(null);
        }

        return incomeStatus;
    }



} // end of controller to incomes
