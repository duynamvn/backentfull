package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.Employee;
import com.project.backend_api.model.Specialization;
import com.project.backend_api.repository.EmployeeRepository;
import com.project.backend_api.repository.SpecializationRepository;
import com.project.backend_api.service.ISpecializationService;

@Service
public class SpecializationServiceImpl implements ISpecializationService{
	
	@Autowired
	private SpecializationRepository specializationRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Specialization> getAllSpecialization() {
		// TODO Auto-generated method stub
		return specializationRepository.findAll();
	}

	@Override
	public Optional<Specialization> getSpecializationById(Long id) {
		// TODO Auto-generated method stub
		return specializationRepository.findById(id);
	}

	@Override
	public Specialization createSpecialization(Specialization specialization) {
		// TODO Auto-generated method stub
		List<Employee> employees = Optional.ofNullable(specialization.getEmployees())
				.orElse(List.of()).stream()
				.map(employee -> employeeRepository.findById(employee.getId())
						.orElse(employee))
				.collect(Collectors.toList());
		specialization.setEmployees(employees);
		return specializationRepository.save(specialization);
	}

	@Override
	public Specialization updateSpecialization(Long id, Specialization specialization) {
		// TODO Auto-generated method stub
		Specialization existingSpecialization = specializationRepository.findById(id).orElseThrow();
		if(specialization != null) {
			existingSpecialization.setSpecializationName(specialization.getSpecializationName());
			return specializationRepository.save(existingSpecialization);
		}
		return null;
	}

	@Override
	public void deleteSpecialization(Long id) {
		// TODO Auto-generated method stub
		specializationRepository.deleteById(id);
		
	}

}
