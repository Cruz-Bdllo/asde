package com.asde.app.service.income;

import com.asde.app.domain.Income;
import com.asde.app.repository.IncomeRepository;
import com.asde.app.service.income.IIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de servicio que implementa los métodos de la interfaz {@link IIncomeService}, esta define el comportamiento
 * de los métodos de la interfaz, los cuales son las operaciones básicas CRUD a la tabla <b>ingresos</b>.
 */

@Service
public class IncomeService implements IIncomeService{

    /* ~    PROPERTIES
    --------------------------------------------------- */
    private IncomeRepository incomeRepo;


    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public IncomeService(IncomeRepository incomeRepo) {
        this.incomeRepo = incomeRepo;
    }



    /* ~    METHODS
    --------------------------------------------------- */

    /**
     * Método que extrae de la BD todos los registros que obren en la tabla <b>ingresos</b>.
     * @return Lista de objetos {@link Income}.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Income> getAllIncomes() {
        return incomeRepo.findAll();
    }


    /**
     * Método que extrae de la BD un registro en especifico por medio de su clave de identificación única generada por
     * la tabla <b>ingresos</b>.
     * @param idIncome Clave única de identificación.
     * @return Objeto {@link Income} si existe algún registro o <b>null</b> en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public Income getIncomeById(Integer idIncome) {
        return incomeRepo.getIncomeByIdIncome(idIncome).orElse(null);
    }


    /**
     * Método que extrae de la BD un registro en especifico por medio de su folio de ingreso
     * de la tabla <b>ingresos</b>.
     * @param codIncome folio de ingreso.
     * @return Objeto {@link Income} si existe algún registro o <b>null</b> en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public Income getIncomeByCodIncome(String codIncome) {
        return incomeRepo.getIncomeByCodIncome(codIncome).orElse(null);
    }


    /**
     * Método que guarda la entidad {@link Income} en la BD en la tabla <b>ingresos</b>.
     * @param income Objeto {@link Income} a guardar.
     */
    @Override
    @Transactional(readOnly = false)
    public void saveIncome(Income income) {
        incomeRepo.save(income);
    }


    /**
     * Método que elimina un registro en especifico de la tabla <b>ingresos</b> dada una clave única
     * de identificación.
     * @param idIncome Clave única para identificar al registro a eliminar.
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteIncomeById(Integer idIncome) {
        incomeRepo.deleteById(idIncome);
    }
} // end service income
