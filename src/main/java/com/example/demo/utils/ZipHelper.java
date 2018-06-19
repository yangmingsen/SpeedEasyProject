package com.example.demo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipHelper {
    /**
     * 解压文件到指定目录
     * 解压后的文件名，和之前一致
     * @param zipFile   待解压的zip文件
     * @param descDir   指定目录
     */
    public static String unZipFiles(File zipFile, String descDir) throws IOException {

        ZipFile zip = new ZipFile(zipFile,Charset.forName("UTF-8"));//解决中文文件夹乱码

        String mainDirec = descDir+DateHelpler.getDateNow().replaceAll(" ","")+System.currentTimeMillis();

        File pathFile = new File(mainDirec);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();//获取一个文件对象

            String zipEntryName = "";
            if(entry.getName().lastIndexOf(File.separator) == -1) {//如果当前压缩包下就是文件
                zipEntryName = "/"+entry.getName();
            } else {
                zipEntryName = entry.getName().substring(entry.getName().lastIndexOf(File.separator));//获取压缩文件名字
            }

            System.out.println("outPath = "+mainDirec+zipEntryName);


            InputStream in = zip.getInputStream(entry);//将zip文件推入输入流中
            String outPath = (mainDirec+zipEntryName);

            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }


            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }

        return mainDirec;
    }

}
