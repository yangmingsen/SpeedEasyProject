package com.example.demo.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BinaryImg {
    public static BufferedImage getBinaryImg(String imgPath) {
        BufferedImage nbi = null;
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
            int h = image.getHeight();
            int w = image.getWidth();
            nbi = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY);
            Color bColor = new Color(10, 10, 10, 255);//黑色
            Color wColor = new Color(255, 255, 255, 255);//白色
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    Color color = new Color(image.getRGB(x, y));
                    if (color.getRed() > 0 && color.getBlue() > 0 && color.getGreen() > 0 && (color.getRed() < 150 || color.getBlue() < 150 || color.getGreen() < 150)) {
                        color = bColor;
                    } else {
                        color = wColor;
                    }
                    nbi.setRGB(x, y, color.getRGB());
                }
            }

            //ImageIO.write(nbi, "png", new File("/home/yms/Documents/OCR/1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nbi;
    }
}
