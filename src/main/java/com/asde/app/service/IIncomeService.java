package com.asde.app.service;

import com.asde.app.domain.Income;

import java.util.List;

public interface IIncomeService {

    List<Income> getAllIncomes();

    Income getIncomeByCodIncome(String codIncome);

    Income getIncomeById(Integer idIncome);

    void saveIncome(Income income);

    void deleteIncomeById(Integer idIncome);

}
