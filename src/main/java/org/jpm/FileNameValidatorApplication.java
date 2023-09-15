package org.jpm;

import org.jpm.util.FileOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileNameValidatorApplication {

    private static final Logger logger = LoggerFactory.getLogger(FileNameValidatorApplication.class);

    public static void main(String[] args) {

        if (args == null || args.length != 1 || args[0] == null || args[0].trim().isEmpty()) {
            logger.error("Usage: gradle run --args=/path/to/your/file.csv");
            System.exit(1);
        }

        logger.info("File Submitted : " + args[0]);

        String inputFile = args[0];

        // Create an instance of FileOperations and use it in your validation logic
        FileOperations fileOperations = new FileOperations();
        FileNameValidator validator = new FileNameValidator(fileOperations);

        // Validate the provided input file
        boolean isValid = validator.validateFileName(inputFile);

        // Exit with an appropriate exit code based on validation result
        System.exit(isValid ? 0 : 1);
    }

}
