package com.asde.app.service;

import com.asde.app.domain.Asde;

import java.util.List;

public interface IAsdeService {

    List<Asde> findAsde();

    void saveAsde(Asde asde);

}
