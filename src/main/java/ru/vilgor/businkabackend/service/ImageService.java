package ru.vilgor.businkabackend.service;

import ru.vilgor.businkabackend.exceptions.ImageFileNotFoundException;

public interface ImageService {
    byte[] getImageByPath(String path) throws ImageFileNotFoundException;
}
