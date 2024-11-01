package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.Employee;
import com.project.backend_api.model.Image;
import com.project.backend_api.model.Student;
import com.project.backend_api.repository.EmployeeRepository;
import com.project.backend_api.repository.ImageRepository;
import com.project.backend_api.repository.StudentRepository;
import com.project.backend_api.service.IImageService;

@Service
public class ImageServiceImpl implements IImageService{

	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Image> getAllImage() {
		// TODO Auto-generated method stub
		return imageRepository.findAll();
	}

	@Override
	public Optional<Image> getImageById(Long id) {
		// TODO Auto-generated method stub
		return imageRepository.findById(id);
	}

	@Override
	public Image createImage(Image image) {
	    if (image.getStudent() != null && image.getStudent().getId() != null) {
	        // Tìm kiếm sinh viên theo ID và gán cho hình ảnh
	        Student student = studentRepository.findById(image.getStudent().getId()).orElseThrow();
	        image.setStudent(student);
	    }
	    if (image.getEmployee() != null && image.getEmployee().getId() != null) {
	    	Employee employee = employeeRepository.findById(image.getEmployee().getId()).orElseThrow();
	    	image.setEmployee(employee);
	    }
	    return imageRepository.save(image);
	}

	@Override
	public Image updateImage(Long id, Image image) {
	    Image existingImage = imageRepository.findById(id)
	            .orElseThrow();

	    // Cập nhật các trường khác
	    existingImage.setImageName(image.getImageName());
	    existingImage.setImageUrl(image.getImageUrl());
	    existingImage.setEmployee(image.getEmployee());
	    existingImage.setStudent(image.getStudent());

	    // Cập nhật sinh viên nếu có
	    if (image.getStudent() != null && image.getStudent().getId() != null) {
	        // Tìm kiếm sinh viên theo ID và gán cho hình ảnh
	        Student student = studentRepository.findById(image.getStudent().getId())
	                .orElseThrow();
	        existingImage.setStudent(student);
	    }

	    if (image.getEmployee() != null && image.getEmployee().getId() != null) {
	    	Employee employee = employeeRepository.findById(image.getEmployee().getId())
	    			.orElseThrow();
	    	existingImage.setEmployee(employee);
	    }
	    
	    return imageRepository.save(existingImage);
	}


	@Override
	public void deleteImage(Long id) {
		// TODO Auto-generated method stub
		imageRepository.deleteById(id);
	}

	
}
