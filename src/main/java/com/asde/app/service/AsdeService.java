package com.asde.app.service;

import com.asde.app.domain.Asde;
import com.asde.app.repository.AsdeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AsdeService implements IAsdeService{
    private AsdeRepository asdeRepo;

    @Autowired
    public AsdeService(AsdeRepository asdeRepo) {
        this.asdeRepo = asdeRepo;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Asde> findAsde() {
        return (List<Asde>) asdeRepo.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveAsde(Asde asde) {
        asdeRepo.save(asde);
    }
}
