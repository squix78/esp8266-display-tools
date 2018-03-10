package ch.squix.esp8266.fontconverter.rest.fontfamily;

import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.squix.esp8266.fontconverter.rest.font.FontResource;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FontFamilyResource extends ServerResource {

    private static Logger logger = LoggerFactory.getLogger(FontFamilyResource.class);

    @Get(value = "json")
    public List<FontFamilyDto> execute() throws FontFormatException, IOException {
        logger.info("Load all font family names");
        List<FontFamilyDto> fonts = new ArrayList<>();
        GraphicsEnvironment graphicEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();

        for (String fontFamilyName : graphicEnvironment.getAvailableFontFamilyNames()) {
            FontFamilyDto dto = new FontFamilyDto();
            dto.setName("XX" + fontFamilyName);
            fonts.add(dto);
        }
        return fonts;
    }

}
