/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ayrat
 */
public class Cropper {
    public void cropImage(){
        try {
            File file = new File("src\\sample.jpg");
            BufferedImage img;

            img = ImageIO.read(file);
            System.out.println("Size of image(width - height):" + img.getWidth() + " - " + img.getHeight());
            img = img.getSubimage(1050, 1450, 500, 500);
            ImageIO.write(img, "png", new File("src\\result.jpg"));
//            int w = img.getWidth();
//            int h = img.getHeight();
//            if (w > h) {
//            }//turn image to 90 right
//            for (double pos = h * 0.45; pos >= 0; pos = pos - h * 0.45) {
//                BufferedImage bi = img.getSubimage(0, (int) Math.rint(pos), w, (int) Math.rint(h * 0.55));
////                ImageIO.write(bi, "jpg", new File("src\\result" + pos + ".jpg"));
//
//                try {
//                    label.setText(searchQRCode(bi));
//                    return true;
//                } catch (NotFoundException nocodeEx) {
//
//                }
//            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
