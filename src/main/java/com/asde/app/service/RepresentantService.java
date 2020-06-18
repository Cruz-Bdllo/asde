package com.asde.app.service;

import com.asde.app.domain.Representant;
import com.asde.app.repository.RepresentantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RepresentantService implements IRepresentantService {
    private RepresentantRepository repreRepo;

    @Autowired
    public RepresentantService(RepresentantRepository repreRepo){
        this.repreRepo = repreRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Representant> getAllRepresentants() {
        return repreRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Representant getRepresentantById(Integer idRepresentant) {
        return repreRepo.findById(idRepresentant).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteRepresentantById(Integer IdRepresentant) {
        repreRepo.deleteById(IdRepresentant);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveRepresentant(Representant representant) {
        repreRepo.save(representant);
    }
}
