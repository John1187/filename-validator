# File Name Validator
    
## Overview
    
This Java program is designed to validate file names based on specific criteria. It checks if a given file name follows the expected format and provides information on whether the file passed or failed validation.

### Assumptions

- The file name format should be : `Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv`
- The portfolio code is case-sensitive and can be one of: A, B, C.
- The valuation date should follow the format 'ddmmyyyy'.
- The sequence number should be between 01 and 99 (inclusive).

### Scenarios

- The program will print whether the file passed or failed validation.
- If the file fails validation, it will provide details about the failure, such as incorrect portfolio code, date format, or an invalid sequence number.

## Usage

### Prerequisites
    
- Java Development Kit (JDK 17) installed on your machine.
- Gradle installed on your machine.
    
### 1. Clone the Repository
    
Clone this repository to your local machine using the following command:
    

    git clone https://github.com/John1187/filename-validator.git
    
Switch to branch : Release/R2309
    
### 2. Compilation and Execution

To compile and execute the program, use the following Gradle commands:

#### Windows

    cd filename-validator
    gradlew.bat run --args="<file_location>"

Replace <file_location> with the location of the file you want to validate. Example:

    gradlew.bat run --args="C:\jpmchase\Test_A_07121987_01.csv"

#### macOS and Linux

    cd filename-validator
    ./gradlew run --args="<file_location>"

Replace <file_location> with the location of the file you want to validate. Example:

    ./gradlew run --args="/jpmchase/Test_A_07121987_01.csv"


### 3. Testing

To run the JUnit tests, use the following Gradle command:

    MacOS 
            ./gradlew test

    Windows

            gradlew.bat





