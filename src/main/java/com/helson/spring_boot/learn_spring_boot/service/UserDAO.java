package com.helson.spring_boot.learn_spring_boot.service;


import com.helson.spring_boot.learn_spring_boot.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDAO {

    private List<User> user_list = new ArrayList<User>();
    private Integer id_counter = 0;

    public User saveUser(User user){
        if(user.getId()== null) {
            user.setId(++id_counter);
            user_list.add(user);
        }
        return user;
    }


    public User retrieveUser(Integer id){
        return user_list.stream().filter(user -> user.getId()==id).collect(Collectors.toList()).get(0);
    }

    public List<User> getAllUsers(){
        return user_list;
    }

    public List<User> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<User> user_list) {
        this.user_list = user_list;
    }
}
