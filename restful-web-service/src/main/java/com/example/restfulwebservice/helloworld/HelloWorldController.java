package com.example.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired //현재 스프링프레임워크에 등록되어 있는 bean들중에서 같은 타입의 가진 bean을 자동으로 주입
    private MessageSource messageSource;

    @GetMapping("/hello-world")
    public String hewlloWorld(){
        return "Hello World123";
    }
    @GetMapping("/hello-world2")
    public String hewlloWorld1(){
        return "Hello World123";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("helloworld");
    }

    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean1(@PathVariable String name){
        return new HelloWorldBean(String.format("helloworld, %s",name));
    }

    @GetMapping(path="/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name="Accept-Language",required = false) Locale locale){
    return messageSource.getMessage("greeting.message",null,locale);
}


}
