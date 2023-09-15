package org.jpm.util;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FileOperationsTest {

    @Test
    public void testFileExistsWithValidFile() {
        FileOperations fileOperations = new FileOperations();
        String path = "C:\\jpmc\\validfile.csv";

        FileOperations fileOperationsSpy = Mockito.spy(fileOperations);
        File mockFile = Mockito.mock(File.class);
        when(fileOperationsSpy.createFile(path)).thenReturn(mockFile);

        when(mockFile.exists()).thenReturn(true);
        when(mockFile.isFile()).thenReturn(true);

        assertTrue(fileOperationsSpy.fileExists(path));
    }

    @Test
    public void testFileExistsWithDirectory() {
        FileOperations fileOperations = new FileOperations();
        String path = "C:\\jpmc\\";

        assertFalse(fileOperations.fileExists(path));
    }

    @Test
    public void testFileExistsWithNonExistentFile() {
        FileOperations fileOperations = new FileOperations();
        String path = "C:\\jpmc\\file.csv";
        assertFalse(fileOperations.fileExists(path));
    }

    @Test
    public void testFileExistsWithNullPath() {
        FileOperations fileOperations = new FileOperations();
        String path = null;
        assertFalse(fileOperations.fileExists(path));
    }
}
