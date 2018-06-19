package com.example.demo.web;


import com.example.demo.domain.ReqPerson;
import com.example.demo.domain.User;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.IUserService;
import com.example.demo.utils.Const;
import com.example.demo.utils.DateHelpler;
import com.example.demo.utils.TokenHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户登录注册接口
 * Created by yangmingsen on 2018/05/01
 */
@RequestMapping("/user")
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    IUserService userRepository;

    /**
     * 用户登录
     * @param reqPerson
     * @param res
     * @return
     */
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
                   .setExpiration(new Date(Const.JWT_TOKEN_EXP))
                   .signWith(SignatureAlgorithm.HS256, Const.JWT_SECRET_KEY)
                   .compact();

           System.out.println("logintoken  =" +jwtToken);

           //添加token到返回值中
           return new ResponseData("0006000","Bearer "+jwtToken);

       }catch (Exception e) {
           e.printStackTrace();
           return new ResponseData(ExceptionMsg.FAILED);
       }

    }

    /**
     * 注册用户
     * @param newUser
     * @return
     */
    @PostMapping("/reg")
    public ResponseData addUser(@RequestBody ReqPerson newUser) {
        System.out.println(newUser.toString());
        try {
            User regUser = userRepository.findUsersByCno(newUser.getUsername() );
            if(regUser != null) {
                return new ResponseData(ExceptionMsg.UserNameUsed);
            }

            User addU = new User();
            addU.setCno(newUser.getUsername());
            addU.setCpasd(newUser.getPassword());
            addU.setCregTime(DateHelpler.getDateNow());

          int res =   userRepository.add(addU);

            return new ResponseData(ExceptionMsg.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping("/tt")
    public ResponseData getTest(HttpServletRequest req) {
        TokenHelper toH = new TokenHelper(req.getHeader(Const.JWT_HEADER));
        if(toH.tokenExpIsFailed()) {
            return new ResponseData(ExceptionMsg.TOKENEXPFAILED);
        }
        System.out.println("exp = "+DateHelpler.getTokenExpirationDate(toH.getClaims().getExpiration()));
        System.out.println("用户铭 = "+toH.getClaims().getSubject());
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    /**
     *返回用户信息接口
     * @param req
     * @return
     */
    @RequestMapping("/info")
    public User getUserInfo(HttpServletRequest req) {
        TokenHelper toh = new TokenHelper(req.getHeader(Const.JWT_HEADER));
        User usr = userRepository.findUsersByCno(toh.getTokenUser());
        usr.setCpasd("");
        return usr;
    }



}
