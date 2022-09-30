package com.yn.wechatmpstudy.contoller;


import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    private final static String  token = "awsl";


    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World";
    }


    @GetMapping("/")
    public String hello2(){
        return "index";
    }

    @ResponseBody
    @GetMapping(value = "/wechat",produces = MediaType.TEXT_PLAIN_VALUE)
    public String wechat(String signature,String timestamp,String nonce,String echostr){
        if (wechatVaild(signature,timestamp,nonce)){
            return echostr;
        }else {
            return "error";
        }

    }

    private Boolean wechatVaild(String signature, String timestamp, String nonce){
        String t = token+timestamp+nonce;
        System.out.println("pre: "+t);
        String res = DigestUtils.sha1Hex(t);
        return  res.equals(signature);
    }

}
