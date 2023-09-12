import org.jpm.FileNameValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileNameValidatorTest {

    @Test
    public void testValidFileName() {
        assertTrue(FileNameValidator.validateFileName("C:\\jpmc\\Test_A_07121987_01.csv"));
        assertTrue(FileNameValidator.validateFileName("/jpmc/Test_B_25062021_12.csv"));
    }

    @Test
    public void testInvalidFileName() {
        assertFalse(FileNameValidator.validateFileName("C:\\jpmc\\Test_E_07121987_03.csv"));
        assertFalse(FileNameValidator.validateFileName("/jpmc/Hello_A_07121987_05.csv"));
        assertFalse(FileNameValidator.validateFileName("C:\\jpmc\\Test_A_07121987.txt"));
        assertFalse(FileNameValidator.validateFileName("/jpmc/Test.txt"));
        assertFalse(FileNameValidator.validateFileName("C:\\jpmc\\Test_A_07121987_04.txt"));
    }
}
