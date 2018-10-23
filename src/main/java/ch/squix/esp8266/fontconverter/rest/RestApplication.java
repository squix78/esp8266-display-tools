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

    private final static String fonts [] = {
            "/meteocons/Meteocons.ttf",
            "/meteocons/Moonphases.ttf",
            "/apache/redressed/Redressed-Regular.ttf",
            "/apache/opensanscondensed/OpenSansCondensed-Bold.ttf",
            "/apache/opensanscondensed/OpenSansCondensed-LightItalic.ttf",
            "/apache/opensanscondensed/OpenSansCondensed-Light.ttf",
            "/apache/tinos/Tinos-Regular.ttf",
            "/apache/tinos/Tinos-Italic.ttf",
            "/apache/tinos/Tinos-Bold.ttf",
            "/apache/tinos/Tinos-BoldItalic.ttf",
            "/apache/rochester/Rochester-Regular.ttf",
            "/apache/jsmathcmsy10/jsMath-cmsy10.ttf",
            "/apache/smokum/Smokum-Regular.ttf",
            "/apache/calligraffitti/Calligraffitti-Regular.ttf",
            "/apache/robotoslab/RobotoSlab-Thin.ttf",
            "/apache/robotoslab/RobotoSlab-Bold.ttf",
            "/apache/robotoslab/RobotoSlab-Light.ttf",
            "/apache/robotoslab/RobotoSlab-Regular.ttf",
            "/apache/ultra/Ultra-Regular.ttf",
            "/apache/syncopate/Syncopate-Bold.ttf",
            "/apache/syncopate/Syncopate-Regular.ttf",
            "/apache/chewy/Chewy-Regular.ttf",
            "/apache/homemadeapple/HomemadeApple-Regular.ttf",
            "/apache/arimo/Arimo-Bold.ttf",
            "/apache/arimo/Arimo-Italic.ttf",
            "/apache/arimo/Arimo-Regular.ttf",
            "/apache/arimo/Arimo-BoldItalic.ttf",
            "/apache/jsmathcmr10/jsMath-cmr10.ttf",
            "/apache/maidenorange/MaidenOrange-Regular.ttf",
            "/apache/jsmathcmti10/jsMath-cmti10.ttf",
            "/apache/nokora/Nokora-Regular.ttf",
            "/apache/nokora/Nokora-Bold.ttf",
            "/apache/unkempt/Unkempt-Bold.ttf",
            "/apache/unkempt/Unkempt-Regular.ttf",
            "/apache/montez/Montez-Regular.ttf",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-Italic.ttf",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-ExtraBoldItalic.ttf",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-LightItalic.ttf",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-Regular.ttf",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-ExtraBold.ttf",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-BoldItalic.ttf",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-Light.ttf",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-Bold.ttf",
            "/apache/mountainsofchristmas/MountainsofChristmas-Regular.ttf",
            "/apache/mountainsofchristmas/MountainsofChristmas-Bold.ttf",
            "/apache/luckiestguy/LuckiestGuy-Regular.ttf",
            "/apache/justanotherhand/JustAnotherHand-Regular.ttf",
            "/apache/yellowtail/Yellowtail-Regular.ttf",
            "/apache/robotocondensed/RobotoCondensed-Bold.ttf",
            "/apache/robotocondensed/RobotoCondensed-Light.ttf",
            "/apache/robotocondensed/RobotoCondensed-Italic.ttf",
            "/apache/robotocondensed/RobotoCondensed-BoldItalic.ttf",
            "/apache/robotocondensed/RobotoCondensed-LightItalic.ttf",
            "/apache/robotocondensed/RobotoCondensed-Regular.ttf",
            "/apache/specialelite/SpecialElite-Regular.ttf",
            "/apache/jsmathcmbx10/jsMath-cmbx10.ttf",
            "/apache/jsmathcmmi10/jsMath-cmmi10.ttf",
            "/apache/aclonica/Aclonica-Regular.ttf",
            "/apache/rancho/Rancho-Regular.ttf",
            "/apache/sunshiney/Sunshiney-Regular.ttf",
            "/apache/walterturncoat/WalterTurncoat-Regular.ttf",
            "/apache/cousine/Cousine-BoldItalic.ttf",
            "/apache/cousine/Cousine-Bold.ttf",
            "/apache/cousine/Cousine-Italic.ttf",
            "/apache/cousine/Cousine-Regular.ttf",
            "/apache/permanentmarker/PermanentMarker-Regular.ttf",
            "/apache/jsmathcmex10/jsMath-cmex10.ttf",
            "/apache/creepstercaps/CreepsterCaps-Regular.ttf",
            "/apache/cherrycreamsoda/CherryCreamSoda-Regular.ttf",
            "/apache/opensanshebrew/OpenSansHebrew-ExtraBoldItalic.ttf",
            "/apache/opensanshebrew/OpenSansHebrew-BoldItalic.ttf",
            "/apache/opensanshebrew/OpenSansHebrew-Regular.ttf",
            "/apache/opensanshebrew/OpenSansHebrew-ExtraBold.ttf",
            "/apache/opensanshebrew/OpenSansHebrew-Light.ttf",
            "/apache/opensanshebrew/OpenSansHebrew-LightItalic.ttf",
            "/apache/opensanshebrew/OpenSansHebrew-Bold.ttf",
            "/apache/opensanshebrew/OpenSansHebrew-Italic.ttf",
            "/apache/robotomono/RobotoMono-Medium.ttf",
            "/apache/robotomono/RobotoMono-Regular.ttf",
            "/apache/robotomono/RobotoMono-LightItalic.ttf",
            "/apache/robotomono/RobotoMono-BoldItalic.ttf",
            "/apache/robotomono/RobotoMono-ThinItalic.ttf",
            "/apache/robotomono/RobotoMono-Light.ttf",
            "/apache/robotomono/RobotoMono-MediumItalic.ttf",
            "/apache/robotomono/RobotoMono-Bold.ttf",
            "/apache/robotomono/RobotoMono-Italic.ttf",
            "/apache/robotomono/RobotoMono-Thin.ttf",
            "/apache/satisfy/Satisfy-Regular.ttf",
            "/apache/rocksalt/RockSalt-Regular.ttf",
            "/apache/roboto/Roboto-Medium.ttf",
            "/apache/roboto/Roboto-Light.ttf",
            "/apache/roboto/Roboto-Regular.ttf",
            "/apache/roboto/Roboto-MediumItalic.ttf",
            "/apache/roboto/Roboto-ThinItalic.ttf",
            "/apache/roboto/Roboto-BoldItalic.ttf",
            "/apache/roboto/Roboto-LightItalic.ttf",
            "/apache/roboto/Roboto-Italic.ttf",
            "/apache/roboto/Roboto-BlackItalic.ttf",
            "/apache/roboto/Roboto-Bold.ttf",
            "/apache/roboto/Roboto-Thin.ttf",
            "/apache/roboto/Roboto-Black.ttf",
            "/apache/irishgrover/IrishGrover-Regular.ttf",
            "/apache/comingsoon/ComingSoon-Regular.ttf",
            "/apache/craftygirls/CraftyGirls-Regular.ttf",
            "/apache/schoolbell/Schoolbell-Regular.ttf",
            "/apache/crushed/Crushed-Regular.ttf",
            "/apache/slackey/Slackey-Regular.ttf",
            "/apache/kranky/Kranky-Regular.ttf",
            "/apache/opensans/OpenSans-SemiBold.ttf",
            "/apache/opensans/OpenSans-Light.ttf",
            "/apache/opensans/OpenSans-Italic.ttf",
            "/apache/opensans/OpenSans-ExtraBold.ttf",
            "/apache/opensans/OpenSans-LightItalic.ttf",
            "/apache/opensans/OpenSans-Bold.ttf",
            "/apache/opensans/OpenSans-SemiBoldItalic.ttf",
            "/apache/opensans/OpenSans-ExtraBoldItalic.ttf",
            "/apache/opensans/OpenSans-Regular.ttf",
            "/apache/opensans/OpenSans-BoldItalic.ttf",
            "/apache/fontdinerswanky/FontdinerSwanky-Regular.ttf"
    };

    @Override
    public Restlet createInboundRoot() {

        for (String font : fonts) {
            registerFont(font);
        }

        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (Font font : ge.getAllFonts()) {
            logger.info("Loadded font {}", font);
        }



        // Create a router Restlet that routes each call to a
        // new instance of HelloWorldResource.
        Router router = new Router(getContext());
        router.attach("/ping", PingResource.class);
        router.attach("/fontFamilies", FontFamilyResource.class);
        router.attach("/fonts", FontResource.class);
        router.attach("/fontArray", FontArrayResource.class);
        router.attach("/fontPreview/{fontName}/{fontStyle}/{fontSize}/{previewDisplay}", FontPreviewResource.class);
        router.attach("/binaryFont/{fontName}/{fontStyle}/{fontSize}", BinaryFontResource.class);
        router.attach("/xbmPreview", XbmPreviewResource.class);
        router.attach("/time", TimeResource.class);

        return router;
    }

    public void registerFont(final String fontPath) {
        try {
            InputStream is = RestApplication.class.getResourceAsStream(fontPath);
            Font fileFont = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fileFont);

        } catch (Exception e) {
            logger.error("Failed to register fonts", e);
        }
    }

}
