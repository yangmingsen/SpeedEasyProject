package com.example.demo.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.io.File;

public class StartOCR {

    public static String getOCRText(String imgPath){

        //File imageFile = new File(path);
        ITesseract instance = new Tesseract();
        instance.setDatapath("/home/yms/Documents/SpringBoot/SpeedEasyServer/src/main/resources");
        //
        instance.setLanguage("new");

        String result = null;
        try {
            result = instance.doOCR(BinaryImg.getBinaryImg(imgPath),new Rectangle(500,77));
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        return result;

    }

}
