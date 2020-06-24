package com.asde.app.service.income;

import com.asde.app.domain.Dependence;
import com.asde.app.domain.Income;

import java.util.List;


/**
 * Interfaz que permite definir los métodos que podrán usar las clase, servicios, componentes o controladores para
 * acceder a la BD y manipular la tabla <b>ingresos</b>, definiendo nombres mas comodos a los que ofrecen las
 * interfaces CRUD {@link org.springframework.data.repository.CrudRepository} o
 * {@link org.springframework.data.jpa.repository.JpaRepository}
 */
public interface IIncomeService {

    /**
     * Obtendrá todos los registros de la tabla <b>ingresos</b> en la BD.
     * @return Lista de objetos de la entidad {@link Income}.
     */
    List<Income> getAllIncomes();


    /**
     * Obtendrá un registro en especifico definido en su parámetro y este sera el folio de ingreso.
     * @param codIncome Integer Identificador único a buscar (folio).
     * @return Objeto de la entidad {@link Income}.
     */
    Income getIncomeByCodIncome(String codIncome);


    /**
     * Obtendrá un registro en especifico definido en su parámetro y este sera el id auto generado por la BD.
     * @param idIncome Integer Identificador único a buscar.
     * @return Objeto de la entidad {@link Income}.
     */
    Income getIncomeById(Integer idIncome);


    /**
     * Guardará la entidad {@link Income} como registro en la tabla <b>empresa</b> de la BD.
     * @param income Objeto de la entidad {@link Income} a guardar.
     */
    void saveIncome(Income income);


    /**
     * Eliminará un registro de la tabla <b>ingresos</b> dado su identificador único en su parámetro.
     * @param idIncome Identificador único a eliminar.
     */
    void deleteIncomeById(Integer idIncome);

}
