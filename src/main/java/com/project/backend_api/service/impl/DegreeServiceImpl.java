package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.Degree;
import com.project.backend_api.model.Employee;
import com.project.backend_api.repository.DegreeRepository;
import com.project.backend_api.repository.EmployeeRepository;
import com.project.backend_api.service.IDegreeService;

@Service
public class DegreeServiceImpl implements IDegreeService{

	@Autowired
	private DegreeRepository degreeRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Degree> getAllDegree() {
		// TODO Auto-generated method stub
		return degreeRepository.findAll();
	}

	@Override
	public Optional<Degree> getDegreeById(Long id) {
		// TODO Auto-generated method stub
		return degreeRepository.findById(id);
	}

	@Override
	public Degree createDegree(Degree degree) {
	    // Kiểm tra và khởi tạo danh sách trống nếu employees là null
	    List<Employee> employees = Optional.ofNullable(degree.getEmployees())
	                                       .orElse(List.of())
	                                       .stream()
	                                       .map(employee -> employeeRepository.findById(employee.getId())
	                                           .orElse(employee))
	                                       .collect(Collectors.toList());
	    degree.setEmployees(employees);
	    return degreeRepository.save(degree);
	}


	@Override
	public Degree updateDegree(Long id, Degree degree) {
		
		Degree existingDegree = degreeRepository.findById(id).orElseThrow();
		if(degree != null) {
			existingDegree.setDegreeName(degree.getDegreeName());
			existingDegree.setNote(degree.getNote());
			return degreeRepository.save(existingDegree);
		}
		
		return null;
	}

	@Override
	public void deleteDegree(Long id) {
		// TODO Auto-generated method stub
		degreeRepository.deleteById(id);
		
	}

}
