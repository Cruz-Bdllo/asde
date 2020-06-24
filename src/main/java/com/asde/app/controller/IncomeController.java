package com.asde.app.controller;

import com.asde.app.domain.Alcaldia;
import com.asde.app.domain.Client;
import com.asde.app.domain.Income;
import com.asde.app.domain.Procedure;
import com.asde.app.service.alcaldia.IAlcaldiaService;
import com.asde.app.service.client.IClientService;
import com.asde.app.service.income.IIncomeService;
import com.asde.app.service.procedure.IProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


/**
 * Este controlador permitira manejar todas la peticiones entrantes a <b>/ingresos/*</b> que gestiona los ingresos
 * realizados por el usuario, en el se podrá acceder a la lista de todos los que ha registrado en la BD, ademas de
 * acceder a los detalles de uno en especifico, de actualizar y eliminar la información.
 */

@Controller
@RequestMapping("/ingresos")
public class IncomeController {

    /* ~    PROPERTIES
    --------------------------------------------------- */
    private IAlcaldiaService alcaldiaService;
    private IClientService clientService;
    private IProcedureService procedureService;
    private IIncomeService incomeService;



    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public IncomeController(IAlcaldiaService alcaldiaService, IClientService clientService, IProcedureService procedureService, IIncomeService incomeService) {
        this.alcaldiaService = alcaldiaService;
        this.clientService = clientService;
        this.procedureService = procedureService;
        this.incomeService = incomeService;
    }


    /* ~    MODELS
    --------------------------------------------------- */

    /**
     * Método que permite extraer por medio de la capa de servicio todos los registros
     * de la tabla <b>alcaldias</b> de la BD, para poder usarlos dentro de las plantillas
     * de thymeleaf que pertenescan a la ruta /ingresos/*.
     * @return Lista de objetos Alcaldia.
     */
    @ModelAttribute(name = "alcaldias")
    public List<Alcaldia> alcaldias() { return alcaldiaService.getAllAlcaldias(); }

    /**
     * Método que permite extraer por medio de la capa de servicio todos los registros
     * de la tabla <b>ingresos</b> de la BD, para poder usarlos dentro de las plantillas
     * de thymeleaf que pertenescan a la ruta /ingresos/*.
     * @return Lista de objetos Income.
     */
    @ModelAttribute(name = "incomes")
    public List<Income> incomes(){ return incomeService.getAllIncomes(); }

    /**
     * Método que permite extraer por medio de la capa de servicio todos los registros
     * de la tabla <b>empresa</b> de la BD, para poder usarlos dentro de las plantillas
     * de thymeleaf que pertenescan a la ruta /ingresos/*.
     * @return Lista de objetos Client.
     */
    @ModelAttribute(name = "clients")
    public List<Client> clients(){ return clientService.getAllClients(); }

    /**
     * Método que permite extraer por medio de la capa de servicio todos los registros
     * de la tabla <b>tramites</b> de la BD, para poder usarlos dentro de las plantillas
     * de thymeleaf que pertenescan a la ruta /ingresos/*.
     * @return Lista de objetos Procedure.
     */
    @ModelAttribute(name = "procedures")
    public List<Procedure> procedures(){ return procedureService.getAllProcedures(); }

    /**
     * Permite acceder a los tipos enum de la clase <b>Income</b>, para poder usarlos dentro de las plantillas
     * de thymeleaf que pertenescan a la ruta /ingresos/*.
     * @return Arreglo TYPE_STATUS.
     */
    @ModelAttribute(name = "statues")
    public Income.TYPE_STATUS[] statues(){ return Income.TYPE_STATUS.values(); }

    /**
     * Permite acceder a los tipos enum de la clase <b>Income</b>, para poder usarlos dentro de las plantillas
     * de thymeleaf que pertenescan a la ruta /ingresos/*.
     * @return Arreglo TYPE_DELIVERED.
     */
    @ModelAttribute(name = "deliveries")
    public Income.TYPE_DELIVERED[] deliveries(){ return Income.TYPE_DELIVERED.values(); }




    /* ~    CONTROLLERS
    -------------------------------------------------------------------------- */

    /**
     * Método que manejara las peticiones cuando el usuario desee acceder a la lista de todos los
     * ingresos que ha realizado.
     * @param model Enviar datos a la plantilla <b>homeIncome</b>
     * @return plantilla <b>homeIncome</b> en formato HTML.
     */
    @GetMapping
    public String incomeIndex(Model model) {
        model.addAttribute("title", "Ingresos");
        model.addAttribute("subtitle", "Lista de ingresos");
        model.addAttribute("formatDate", new SimpleDateFormat("EEEE dd MMMMM, YYYY",
                new Locale("es", "ES")));

        return "/incomes/homeIncome";
    }


    /**
     * Método que menejara las peticiones cuando el usuario desee agregar un nuevo ingreso por medio
     * del formulario.
     * @param model Enviar datos a la plantilla <b>formIncome</b>
     * @return plantilla <b>formIncome</b> en formato HTML.
     */
    @GetMapping("/crear")
    public String viewFormNewIncome(Model model) {
        model.addAttribute("title", "Nuevo ingreso");
        model.addAttribute("subtitle", "Registra un ingreso que hayas hecho");
        model.addAttribute("income", new Income());

        return "/incomes/formIncome";
    }


    /**
     * Método que menejara el envio de datos por medio del formulario <b>formIncome</b> (POST) el cual permitira
     * registrar en la BD un nuevo ingreso, validando su contenido y realizando el guardado.
     * @param income Objeto enviado desde el formulario para su almacenamiento.
     * @param errors Objeto creado en caso de algún error en la validación de la entidad.
     * @param model Enviar datos a la plantilla <b>formIncome</b> en caso de algún error.
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return Redirecciona a la lista de ingresos.
     */
    @PostMapping("/guardar")
    public String saveIncome(@Valid Income income, Errors errors, Model model, RedirectAttributes msjHeader) {
        if ( errors.hasErrors() ) {
            model.addAttribute("title", (income.getIdIncome() != null) ? "Actualizar ingreso"
                    : "Nuevo ingreso");
            model.addAttribute("subtitle", "Corrija los siguiente campos para guardar su ingreso");

            return (income.getIdIncome() != null) ? "/incomes/updateIncome" : "/incomes/formIncome";
        }

        if ( income.getIdIncome() != null ) { // es una actualización
            Income incomeDB = incomeService.getIncomeById(income.getIdIncome());
            income.setExpirateDate((incomeDB.getExpirateDate() != null)?incomeDB.getExpirateDate():null);
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
        msjHeader.addFlashAttribute("success", "El trámite "+income.getProcedure().getName()+
                " se ha guardado correctamente para "+income.getClient().getName());

        return "redirect:/ingresos";
    }


    /**
     * Método que permitira eliminar un registro especifico de la BD en la tabla <b>ingresos</b>, por medio
     * de la clave principal dada como parámetro.
     * @param codIncome String clave principal que identifica al registro a eliminar.
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return Redirecciona a la lista de ingresos.
     */
    @GetMapping("/eliminar/{codIncome}")
    public String deleteIncome(@PathVariable String codIncome, RedirectAttributes msjHeader) {
        Income income = incomeService.getIncomeByCodIncome(codIncome.replace('_','/'));
        if (income == null ) {
            msjHeader.addFlashAttribute("error", "El folio "+codIncome.replace('_','/')+" no existe, intentelo con uno valido");

        }else{
            incomeService.deleteIncomeById(income.getIdIncome());
            msjHeader.addFlashAttribute("success", "El acuse con el folio "+codIncome.replace('_','/')+" ha sido eliminado correctamente");
        }

        return "redirect:/ingresos";
    }


    /**
     * Método que permitira cambiar el estado de un ingreso por medio de un formulario.
     * @param codIncome String clave principal que identifica al registro a eliminar.
     * @param model Enviar datos a la plantilla <b>homeIncome</b> en caso de algún error.
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return plantilla <b>changeStatusForm</b> en formato HTML.
     */
    @GetMapping("/cambiar-estado/{codIncome}")
    public String calcDateExpiration(@PathVariable String codIncome, Model model, RedirectAttributes msjHeader) {
        Income income = incomeService.getIncomeByCodIncome(codIncome.replace('_','/'));
        if (income == null) {
            msjHeader.addFlashAttribute("error", "El acuse con el folio "+codIncome.replace('_','/')+
                    " no existe, intente con uno valido");
            return "redirect:/ingresos";
        }
        model.addAttribute("income", income);
        model.addAttribute("title", "Cambiar estado");
        model.addAttribute("statues", Income.TYPE_STATUS.values());
        model.addAttribute("subtitle", "Cambiar estado del ingreso con el folio "+income.getCodIncome());

        return "/incomes/changeStatusForm";
    }


    /**
     * Método que manejara el envío de datos por medio del formulario <b>changeStatusForm</b> (POST) el cual permitira
     * actualizar el estado de un registro de la BD.
     * @param income Objeto enviado desde el formulario.
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return Redirecciona a la lista de ingresos.
     */
    @PostMapping("/guardar-estado")
    public String saveChangeStatus (Income income, RedirectAttributes msjHeader) {

        Income incomeDB = incomeService.getIncomeByCodIncome(income.getCodIncome());
        incomeDB.setStatus(income.getStatus());
        incomeDB = calcDateExpiration(incomeDB);
        incomeService.saveIncome(incomeDB);
        msjHeader.addFlashAttribute("success", "Se ha cambiado el estado de " +
                income.getCodIncome()+" ha "+income.getStatus().name());

        return "redirect:/ingresos";
    }


    /**
     * Método que manejara las peticiones para que el usuario pueda acceder a los detalles de un ingreso en
     * especifico dando como parámetro el folio de ingreso.
     * @param codIncome String clave principal que identifica al registro a eliminar.
     * @param model Enviar datos a la plantilla <b>detailsIncome</b> en caso de algún error.
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return plantilla <b>detailsIncome</b> en formato HTML.
     */
    @GetMapping("detalles/{codIncome}")
    public String detailsIncome ( @PathVariable String codIncome, Model model, RedirectAttributes msjHeader ) {
        Income income = incomeService.getIncomeByCodIncome(codIncome.replace('_','/'));
        if ( income == null ) {
            msjHeader.addFlashAttribute("error", "El ingreso con folio " +
                    codIncome.replace('_','/') + " no existe, intente con uno válido.");
            return "redirect:/ingresos";
        }
        model.addAttribute("title", "Detalles del ingreso " + codIncome.replace('_','/'));
        model.addAttribute("subtitle", "Detalles del ingreso "+income.getProcedure().getName()+
                " ("+codIncome.replace('_','/')+")");
        model.addAttribute("income", income);
        model.addAttribute("formatDate", new SimpleDateFormat("EEEE dd, MMMMM YYYY",
                new Locale("es", "ES")));

        return "/incomes/detailsIncome";
    } // end details to income


    /**
     * Método que manejara las peticiones para actualizar la información de un ingreso en especifico, mandandolo a un
     * formulario para realizar los cambios.
     * @param codIncome String clave principal que identifica al registro a eliminar.
     * @param model Enviar datos a la plantilla <b>detailsIncome</b> en caso de algún error.
     * @param msjHeader Define mensajes personalizados en la cabecera al realizar algúna acción.
     * @return plantilla <b>updateIncome</b> en formato HTML.
     */
    @GetMapping("/actualizar/{codIncome}")
    public String updateIncome ( @PathVariable String codIncome, Model model, RedirectAttributes msjHeader ) {
        Income income = incomeService.getIncomeByCodIncome(codIncome.replace('_','/'));
        if ( income == null ) {
            msjHeader.addFlashAttribute("error", "El ingreso con folio " + codIncome.replace('_','/') +" no existe" +
                    ", intente con uno válido");
            return "redirect:/ingresos";
        }
        model.addAttribute("title", "Actualizar ingreso");
        model.addAttribute("subtitle", "Modificar ingreso con el folio " + income.getCodIncome());
        model.addAttribute("income", income);

        return "/incomes/updateIncome";
    }



    /* ~    UTILS
    -------------------------------------------------------------------------- */

    /**
     * Este método asigna la fecha en que expirara el documento ingresado una vez que tenga un estado diferente a
     * <b>INGRESADO</b> ademas de establecer que el documentos es de tipo vigente.
     * @param incomeForm Objeto Income sobre el cual se trabajara.
     * @return Objeto Income con la fecha de vigencia y estado de vigencia actualizados.
     */
    private Income calcDateExpiration(Income incomeForm) {
        if(incomeForm.getStatus() == Income.TYPE_STATUS.COMPLETADO) {
            // Empieza la vigencia del documento
            incomeForm.setExpirate(true);

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


    /**
     * Este método define y establece la fecha de vigencia, aumentando a la fecha en que se recogio el documento
     * la fecha de vigencia del mismo, mediante el tipo de tramite que se ingreso.
     * @param proceFunction Procedure obtiene la fecha de duración.
     * @param incomeFunction Income obtener la fecha en que se recogio el trámite.
     * @return Calendar con la fecha en que el documento expirara.
     */
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
