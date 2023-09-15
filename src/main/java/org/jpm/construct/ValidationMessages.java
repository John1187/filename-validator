package org.jpm.construct;

public class ValidationMessages {

    public enum ErrorMessage {
        FILE_FORMAT_ERROR("File format should be Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv"),
        PREFIX_ERROR("Prefix for the file should be 'Test' found '%s'."),
        PORTFOLIO_CODE_ERROR("PortfolioCode should be A/B/C found %s."),
        DATE_FORMAT_ERROR("Valuation Date is not a valid date format 'ddmmyyyy'."),
        FILE_FORMAT_INVALID("Invalid File format. Expected 'csv' found '%s'"),
        SEQUENCE_NUMBER_ERROR("Invalid sequence number '%s'");

        private final String message;

        ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}