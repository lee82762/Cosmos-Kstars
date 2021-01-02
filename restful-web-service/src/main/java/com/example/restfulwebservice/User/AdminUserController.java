package com.example.restfulwebservice.User;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    private UserDaoService service;

    //생성자를 통한 의존성 주입
    public AdminUserController(UserDaoService service){
        this.service=service;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers(){
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","ssn");//bean의 프로포티스를 관리

        //필터를 사용할수 형태로 변경
        FilterProvider filters=new SimpleFilterProvider().addFilter("UserInfo",filter);

        //fiter를 객체형태로 return 하기위해서
        MappingJacksonValue mapping=new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;

    }

    //GET/admin/users/1 or/users/10 ->/admin/v1/users/1
    //@GetMapping("/v1/users/{id}")
   // @GetMapping(value = "/users/{id}/",params = "version=1")//parm을 이용한 버전관리
  //  @GetMapping(value = "/users/{id}",headers="X-API-VERSION=1")//header을 이용한 버전관리
    @GetMapping(value = "/users/{id}",produces = "application/vnd.company.appv1+json")//mytime을 이용
    public MappingJacksonValue retrieveUserV1(@PathVariable int id){
        //Ctrl+Art+V
        User user = service.findOne(id);

        //예외발생 코드
        if(user==null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }
        //제어하고 싶은 것이 있으면 filter Class을 사용하는게 좋다.
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","ssn");

        FilterProvider filters=new SimpleFilterProvider().addFilter("UserInfo",filter);

        MappingJacksonValue mapping=new MappingJacksonValue(user);
        mapping.setFilters(filters);
        return mapping;
    }

    //@GetMapping("/v2/users/{id}")
    // @GetMapping(value = "/users/{id}/",params = "version=2")
   // @GetMapping(value = "/users/{id}",headers="X-API-VERSION=2") //header을 이용한 버전관리
    @GetMapping(value = "/users/{id}",produces = "application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id){
        //Ctrl+Art+V
        User user = service.findOne(id);
        //예외발생 코드
        if(user==null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }
        //User->User2 옮기기
        UserV2 userV2=new UserV2();
        BeanUtils.copyProperties(user,userV2);
        userV2.setGrade("VIP");

        //제어하고 싶은 것이 있으면 filter Class을 사용하는게 좋다.
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","grade");

        FilterProvider filters=new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping=new MappingJacksonValue(userV2);
        mapping.setFilters(filters);
        return mapping;
    }




}
