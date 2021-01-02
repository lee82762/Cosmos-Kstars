package com.example.restfulwebservice.User;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users=new ArrayList<>();
    private  static  int userCount=3;

    static{
        users.add(new User(1,"jaebum",new Date(),"pass1","701010-111111"));
        users.add(new User(2,"kimsung",new Date(),"pass2","801010-111111"));
        users.add(new User(3,"yehea",new Date(),"pass3","901010-111111"));
    }
    public List<User> findAll(){
        return  users;
    }
    //전체정보 찾는 서비스
    public User save(User user){
        if(user.getId()==null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }
    //하나만 찾아주는 서비스
    public User findOne(int id){
        for(User user :users){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }
    //삭제 서비스
    public User deleteById(int id){
        Iterator<User>iterator= users.iterator();
        //순차적으로 표현
        while (iterator.hasNext()){
            User user=iterator.next();

            if(user.getId()==id){
                iterator.remove();
                return user;
            }

        }
        return null;
    }

    //수정 서비스
    public User updateById(int id,User user){
        for(User user1: users){
            if(user1.getId()==id){
                int idx=users.indexOf(user1);
                users.get(idx).setName(user.getName());
                users.get(idx).setJoinDate(user.getJoinDate());
                return user;
            }

        }
        return null;
    }


}
