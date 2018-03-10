package ch.squix.esp8266.fontconverter.rest;

import java.awt.*;
import java.io.IOException;
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

public class RestApplication extends Application {

    @Override
    public Restlet createInboundRoot() {
        try {
            FontRepository.registerResourceFonts();
        } catch (URISyntaxException e) {
            e.printStackTrace();
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
