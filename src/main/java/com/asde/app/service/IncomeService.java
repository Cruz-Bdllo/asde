package com.asde.app.service;

import com.asde.app.domain.Income;
import com.asde.app.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncomeService implements IIncomeService{
    private IncomeRepository incomeRepo;

    @Autowired
    public IncomeService(IncomeRepository incomeRepo) {
        this.incomeRepo = incomeRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Income> getAllIncomes() {
        return incomeRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Income getIncomeByCodIncome(String codIncome) {
        return incomeRepo.getIncomeByCodIncome(codIncome).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveIncome(Income income) {
        incomeRepo.save(income);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteIncomeById(Integer idIncome) {
        incomeRepo.deleteById(idIncome);
    }
} // end service income
