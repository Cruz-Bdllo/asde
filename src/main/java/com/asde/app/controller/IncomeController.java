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
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    @ModelAttribute(name = "statues")
    public Income.TYPE_STATUS[] statues(){ return Income.TYPE_STATUS.values(); }
    @ModelAttribute(name = "deliveries")
    public Income.TYPE_DELIVERED[] deliveries(){ return Income.TYPE_DELIVERED.values(); }


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
            model.addAttribute("title", (income.getIdIncome() != null) ? "Actualizar ingreso"
                    : "Nuevo ingreso");
            model.addAttribute("subtitle", "Corrija los siguiente campos para guardar su ingreso");

            return (income.getIdIncome() != null) ? "/incomes/updateIncome" : "/incomes/formIncome";
        }

        if ( income.getIdIncome() != null ) { // es una actualización
            Income incomeDB = incomeService.getIncomeById(income.getIdIncome());
            income.setExpirateDate((incomeDB.getExpirateDate() != null)?incomeDB.getExpirateDate():null);
//            income.setDelivered((income.getStatus() == Income.TYPE_STATUS.INGRESADO)
//                    ? Income.TYPE_DELIVERED.NO_ENTREGADO : incomeDB.getDelivered());
            income = calcDateExpiration(income);

        }else { // nuevo ingreso
            Income incomeDb = incomeService.getIncomeByCodIncome(income.getCodIncome());
            if( incomeDb != null ){
                model.addAttribute("error_duplicate", "El folio ya existe");

                return "/incomes/formIncome";
            }
            income.setStatus(Income.TYPE_STATUS.INGRESADO);
            income.setDelivered(Income.TYPE_DELIVERED.NO_ENTREGADO);
        }
        // Save
        incomeService.saveIncome(income);
        notify.addFlashAttribute("success", "El trámite "+income.getProcedure().getName()+
                " se ha guardado correctamente para "+income.getClient().getName());

        return "redirect:/ingresos";
    }


    @GetMapping("/eliminar/{codIncome}")
    public String deleteIncome(@PathVariable String codIncome, RedirectAttributes notify) {
        Income income = incomeService.getIncomeByCodIncome(codIncome.replace('_','/'));
        if (income == null ) {
            notify.addFlashAttribute("error", "El folio "+codIncome.replace('_','/')+" no existe, intentelo con uno valido");

        }else{
            incomeService.deleteIncomeById(income.getIdIncome());
            notify.addFlashAttribute("success", "El acuse con el folio "+codIncome.replace('_','/')+" ha sido eliminado correctamente");
        }

        return "redirect:/ingresos";
    }

    @GetMapping("/cambiar-estado/{codIncome}")
    public String calcDateExpiration(@PathVariable String codIncome, Model model, RedirectAttributes notify) {
        Income income = incomeService.getIncomeByCodIncome(codIncome.replace('_','/'));
        if (income == null) {
            notify.addFlashAttribute("error", "El acuse con el folio "+codIncome.replace('_','/')+
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
    public String saveChangeStatus (Income income, Errors errors, Model model, RedirectAttributes notify) {

        Income incomeDB = incomeService.getIncomeByCodIncome(income.getCodIncome());
        incomeDB.setStatus(income.getStatus());
        incomeDB = calcDateExpiration(incomeDB);
        incomeService.saveIncome(incomeDB);
        notify.addFlashAttribute("success", "Se ha cambiado el estado de " +
                income.getCodIncome()+" ha "+income.getStatus().name());

        return "redirect:/ingresos";
    }

    @GetMapping("detalles/{codIncome}")
    public String detailsIncome ( @PathVariable String codIncome, Model model, RedirectAttributes notify ) {
        Income income = incomeService.getIncomeByCodIncome(codIncome.replace('_','/'));
        if ( income == null ) {
            notify.addFlashAttribute("error", "El ingreso con folio " + codIncome.replace('_','/') + " no existe, " +
                    "intente con uno válido.");
            return "redirect:/ingresos";
        }
        model.addAttribute("title", "Detalles del ingreso " + codIncome.replace('_','/'));
        model.addAttribute("subtitle", "Detalles del ingreso "+income.getProcedure().getName()+ " ("+codIncome.replace('_','/')+")");
        model.addAttribute("income", income);
        model.addAttribute("formatDate", new SimpleDateFormat("EEEE dd, MMMMM YYYY",
                new Locale("es", "ES")));

        return "/incomes/detailsIncome";
    } // end details to income

    @GetMapping("/actualizar/{codIncome}")
    public String updateIncome ( @PathVariable String codIncome, Model model, RedirectAttributes notify ) {
        Income income = incomeService.getIncomeByCodIncome(codIncome.replace('_','/'));
        if ( income == null ) {
            notify.addFlashAttribute("error", "El ingreso con folio " + codIncome.replace('_','/') +" no existe" +
                    ", intente con uno válido");
            return "redirect:/ingresos";
        }
        model.addAttribute("title", "Actualizar ingreso");
        model.addAttribute("subtitle", "Modificar ingreso con el folio " + income.getCodIncome());
        model.addAttribute("income", income);

        return "/incomes/updateIncome";
    }



    // --- Factorizar ---
    private Income calcDateExpiration(Income incomeForm) {
        if(incomeForm.getStatus() == Income.TYPE_STATUS.COMPLETADO) {
            // Empieza la vigencia del documento
            incomeForm.setExpirate(true);

            // cambiar las fechas
            // Obtener la fecha de vigencia del documento ingresado
            Procedure procedure = incomeForm.getProcedure();

            // Crear calendario para asignar la fecha de vigencia para el documento
            Calendar calendar = defineTypeDuration(procedure, incomeForm);

            // Establecemos la fecha de vigencia
            incomeForm.setExpirateDate((calendar != null )?calendar.getTime():null);
        } else if (incomeForm.getStatus() == Income.TYPE_STATUS.PREVENCION) {
            incomeForm.setExpirateDate(null);
        }

        return incomeForm;
    }

    private Calendar defineTypeDuration( Procedure proceFunction, Income incomeFunction ) {
        Calendar calendarExpiration = Calendar.getInstance();
        calendarExpiration.setTime(incomeFunction.getEndDate()); // asigna fecha del parametro

        if (proceFunction.getExpiration().contains("años")) {
            int anios = Integer.parseInt(proceFunction.getExpiration().substring(0,1));
            calendarExpiration.add(Calendar.YEAR, anios);
        }else if (proceFunction.getExpiration().contains("meses")){
            int meses = Integer.parseInt(proceFunction.getExpiration().substring(0,1));
            calendarExpiration.add(Calendar.MONTH, meses);
        } else if (proceFunction.getExpiration().contains("permanente")) {
            calendarExpiration=null;
        }

        return calendarExpiration;
    }



} // end of controller to incomes
