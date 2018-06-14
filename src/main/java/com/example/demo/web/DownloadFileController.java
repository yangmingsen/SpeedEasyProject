package com.example.demo.web;


import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 *下载接口
 * Created by yangmingsen on 2018/6/14
 */
@RequestMapping("/download")
@RestController
@CrossOrigin
public class DownloadFileController {
    private static String ExcelFOLDER = "/home/yms/Documents/SpringBoot/SpeedEasyServer/src/main/resources/ResultExcel/";

    /***
     * 客户端下载excel文件接口
     * @param ReqUrl
     * @param resp
     */
    @RequestMapping(value = "/excel") //使用/download/excel?fileUrl=2018-06-0617:36:331528277793259.xlsx便可下载文件
    public void DownloadExcelFile(@RequestParam("fileUrl") String ReqUrl,  HttpServletResponse resp) {
        System.out.println(ReqUrl);
        File file = new File(ExcelFOLDER+ReqUrl);
        resp.setHeader("content-type", "application/octet-stream");
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment;filename=" + ReqUrl);

        byte [] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;

        try{
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1 ) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
