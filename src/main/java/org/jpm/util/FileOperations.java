package org.jpm.util;

import org.jpm.FileNameValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileOperations {

    private static final Logger logger = LoggerFactory.getLogger(FileOperations.class);

    public boolean fileExists(String path) {
        File file = createFile(path);
        return file != null && file.exists() && file.isFile();
    }

    protected File createFile(String path) {
        if (path == null) {
            logger.error("File location path is invalid.");
            return null;
        }
        return new File(path);
    }
}


