/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author BikchentaevAA
 */
public class TestAutoCrop {
    
    private static Logger log = Logger.getLogger(TestAutoCrop.class.getName());

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {

        try {
            BufferedImage cropped = autoCrop(ImageIO.read(new File("123.png")), 130);//getCroppedImage();
            ImageIO.write(cropped, "png", new File("1234.png"));
        } catch (IOException ex) {
            Logger.getLogger(TestAutoCrop.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static BufferedImage getCroppedImage() throws IOException {
        BufferedImage source = ImageIO.read(new File("124.png"));

        boolean flag = false;
        int upperBorder = -1;
        do {
            upperBorder++;
            for (int c1 = 0; c1 < source.getWidth(); c1++) {
                if (source.getRGB(c1, upperBorder) != Color.black.getRGB()) {
                    flag = true;
                    break;
                }
            }

            if (upperBorder >= source.getHeight()) {
                flag = true;
            }
        } while (!flag);

        BufferedImage destination = new BufferedImage(source.getWidth(), source.getHeight() - upperBorder, BufferedImage.TYPE_INT_ARGB);
        destination.getGraphics().drawImage(source, 0, upperBorder * -1, null);

        return destination;
    }

    public static BufferedImage autoCrop(final BufferedImage image, final int fuzziness) throws IOException {
        final Color color = new Color(image.getRGB(0, 0));
        boolean stop = false;
        int cropTop = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (!ColorUtils.match(color, image.getRGB(x, y), fuzziness)) {
                    stop = true;
                    break;
                }
            }
            if (stop) {
                if (y > 0) {
                    cropTop = y - 1;
                }
                break;
            }
        }

        log.finest("Cropping top " + cropTop + " rows");

        stop = false;
        int cropBot = image.getHeight();
        for (int y = (image.getHeight() - 1); y >= 0; y--) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (!ColorUtils.match(color, image.getRGB(x, y), fuzziness)) {
                    stop = true;
                    break;
                }
            }
            if (stop) {
                if (y < image.getHeight()) {
                    cropBot = y + 1;
                }
                break;
            }
        }

        log.finest("Cropping bottom " + (image.getHeight() - cropBot) + " rows");

        stop = false;
        int cropLeft = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (!ColorUtils.match(color, image.getRGB(x, y), fuzziness)) {
                    stop = true;
                    break;
                }
            }
            if (stop) {
                if (x > 0) {
                    cropLeft = x - 1;
                }
                break;
            }
        }

        log.finest("Cropping left " + cropLeft + " rows");

        stop = false;
        int cropRight = 0;
        for (int x = (image.getWidth() - 1); x >= 0; x--) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (!ColorUtils.match(color, image.getRGB(x, y), fuzziness)) {
                    stop = true;
                    break;
                }
            }
            if (stop) {
                if (x < image.getWidth()) {
                    cropRight = x + 1;
                }
                break;
            }
        }

        log.finest("Cropping right " + (image.getWidth() - cropRight) + " rows");

        final BufferedImage cropped = image.getSubimage(cropLeft, cropTop, cropRight - cropLeft, cropBot - cropTop);
        return cropped;
    }

}
