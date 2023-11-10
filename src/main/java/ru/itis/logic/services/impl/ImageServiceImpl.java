package ru.itis.logic.services.impl;

import ru.itis.dao.entities.Image;
import ru.itis.dao.repositories.ImageRepository;
import ru.itis.logic.services.ImageService;

import javax.servlet.http.Part;
import java.util.List;

public class ImageServiceImpl implements ImageService {

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    private final ImageRepository imageRepository;

    @Override
    public void create(Part file) {
        imageRepository.create(file);
    }

    @Override
    public void create(List<Image> images) {
        for (Image image : images) {
            imageRepository.create(image);
        }
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }
}
