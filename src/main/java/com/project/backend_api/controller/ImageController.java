package com.project.backend_api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend_api.dto.ImageDTO;
import com.project.backend_api.mapper.ImageMapper;
import com.project.backend_api.model.Image;
import com.project.backend_api.service.IImageService;

@RestController
@RequestMapping("/api/image")
public class ImageController {

	@Autowired
	private IImageService iImageService;
	
	@GetMapping
	public ResponseEntity<List<ImageDTO>> getAllImage(){
		List<Image> images = iImageService.getAllImage();
		List<ImageDTO> imageDTO = images.stream()
				.map(ImageMapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(imageDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ImageDTO> getImageById(@PathVariable Long id){
		Optional<Image> image = iImageService.getImageById(id);
		return image.map(ImageMapper::toDTO).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	@PostMapping
	public ResponseEntity<Image> createImage(@RequestBody Image image){
		Image createImage = iImageService.createImage(image);
		return ResponseEntity.ok(createImage);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Image> updateImage(@PathVariable Long id, @RequestBody Image image){
		Image updateImage = iImageService.updateImage(id, image);
		return ResponseEntity.ok(updateImage);
	}
}
