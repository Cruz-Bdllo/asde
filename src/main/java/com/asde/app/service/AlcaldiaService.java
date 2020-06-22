package com.asde.app.service;

import com.asde.app.domain.Alcaldia;
import com.asde.app.repository.AlcaldiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlcaldiaService implements IAlcaldiaService{
    private AlcaldiaRepository alcaldiaRepo;

    @Autowired
    public AlcaldiaService (AlcaldiaRepository alcaldiaRepo) {
        this.alcaldiaRepo = alcaldiaRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alcaldia> getAllAlcaldias() {
        return (List<Alcaldia>) alcaldiaRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Alcaldia getAlcaldiaById(Integer idAlcaldia) {
        return alcaldiaRepo.findById(idAlcaldia).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveAlcaldia(Alcaldia alcaldia) {
        alcaldiaRepo.save(alcaldia);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAlcaldia(Integer idAlcaldia) {
        alcaldiaRepo.deleteById(idAlcaldia);
    }
} // end service to alcaldia
