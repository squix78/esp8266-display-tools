package ch.squix.esp8266.fontconverter.rest.font.preview;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.imageio.ImageIO;

import org.restlet.data.MediaType;
import org.restlet.representation.ByteArrayRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class FontPreviewResource extends ServerResource {

    @Get("image/png")
    public void getPreview() throws IOException {
        String fontFamilyInput = URLDecoder.decode(
                (String) this.getRequestAttributes().get("fontName"), "utf8");
        String previewDisplayParam = URLDecoder.decode(
                (String) this.getRequestAttributes().get("previewDisplay"));
        System.out.println("preview Display Param: " + previewDisplayParam);
        PreviewDisplay previewDisplay = PreviewDisplay.valueOf(previewDisplayParam);
        if (previewDisplay == null) {
            previewDisplay = PreviewDisplay.OLED96;
        }
        System.out.println("preview Display: " + previewDisplay);
        String fontStyleInput = (String) this.getRequestAttributes().get("fontStyle");
        Integer fontStyle = Integer.valueOf(fontStyleInput);
        String fontSizeInput = (String) this.getRequestAttributes().get("fontSize");
        Integer fontSize = Integer.valueOf(fontSizeInput) * previewDisplay.getZoomFactor();
        BufferedImage oldImage = null;

        oldImage = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(previewDisplay.getFilename()));
        Font font = new Font(fontFamilyInput, fontStyle, fontSize);

        BufferedImage newImage = new BufferedImage(oldImage.getWidth(), oldImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(oldImage, 0, 0, null);
        graphics.setFont(font);
        Rectangle clipRect = previewDisplay.getClipRect();
        graphics.setClip(clipRect.x, clipRect.y, clipRect.width, clipRect.height);

        drawString(graphics, "ABC abc 123 $€°. The quick brown fox jumps over the lazy dog.", clipRect.x,
                clipRect.y + graphics.getFontMetrics().getAscent(), clipRect.width);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(newImage, "png", baos);
        byte[] bytes = baos.toByteArray();
        ByteArrayRepresentation bar = new ByteArrayRepresentation(bytes, MediaType.IMAGE_PNG);
        getResponse().setEntity(bar);
    }

    public void drawString(Graphics g, String s, int x, int y, int width) {
        // FontMetrics gives us information about the width,
        // height, etc. of the current Graphics object's Font.
        FontMetrics fm = g.getFontMetrics();

        int lineHeight = fm.getHeight();

        int curX = x;
        int curY = y;

        String[] words = s.split(" ");

        for (String word : words) {
            // Find out thw width of the word.
            int wordWidth = fm.stringWidth(word + " ");

            // If text exceeds the width, then move to next line.
            if (curX + wordWidth >= x + width) {
                curY += lineHeight;
                curX = x;
            }

            g.drawString(word, curX, curY);

            // Move over to the right for next word.
            curX += wordWidth;
        }
    }
}
