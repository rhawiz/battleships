package net.thoughtmachine.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by rawan on 13/03/2016.
 */
public class FileReader {

    private final String filePath;
    private String text;

    public FileReader(String filePath) {
        this.filePath = filePath;
        text = "";
    }

    public String read() {
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                text += line + "\n";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text;
    }
}
