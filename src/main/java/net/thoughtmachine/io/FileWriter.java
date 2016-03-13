package net.thoughtmachine.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author rhawiz.
 */
public class FileWriter {

    String filePath;
    String text;

    public FileWriter(String filePath, String text) {
        this.filePath = filePath;
        this.text = text;
    }


    /**
     * Write the input text to the file. Will attempt to create the file if it doesn't exist.
     *
     * @throws IOException
     */
    public void write() throws IOException {
        String fullFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + filePath;
        File f = new File(fullFilePath);
        System.out.println(fullFilePath);

        if (!f.exists()) {
            f.createNewFile();
        }

        PrintWriter writer = new PrintWriter(f, "UTF-8");
        writer.println(text);
        writer.close();
    }
}
