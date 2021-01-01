package com.example.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
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


}
