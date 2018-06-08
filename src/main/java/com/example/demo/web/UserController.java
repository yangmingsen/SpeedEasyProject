package com.example.demo.web;


import com.example.demo.domain.ReqPerson;
import com.example.demo.domain.User;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.Response;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.IUserService;
import com.example.demo.utils.DateHelpler;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;


@RequestMapping("/user")
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    IUserService userRepository;


    @PostMapping("/login")
    public ResponseData login(@RequestBody ReqPerson reqPerson, HttpServletResponse res)  {

        System.out.println(reqPerson.getUsername()+" + "+reqPerson.getPassword());
       try{
           User loginUser = userRepository.findUsersByCno(reqPerson.getUsername());
           if(loginUser == null) {
               return new ResponseData(ExceptionMsg.LoginNameNotExists);
           } else if( !loginUser.getCpasd().equals(reqPerson.getPassword())) {
               return new ResponseData(ExceptionMsg.LoginNameOrPassWordError);
           }

           //创建token令牌
            String jwtToken = Jwts.builder().setSubject(reqPerson.getUsername())
                   .setExpiration(new Date(System.currentTimeMillis()+7*24*60*60*1000))
                   .signWith(SignatureAlgorithm.HS256, "secretkey")
                   .compact();

            //添加token令牌到响应头中
           res.addHeader("Authorization","Bearer "+jwtToken);

           return new ResponseData(ExceptionMsg.SUCCESS);

       }catch (Exception e) {
           e.printStackTrace();
           return new ResponseData(ExceptionMsg.FAILED);
       }

    }

    @PostMapping("/reg")
    public ResponseData addUser(@RequestBody User newUser) {
        try {
            User regUser = userRepository.findUsersByCno(newUser.getCno());
            if(regUser != null) {
                return new ResponseData(ExceptionMsg.UserNameUsed);
            }

            regUser.setCno(newUser.getCno());
            regUser.setCname(newUser.getCname());
            regUser.setCpasd(newUser.getCpasd());
            regUser.setCregTime(DateHelpler.getDateNow());

            userRepository.add(regUser);

            return new ResponseData(ExceptionMsg.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping("/tt")
    public ReqPerson getTest( HttpServletResponse res) {
        System.out.println("hello wolrd");

        //创建token令牌
        String jwtToken = Jwts.builder().setSubject("yangmingsen")
                .setExpiration(new Date(System.currentTimeMillis()+60*1000))
                .signWith(SignatureAlgorithm.HS256, "secretkey")
                .compact();

        //添加token令牌到响应头中
        res.addHeader("Authorization","Bearer "+jwtToken);
        return new ReqPerson("yangmingsen","1234567");
    }

}
