package com.shop.admin.product.service;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;

class ExistingFileRenameUtils {

    /**
     * test.png
     * test-1.png
     * test-2.png
     * test-3.png
     * test-4.png
     */
    public static String renameIfExists(Path uploadDit, String fileName) {
        if (Files.exists(uploadDit.resolve(fileName))) {
            return renameAndCheck(uploadDit, fileName);
        }
        return fileName;
    }

    private static String renameAndCheck(Path uploadDit, String fileName) {
        String newName = renameFileName(fileName);
        if (Files.exists(uploadDit.resolve(newName))) {
            newName = renameAndCheck(uploadDit, newName);
        }
        return newName;
    }

    private static String renameFileName(String fileName) {
        String name = FilenameUtils.getBaseName(fileName);
        String[] split = name.split("-(?=[0-9]+$)");
        int counter = split.length > 1 ? Integer.parseInt(split[1]) + 1 : 1;
        return split[0] + "-" + counter + "." + FilenameUtils.getExtension(fileName);
    }
}
