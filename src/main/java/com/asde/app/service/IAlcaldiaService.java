package com.asde.app.service;

import com.asde.app.domain.Alcaldia;

import java.util.List;

public interface IAlcaldiaService {

    List<Alcaldia> getAllAlcaldias();

    Alcaldia getAlcaldiaById(Integer idAlcaldia);

    void saveAlcaldia(Alcaldia alcaldia);

    void deleteAlcaldia(Integer idAlcaldia);

} // end service interface
