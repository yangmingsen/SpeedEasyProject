package com.example.demo.web;

import com.example.demo.domain.FileHistory;
import com.example.demo.domain.ResultHistory;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.IFHService;
import com.example.demo.service.IRHService;
import com.example.demo.utils.Const;
import com.example.demo.utils.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户历史记录查询接口
 * Created by yangmigsen on 2018/06/14
 */
@RequestMapping("/his")
@RestController
@CrossOrigin
public class HistoryController {
    @Autowired
    private IFHService fhRepository;

    @Autowired
    private IRHService rhRepository;

    /***
     * 查询某用户所有的上传记录
     * @param req
     * @return
     */
    @RequestMapping("/photofile")
    public List<FileHistory> getFileHistory(HttpServletRequest req) {
        TokenHelper toh = new TokenHelper(req.getHeader(Const.JWT_HEADER));
        return  fhRepository.findFHListByCno(toh.getTokenUser());
    }

    /***
     * 查询用户所有的识别结果记录
     * @param req
     * @return
     */
    @RequestMapping("/excelfile")
    public List<ResultHistory> getResultHistory(HttpServletRequest req) {
       // TokenHelper toh = new TokenHelper(req.getHeader(Const.JWT_HEADER));
        return rhRepository.findRHListByCno("123456@qq.com");
    }
}
