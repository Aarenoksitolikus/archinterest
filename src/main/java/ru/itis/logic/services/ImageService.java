package ru.itis.logic.services;

import ru.itis.dao.entities.Image;

import javax.servlet.http.Part;
import java.util.List;

public interface ImageService {
    void create(Part file);
    void create(List<Image> images);

    List<Image> findAll();
}
