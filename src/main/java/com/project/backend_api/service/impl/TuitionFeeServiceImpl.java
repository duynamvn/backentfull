package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.TuitionFee;
import com.project.backend_api.repository.TuitionFeeRepository;
import com.project.backend_api.service.ITuitionFeeService;

@Service
public class TuitionFeeServiceImpl implements ITuitionFeeService{

	@Autowired
    private TuitionFeeRepository tuitionFeeRepository;

    @Override
    public List<TuitionFee> getAllTuitionFee() {
        return tuitionFeeRepository.findAll();
    }

    @Override
    public Optional<TuitionFee> getTuitionFeeById(Long id) {
        return tuitionFeeRepository.findById(id);
    }

    @Override
    public TuitionFee createTuitionFee(TuitionFee tuitionFee) {
        return tuitionFeeRepository.save(tuitionFee);
    }

    @Override
    public TuitionFee updateTuitionFee(Long id, TuitionFee tuitionFee) {
        TuitionFee existingTuitionFee = tuitionFeeRepository.findById(id).orElseThrow(null);
        existingTuitionFee.setRegistrationDate(tuitionFee.getRegistrationDate());
        existingTuitionFee.setCollectionDate(tuitionFee.getCollectionDate());
        existingTuitionFee.setNote(tuitionFee.getNote());
        existingTuitionFee.setCollectedMoney(tuitionFee.getCollectedMoney());
        existingTuitionFee.setActivate(tuitionFee.getActivate());
        existingTuitionFee.setCourse(tuitionFee.getCourse());
        return tuitionFeeRepository.save(existingTuitionFee);
    }

    @Override
    public void deleteTuitionFee(Long id) {
        tuitionFeeRepository.deleteById(id);
    }

}
