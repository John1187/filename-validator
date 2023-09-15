package org.jpm;

import org.jpm.construct.ValidationMessages;
import org.jpm.util.FileOperations;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileNameValidator {
    private static final Pattern FILE_NAME_PATTERN = Pattern.compile("^Test_[ABC]_\\d{8}_\\d{2}\\.csv$");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("ddMMyyyy");
    private static final Logger logger = LoggerFactory.getLogger(FileNameValidator.class);
    private final FileOperations fileOperations;

    public FileNameValidator(FileOperations fileOperations) {
        this.fileOperations = fileOperations;
    }

    public boolean validateFileName(String fileLocation) {

        if (!fileOperations.fileExists(fileLocation)) {
            logger.error("File location is invalid or file does not exist.");
            return false;
        }

        String fileName = new File(fileLocation).getName();
        Matcher matcher = FILE_NAME_PATTERN.matcher(fileName);

        ;

        if (matcher.matches()) {
            String[] parts = fileName.split("_");
            String portfolioCode = parts[1];
            String dateString = parts[2];
            String sequenceNumber = parts[3].split("\\.")[0];

            if (!isValidDate(dateString)) {
                printValidationFailure(fileName, ValidationMessages.ErrorMessage.DATE_FORMAT_ERROR, fileName);
                return false;
            }

            // File name is valid
            logger.info("File '" + fileName + "' passed validation.");
            return true;
        } else {
            // File name is invalid, provide details about the failure
            validateAndPrintFailure(fileName);
            return false;
        }
    }

    private static void validateAndPrintFailure(String fileName) {
        String[] parts = fileName.split("_");
        if (parts.length != 4) {
            printValidationFailure(fileName, ValidationMessages.ErrorMessage.FILE_FORMAT_ERROR, fileName);
        } else if (!parts[0].equals("Test")) {
            // Invalid prefix
            printValidationFailure(fileName, ValidationMessages.ErrorMessage.PREFIX_ERROR, parts[0]);
        } else if (!parts[1].matches("[ABC]")) {
            // Invalid portfolio code
            printValidationFailure(fileName, ValidationMessages.ErrorMessage.PORTFOLIO_CODE_ERROR, parts[1]);
        } else if (!parts[2].matches("\\d{8}")) {
            // Invalid date format
            printValidationFailure(fileName, ValidationMessages.ErrorMessage.DATE_FORMAT_ERROR, fileName);
        } else if (!parts[3].matches("\\d{2}\\.csv")) {
            // Invalid sequence number or file format
            if (!parts[3].endsWith(".csv")) {
                printValidationFailure(fileName, ValidationMessages.ErrorMessage.FILE_FORMAT_INVALID, parts[3].substring(parts[3].indexOf('.') + 1));
            } else {
                printValidationFailure(fileName, ValidationMessages.ErrorMessage.SEQUENCE_NUMBER_ERROR, parts[3].substring(0, 2));
            }
        }
    }

    private static boolean isValidDate(String dateString) {
        DATE_FORMAT.setLenient(false);
        try {
            Date date = DATE_FORMAT.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static void printValidationFailure(String fileName, ValidationMessages.ErrorMessage errorMessage, String... args) {
        logger.error("File '" + fileName + "' failed validation.");
        logger.error(String.format(errorMessage.getMessage(), (Object[]) args));
    }
}
