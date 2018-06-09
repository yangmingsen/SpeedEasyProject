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
            nbi = new BufferedImage(w, h, image.getType());
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    if(image.getRGB(x, y)== 0 )
                    {
                        image.setRGB(x, y, -1);
                    }
                    nbi.setRGB(x, y,image.getRGB(x, y));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return nbi;
    }
}
