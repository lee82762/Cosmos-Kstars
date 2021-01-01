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
        users.add(new User(1,"jaebum",new Date()));
        users.add(new User(2,"kimsung",new Date()));
        users.add(new User(3,"yehea",new Date()));
    }
    public List<User> findAll(){
        return  users;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        for(User user :users){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

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

    public User updateById(int id,User user){
        for(User user1: users){
            if(user1.getId()==id){
                System.out.println(id);
                System.out.println(users.get(id-1));
                users.get(id-1).setName(user.getName());
                users.get(id-1).setJoinDate(user.getJoinDate());
            }
            return user;
        }
        return null;
    }


}
