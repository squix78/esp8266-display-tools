package ch.squix.esp8266.fontconverter.rest;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;


public class FontRepository {

    public static void registerResourceFonts() throws URISyntaxException {
        List<File> fontNames = new ArrayList<>();

        File dir = new File(FontRepository.class.getClassLoader().getResource("apache").getFile());
        parseFontNames(fontNames, dir);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (File file : fontNames) {
            System.out.println(file.getName());
            try {
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, file));
            } catch (IOException | FontFormatException e) {
                // Handle exception
            }
        }
    }

    public static void parseFontNames(List<File> fontFiles, File folder) throws URISyntaxException {
        if (!folder.exists() || !folder.isDirectory()) {
            return;
        }

        for (File nextFile : folder.listFiles()) {
            if (nextFile.isDirectory()) {
                parseFontNames(fontFiles, nextFile);
            } else if (nextFile.getName().matches(".*ttf")) {
                fontFiles.add(nextFile);

            }
        }


    }


    public static void main(String[] args) throws URISyntaxException, IOException, FontFormatException {
        FontRepository.registerResourceFonts();
    }

}
