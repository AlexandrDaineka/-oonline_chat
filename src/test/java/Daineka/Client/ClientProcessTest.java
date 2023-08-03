package Daineka.Client;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ClientProcessTest {

    @Test
    void testGetValueString() {
        String input = "Test Input";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        assertEquals(input, ClientProcess.getValueString());
    }
}
