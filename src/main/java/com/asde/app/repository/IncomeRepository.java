package com.asde.app.repository;


import com.asde.app.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Interfaz que permite usar las operaciones básicas para manipular la tabla <b>ingresos</b> de la BD, asignando
 * como clave primaria un objeto de tipo <b>Integer</b>, esto lo realiza gracias a la herencia de la interfaz
 * {@link JpaRepository}, támbien hace uso de la interfaz {@link PagingAndSortingRepository} para poder
 * paginar los registros en caso de que sean demasiados teniendo un mejor control en el despliegue de los mismos.
 */

public interface IncomeRepository extends JpaRepository<Income, Integer> {

    /**
     * Método que personaliza una query, obtiendo un objeto Income de la BD de acuerdo al identificador único que se le
     * proporcione como parámetro.
     * @param idIncome Identificador único a buscar en la BD.
     * @return El objeto <b>Income</b>> de la BD o <b>null</b> en caso contrario.
     */
    Optional<Income> getIncomeByIdIncome(Integer idIncome);

    /**
     * Método que personaliza una query, obtiendo un objeto <b>Income</b>  de la BD de acuerdo al folio del trámite
     * ingresado proporcionado como parámetro.
     * @param codIncome Identificador único a buscar en la BD.
     * @return El objeto <b>Income</b> de la BD o <b>null</b> en caso contrario.
     */
    Optional<Income> getIncomeByCodIncome(String codIncome);
}
