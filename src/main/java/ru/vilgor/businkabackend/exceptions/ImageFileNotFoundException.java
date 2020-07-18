package ru.vilgor.businkabackend.exceptions;

public class ImageFileNotFoundException extends Exception {
    private static final String ERROR_MESSAGE = "Image file not found.";

    private String filePath;

    public ImageFileNotFoundException(String filePath) {
        super(ERROR_MESSAGE);
        this.filePath = filePath;
    }

    @Override
    public String getMessage() {
        return ERROR_MESSAGE + " Image path: " + filePath;
    }
}
