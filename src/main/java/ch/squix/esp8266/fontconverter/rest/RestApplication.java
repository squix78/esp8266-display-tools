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
            "/meteocons/Meteocons.tff",
            "/apache/redressed/Redressed-Regular.tff",
            "/apache/opensanscondensed/OpenSansCondensed-Bold.tff",
            "/apache/opensanscondensed/OpenSansCondensed-LightItalic.tff",
            "/apache/opensanscondensed/OpenSansCondensed-Light.tff",
            "/apache/tinos/Tinos-Regular.tff",
            "/apache/tinos/Tinos-Italic.tff",
            "/apache/tinos/Tinos-Bold.tff",
            "/apache/tinos/Tinos-BoldItalic.tff",
            "/apache/rochester/Rochester-Regular.tff",
            "/apache/jsmathcmsy10/jsMath-cmsy10.tff",
            "/apache/smokum/Smokum-Regular.tff",
            "/apache/calligraffitti/Calligraffitti-Regular.tff",
            "/apache/robotoslab/RobotoSlab-Thin.tff",
            "/apache/robotoslab/RobotoSlab-Bold.tff",
            "/apache/robotoslab/RobotoSlab-Light.tff",
            "/apache/robotoslab/RobotoSlab-Regular.tff",
            "/apache/ultra/Ultra-Regular.tff",
            "/apache/syncopate/Syncopate-Bold.tff",
            "/apache/syncopate/Syncopate-Regular.tff",
            "/apache/chewy/Chewy-Regular.tff",
            "/apache/homemadeapple/HomemadeApple-Regular.tff",
            "/apache/arimo/Arimo-Bold.tff",
            "/apache/arimo/Arimo-Italic.tff",
            "/apache/arimo/Arimo-Regular.tff",
            "/apache/arimo/Arimo-BoldItalic.tff",
            "/apache/jsmathcmr10/jsMath-cmr10.tff",
            "/apache/maidenorange/MaidenOrange-Regular.tff",
            "/apache/jsmathcmti10/jsMath-cmti10.tff",
            "/apache/nokora/Nokora-Regular.tff",
            "/apache/nokora/Nokora-Bold.tff",
            "/apache/unkempt/Unkempt-Bold.tff",
            "/apache/unkempt/Unkempt-Regular.tff",
            "/apache/montez/Montez-Regular.tff",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-Italic.tff",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-ExtraBoldItalic.tff",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-LightItalic.tff",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-Regular.tff",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-ExtraBold.tff",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-BoldItalic.tff",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-Light.tff",
            "/apache/opensanshebrewcondensed/OpenSansHebrewCondensed-Bold.tff",
            "/apache/mountainsofchristmas/MountainsofChristmas-Regular.tff",
            "/apache/mountainsofchristmas/MountainsofChristmas-Bold.tff",
            "/apache/luckiestguy/LuckiestGuy-Regular.tff",
            "/apache/justanotherhand/JustAnotherHand-Regular.tff",
            "/apache/yellowtail/Yellowtail-Regular.tff",
            "/apache/robotocondensed/RobotoCondensed-Bold.tff",
            "/apache/robotocondensed/RobotoCondensed-Light.tff",
            "/apache/robotocondensed/RobotoCondensed-Italic.tff",
            "/apache/robotocondensed/RobotoCondensed-BoldItalic.tff",
            "/apache/robotocondensed/RobotoCondensed-LightItalic.tff",
            "/apache/robotocondensed/RobotoCondensed-Regular.tff",
            "/apache/specialelite/SpecialElite-Regular.tff",
            "/apache/jsmathcmbx10/jsMath-cmbx10.tff",
            "/apache/jsmathcmmi10/jsMath-cmmi10.tff",
            "/apache/aclonica/Aclonica-Regular.tff",
            "/apache/rancho/Rancho-Regular.tff",
            "/apache/sunshiney/Sunshiney-Regular.tff",
            "/apache/walterturncoat/WalterTurncoat-Regular.tff",
            "/apache/cousine/Cousine-BoldItalic.tff",
            "/apache/cousine/Cousine-Bold.tff",
            "/apache/cousine/Cousine-Italic.tff",
            "/apache/cousine/Cousine-Regular.tff",
            "/apache/permanentmarker/PermanentMarker-Regular.tff",
            "/apache/jsmathcmex10/jsMath-cmex10.tff",
            "/apache/creepstercaps/CreepsterCaps-Regular.tff",
            "/apache/cherrycreamsoda/CherryCreamSoda-Regular.tff",
            "/apache/opensanshebrew/OpenSansHebrew-ExtraBoldItalic.tff",
            "/apache/opensanshebrew/OpenSansHebrew-BoldItalic.tff",
            "/apache/opensanshebrew/OpenSansHebrew-Regular.tff",
            "/apache/opensanshebrew/OpenSansHebrew-ExtraBold.tff",
            "/apache/opensanshebrew/OpenSansHebrew-Light.tff",
            "/apache/opensanshebrew/OpenSansHebrew-LightItalic.tff",
            "/apache/opensanshebrew/OpenSansHebrew-Bold.tff",
            "/apache/opensanshebrew/OpenSansHebrew-Italic.tff",
            "/apache/robotomono/RobotoMono-Medium.tff",
            "/apache/robotomono/RobotoMono-Regular.tff",
            "/apache/robotomono/RobotoMono-LightItalic.tff",
            "/apache/robotomono/RobotoMono-BoldItalic.tff",
            "/apache/robotomono/RobotoMono-ThinItalic.tff",
            "/apache/robotomono/RobotoMono-Light.tff",
            "/apache/robotomono/RobotoMono-MediumItalic.tff",
            "/apache/robotomono/RobotoMono-Bold.tff",
            "/apache/robotomono/RobotoMono-Italic.tff",
            "/apache/robotomono/RobotoMono-Thin.tff",
            "/apache/satisfy/Satisfy-Regular.tff",
            "/apache/rocksalt/RockSalt-Regular.tff",
            "/apache/roboto/Roboto-Medium.tff",
            "/apache/roboto/Roboto-Light.tff",
            "/apache/roboto/Roboto-Regular.tff",
            "/apache/roboto/Roboto-MediumItalic.tff",
            "/apache/roboto/Roboto-ThinItalic.tff",
            "/apache/roboto/Roboto-BoldItalic.tff",
            "/apache/roboto/Roboto-LightItalic.tff",
            "/apache/roboto/Roboto-Italic.tff",
            "/apache/roboto/Roboto-BlackItalic.tff",
            "/apache/roboto/Roboto-Bold.tff",
            "/apache/roboto/Roboto-Thin.tff",
            "/apache/roboto/Roboto-Black.tff",
            "/apache/irishgrover/IrishGrover-Regular.tff",
            "/apache/comingsoon/ComingSoon-Regular.tff",
            "/apache/craftygirls/CraftyGirls-Regular.tff",
            "/apache/schoolbell/Schoolbell-Regular.tff",
            "/apache/crushed/Crushed-Regular.tff",
            "/apache/slackey/Slackey-Regular.tff",
            "/apache/kranky/Kranky-Regular.tff",
            "/apache/opensans/OpenSans-SemiBold.tff",
            "/apache/opensans/OpenSans-Light.tff",
            "/apache/opensans/OpenSans-Italic.tff",
            "/apache/opensans/OpenSans-ExtraBold.tff",
            "/apache/opensans/OpenSans-LightItalic.tff",
            "/apache/opensans/OpenSans-Bold.tff",
            "/apache/opensans/OpenSans-SemiBoldItalic.tff",
            "/apache/opensans/OpenSans-ExtraBoldItalic.tff",
            "/apache/opensans/OpenSans-Regular.tff",
            "/apache/opensans/OpenSans-BoldItalic.tff",
            "/apache/fontdinerswanky/FontdinerSwanky-Regular.tff"
    };

    @Override
    public Restlet createInboundRoot() {

            for (String font : fonts) {
                try {
                    registerFont(font);
                } catch (Exception e) {
                    logger.error("Failed to register fonts", e);
                }
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

    public void registerFont(String fontPath) throws java.awt.FontFormatException, java.io.IOException {
        InputStream is = RestApplication.class.getResourceAsStream(fontPath);
        Font fileFont = Font.createFont(Font.TRUETYPE_FONT, is);
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(fileFont);
    }

}
