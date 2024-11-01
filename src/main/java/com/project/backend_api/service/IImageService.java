package com.project.backend_api.service;

import java.util.List;
import java.util.Optional;

import com.project.backend_api.model.Image;

public interface IImageService {

	List<Image> getAllImage();
	Optional<Image> getImageById(Long id);
	Image createImage(Image image);
	Image updateImage(Long id, Image image);
	void deleteImage(Long id);
}
