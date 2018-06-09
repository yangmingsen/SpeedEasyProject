package com.example.demo.web;

import com.example.demo.domain.TBILModel;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.utils.DateHelpler;
import com.example.demo.utils.OperatorExcel;
import com.example.demo.utils.StartOCR;

import com.example.demo.utils.ZipHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


@RequestMapping("/upload")
@RestController
@CrossOrigin
public class UploadFileController {

    private final Logger logger = LoggerFactory.getLogger(UploadFileController.class);

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/home/yms/Documents/SpringBoot/SpeedEasyServer/src/main/resources/upload-dir/";
    private static String ExcelFOLDER2 = "/home/yms/Documents/SpringBoot/SpeedEasyServer/src/main/resources/ResultExcel/";

    // Multiple file upload
    @PostMapping("/files")
    public ResponseData uploadFileMulti(@RequestParam("files") MultipartFile[] uploadfiles) {

        if (uploadfiles.length == 0 ) {
            return new ResponseData(ExceptionMsg.FileEmpty);
        }

        String resFileUrl = null;
        if(false) {
            //获取Session生成 Data+System.curr...+用户名.excel
        } else {
           resFileUrl = DateHelpler.getDateNow().replaceAll(" ","")+System.currentTimeMillis();
        }

        StringBuffer res = new StringBuffer();
        ArrayList towrite = new ArrayList<TBILModel>();

        System.out.println("OK! Start to OCR!");
        long start = System.currentTimeMillis();
        try {
            for(MultipartFile file: uploadfiles) {
                if(!file.isEmpty()) {
                    byte [] bytes = file.getBytes();
                    Path path = Paths.get(UPLOADED_FOLDER+resFileUrl+file.getOriginalFilename());
                    Files.write(path,bytes);

                    String tmpRes = StartOCR.getOCRText(UPLOADED_FOLDER+resFileUrl+file.getOriginalFilename());

                    String [] tmpSplit = tmpRes.split("\n");
                    towrite.add( new TBILModel(tmpSplit[0].split(":")[1], tmpSplit[1].split(":")[1]) );

                    res.append(tmpRes);
                }
            }

        } catch (IOException e) {
            return new ResponseData(ExceptionMsg.RequestError);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time-consuming:"+((float)(end-start)/1000.0f)+"秒");

        OperatorExcel.writeExcel("2007",towrite,ExcelFOLDER2+resFileUrl);

        return new ResponseData("000510",resFileUrl+".xlsx");

    }

    @PostMapping("/file")
    public ResponseData singleFileUpload(@RequestParam("file") MultipartFile file)  throws Exception {
        System.out.println("okkkkkkkkkkkkkkk  fileName = "+file.getOriginalFilename()+
        "   fileLength = "+file.getSize());

        if (file.isEmpty()) {
            return new ResponseData(ExceptionMsg.FileEmpty);
        }

        try {
            byte [] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println("写入出错!!!!!!!!!!!");
            e.printStackTrace();
            //return new ResponseEntity("上传出错", HttpStatus.OK);
        }

        System.out.println("in 30 ");

        StringBuffer res = new StringBuffer();
        ArrayList towrite = new ArrayList<TBILModel>();

        String unzipPath = ZipHelper.unZipFiles(new File(UPLOADED_FOLDER + file.getOriginalFilename()),UPLOADED_FOLDER+"zip/");

        System.out.println("unpath = "+unzipPath);

        File tmf = new File(unzipPath);
        File [] tmf2 = tmf.listFiles();

        System.out.println("Ok start OCR ");
        long start = System.currentTimeMillis();

        for(File f: tmf2) {

            if(!f.getName().endsWith(".png")) {
                continue;
            }
            String tmpRes = StartOCR.getOCRText(f.getPath());
            String [] tmpSplit = tmpRes.split("\n");
            towrite.add( new TBILModel(tmpSplit[0].split(":")[1], tmpSplit[1].split(":")[1]) );

            res.append(tmpRes);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time-consuming:"+((float)(end-start)/1000.0f)+"秒");

        String resUrl =unzipPath.substring(unzipPath.lastIndexOf('/')+1);
        System.out.println("execute EXCel res = "+ExcelFOLDER2+resUrl);

        OperatorExcel.writeExcel("2007",towrite,ExcelFOLDER2+resUrl);

        System.out.println("url = "+resUrl+".xlsx");
        return new ResponseData("000510",resUrl+".xlsx");

    }



    @RequestMapping("/test")
    public TBILModel getTsetJson() {

        return new TBILModel("123456","oooo");
    }


}
