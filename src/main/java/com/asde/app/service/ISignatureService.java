package com.asde.app.service;

import com.asde.app.domain.Signature;

import java.util.List;

public interface ISignatureService {

    List<Signature> getAllSignatures();

    Signature findSignatureById(Integer idSignature);

    void saveSignature(Signature signature);

    void deleteSignatureById(Integer idSignature);

}
