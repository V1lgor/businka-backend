package ru.vilgor.businkabackend.service;

import ru.vilgor.businkabackend.exceptions.ImageFileNotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService{
    @Override
    public byte[] getImageByPath(String path) throws ImageFileNotFoundException {
        Resource resource = new ClassPathResource(path);
        try {
            return IOUtils.toByteArray(resource.getInputStream());
        }
        catch (IOException e) {
            throw new ImageFileNotFoundException(path);
        }
    }
}
