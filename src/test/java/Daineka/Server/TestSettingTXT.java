package Daineka.Server;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestSettingTXT {
    File file = new File("settings.txt");
    SettingsTXT parserSettingTXT = new SettingsTXT(file);

    @Test
    void notGetPort() throws IOException {
        int result = (int)parserSettingTXT.getData("port");
        assertNotEquals(486837, result);
    }
    @Test
    void getPort() throws IOException {
        int result = (int)parserSettingTXT.getData("port");
        assertEquals(1234, result);
    }
}
