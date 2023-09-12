package org.jpm;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameValidator {
    public static boolean validateFileName(String fileLocation) {
        String fileName = getFileNameFromLocation(fileLocation);

        // Define the regular expression pattern for valid file names
        String pattern = "^Test_[ABC]_\\d{8}_\\d{2}\\.csv$";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(fileName);

        if (matcher.matches()) {

            String[] parts = fileName.split("_");
            String portfolioCode = parts[1];
            String dateString = parts[2];
            String sequenceNumber = parts[3].split("\\.")[0];

            // Validate date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            dateFormat.setLenient(false);
            try {
                Date date = dateFormat.parse(dateString);
            } catch (Exception e) {
                System.out.println("File '" + fileName + "' failed validation.");
                System.out.println("Valuation Date is not a valid date format 'ddmmyyyy'.");
                return false;
            }

            // File name is valid
            System.out.println("File '" + fileName + "' passed validation.");
            return true;
        } else {
            // File name is invalid, provide details about the failure
            validateAndPrintFailure(fileName);
            return false;
        }
    }

    private static String getFileNameFromLocation(String fileLocation) {
        File file = new File(fileLocation);
        return file.getName();
    }

    private static void validateAndPrintFailure(String fileName) {
        String[] parts = fileName.split("_");
        if (parts.length != 4){
            System.out.println("File '" + fileName + "' failed validation.");
            System.out.println("File format should be Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv");
        } else if (!parts[0].equals("Test")) {
            // Invalid prefix
            System.out.println("File '" + fileName + "' failed validation.");
            System.out.println("Prefix for the file should be 'Test' found '" + parts[0] + "'.");
        } else if (!parts[1].matches("[ABC]")) {
            // Invalid portfolio code
            System.out.println("File '" + fileName + "' failed validation.");
            System.out.println("PortfolioCode should be A/B/C found " + parts[1] + ".");
        } else if (!parts[2].matches("\\d{8}")) {
            // Invalid date format
            System.out.println("File '" + fileName + "' failed validation.");
            System.out.println("Valuation Date is not a valid date format 'ddmmyyyy'.");
        } else if (!parts[3].matches("\\d{2}\\.csv")) {
            // Invalid sequence number or file format
            System.out.println("File '" + fileName + "' failed validation.");
            if (!parts[3].endsWith(".csv")) {
                System.out.println("Invalid File format. Expected 'csv' found '" + parts[3].substring(parts[3].indexOf('.') + 1) + "'");
            } else {
                System.out.println("Invalid sequence number '" + parts[3].substring(0, 2) + "'");
            }
        }
    }
}
