package ch.squix.esp8266.fontconverter.rest;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;


import ch.squix.esp8266.fontconverter.rest.fontarray.BinaryFontResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import ch.squix.esp8266.fontconverter.rest.font.FontResource;
import ch.squix.esp8266.fontconverter.rest.font.preview.FontPreviewResource;
import ch.squix.esp8266.fontconverter.rest.fontarray.FontArrayResource;
import ch.squix.esp8266.fontconverter.rest.fontfamily.FontFamilyResource;
import ch.squix.esp8266.fontconverter.rest.ping.PingResource;
import ch.squix.esp8266.fontconverter.rest.time.TimeResource;
import ch.squix.esp8266.fontconverter.rest.xbm.preview.XbmPreviewResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestApplication extends Application {


    Logger logger = LoggerFactory.getLogger(RestApplication.class);

    @Override
    public Restlet createInboundRoot() {
        try {

            FontRepository.registerResourceFonts();
            InputStream is = RestApplication.class.getResourceAsStream("/meteocons/Meteocons.ttf");
            Font fileFont = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fileFont);
        } catch (Exception e) {
            logger.error("Failed to register fonts", e);
        }
        // Create a router Restlet that routes each call to a
        // new instance of HelloWorldResource.
        Router router = new Router(getContext());
        router.attach("/ping", PingResource.class);
        router.attach("/fontFamilies", FontFamilyResource.class);
        router.attach("/fonts", FontResource.class);
        router.attach("/fontArray", FontArrayResource.class);
        router.attach("/fontPreview/{fontName}/{fontStyle}/{fontSize}", FontPreviewResource.class);
        router.attach("/binaryFont/{fontName}/{fontStyle}/{fontSize}", BinaryFontResource.class);
        router.attach("/xbmPreview", XbmPreviewResource.class);
        router.attach("/time", TimeResource.class);

        return router;
    }

}
