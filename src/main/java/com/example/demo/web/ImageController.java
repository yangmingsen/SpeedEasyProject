package com.example.demo.web;

import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.utils.Const;
import com.example.demo.utils.TokenHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * 图片服务器接口
 * Created by yangmingsen on 2018/06/14
 */
@RequestMapping("/image")
@RestController
@CrossOrigin
public class ImageController {

    private static final String PHOTO_PATH = "/home/yms/Documents/SpringBoot/SpeedEasyServer/src/main/resources/static/images/";

    /***
     * 向客户端响应图片
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping(value = "/usr")
    public void queryPic( @RequestParam("img") String img, HttpServletRequest req, HttpServletResponse res) throws IOException {

        System.out.println("imguser = "+img);
        res.setContentType("image/jpeg");
        FileInputStream is = new FileInputStream(PHOTO_PATH+img+".jpg");


        if (is != null){
            int i = is.available(); // 得到文件大小
            byte data[] = new byte[i];
            is.read(data); // 读数据
            is.close();
            res.setContentType("image/jpeg");  // 设置返回的文件类型
            OutputStream toClient = res.getOutputStream(); // 得到向客户端输出二进制数据的对象
            toClient.write(data); // 输出数据
            toClient.close();
        }
    }

}
