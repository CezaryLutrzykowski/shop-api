package com.shop.admin.product.service;

import com.shop.admin.common.utils.SlugifyUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AdminProductImageService {

    @Value("${app.uploadDit}")
    private String uploadDit;

    public String uploadImage(String filename, InputStream inputStream) {
        String newFileName = SlugifyUtils.slugifyFileName(filename);
        newFileName = ExistingFileRenameUtils.renameIfExists(Path.of(uploadDit), newFileName);
        Path filePath = Path.of(uploadDit).resolve(newFileName);
        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException("Nie mogę zapisać pliku", e);
        }
        return newFileName;
    }

    public Resource serveFiles(String filename) {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        return fileSystemResourceLoader.getResource(uploadDit + filename);
    }
}
