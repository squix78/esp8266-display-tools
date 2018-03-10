package ch.squix.esp8266.fontconverter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static Logger logger = LoggerFactory.getLogger(FontRepository.class);

    public static void registerResourceFonts() throws URISyntaxException {
        List<File> fontNames = new ArrayList<>();

        File dir = new File(FontRepository.class.getClassLoader().getResource("apache").getFile());
        logger.info("Font directory: {}", dir);
        parseFontNames(fontNames, dir);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (File file : fontNames) {
            logger.info("Checking font file {}", file.getName());
            try {
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, file));
            } catch (IOException | FontFormatException e) {
                logger.error("Registering fonts failed", e);
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
