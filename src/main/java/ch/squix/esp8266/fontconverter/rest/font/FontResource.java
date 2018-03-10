package ch.squix.esp8266.fontconverter.rest.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.squix.esp8266.fontconverter.rest.*;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FontResource extends ServerResource {

    private static Logger logger = LoggerFactory.getLogger(FontResource.class);

    @Get(value = "json")
    public List<FontDto> execute() throws FontFormatException, IOException {

        String fontFamily = (String) this.getRequestAttributes().get("fontFamily");
        logger.info("loading font types for {}", fontFamily);
        List<FontDto> fonts = new ArrayList<>();
        GraphicsEnvironment graphicEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (Font font : graphicEnvironment.getAllFonts()) {
            if (fontFamily == null || fontFamily.equals(font.getFamily())) {
                FontDto dto = new FontDto();
                dto.setName(font.getFontName());
                dto.setFontFamily(font.getFamily());
                dto.setPlain(font.isPlain());
                dto.setItalic(font.isItalic());
                dto.setBold(font.isBold());
                fonts.add(dto);
            }
        }
        return fonts;
    }

}
