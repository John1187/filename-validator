import org.jpm.FileNameValidator;
import org.jpm.util.FileOperations;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FileNameValidatorTest {

    @Test
    public void testInvalidFileLocation() {

        FileOperations fileOperations = Mockito.mock(FileOperations.class);
        when(fileOperations.fileExists(any())).thenReturn(false);

        FileNameValidator validator = new FileNameValidator(fileOperations);
        assertFalse(validator.validateFileName("C:\\nonexistent\\Test_A_01012022_01.csv"));
    }

    @Test
    public void testValidFileName() {

        FileOperations fileOperations = Mockito.mock(FileOperations.class);
        when(fileOperations.fileExists(any())).thenReturn(true);

        FileNameValidator validator = new FileNameValidator(fileOperations);

        assertTrue(validator.validateFileName("C:\\jpmc\\Test_A_07121987_01.csv"));
        assertTrue(validator.validateFileName("C:\\jpmc\\Test_B_12122021_99.csv"));
        assertTrue(validator.validateFileName("C:\\jpmc\\Test_C_07052023_42.csv"));
    }

    @Test
    public void testInvalidFileName() {

        FileOperations fileOperations = Mockito.mock(FileOperations.class);
        when(fileOperations.fileExists(any())).thenReturn(true);

        FileNameValidator validator = new FileNameValidator(fileOperations);

        assertFalse(validator.validateFileName("C:\\jpmc\\Test_E_07121987_03.csv"));
        assertFalse(validator.validateFileName("C:\\jpmc\\Test_A_07121987.txt"));
        assertFalse(validator.validateFileName("C:\\jpmc\\Test_A_07121987_04.txt"));
        assertFalse(validator.validateFileName("C:\\jpmc\\InvalidPrefix_A_01012022_01.csv"));
        assertFalse(validator.validateFileName("C:\\jpmc\\Test_X_01012022_01.csv"));
        assertFalse(validator.validateFileName("C:\\jpmc\\Test_A_12345_01.csv"));
        assertFalse(validator.validateFileName("C:\\jpmc\\Test_A_01012022_123.csv"));
        assertFalse(validator.validateFileName("C:\\jpmc\\Test_A_01012022.csv"));
        assertFalse(validator.validateFileName("C:\\jpmc\\Test_A_0101202201.csv"));
    }

    @Test
    public void testInvalidDate() {

        FileOperations fileOperations = Mockito.mock(FileOperations.class);
        when(fileOperations.fileExists(any())).thenReturn(true);

        FileNameValidator validator = new FileNameValidator(fileOperations);

        assertFalse(validator.validateFileName("C:\\jpmc\\Test_A_31132022_01.csv"));
        assertFalse(validator.validateFileName("C:\\jpmc\\Test_B_02292020_01.csv")); // Leap year
    }
}