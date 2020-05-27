package com.asde.app.service;

import com.asde.app.domain.Signature;
import com.asde.app.repository.SignatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SignatureService implements ISignatureService{
    /* ~    PROPERTIES
    --------------------------------------------------- */
    private SignatureRepository signatureRepo;


    /* ~    METHODS
    --------------------------------------------------- */
    @Autowired
    public SignatureService(SignatureRepository signatureRepo) {
        this.signatureRepo = signatureRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Signature> getAllSignatures() {
        return signatureRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Signature findSignatureById(Integer idSignature) {
        return signatureRepo.findById(idSignature).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveSignature(Signature signature) {
        signatureRepo.save(signature);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSignatureById(Integer idSignature) {
        signatureRepo.deleteById(idSignature);
    }

} // end service implementation
