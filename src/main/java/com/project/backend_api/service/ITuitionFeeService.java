package com.project.backend_api.service;

import java.util.List;
import java.util.Optional;

import com.project.backend_api.model.TuitionFee;

public interface ITuitionFeeService {

	List<TuitionFee> getAllTuitionFee();
    Optional<TuitionFee> getTuitionFeeById(Long id);
    TuitionFee createTuitionFee(TuitionFee tuitionFee);
    TuitionFee updateTuitionFee(Long id, TuitionFee tuitionFee);
    void deleteTuitionFee(Long id);
}
