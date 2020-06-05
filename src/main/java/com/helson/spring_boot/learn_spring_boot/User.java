package com.helson.spring_boot.learn_spring_boot;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

@Validated
public class User {

    private Integer id;

    //This is the validation annotation.
    @Size(min=3, message = "Name is not of the expected length")
    private String name;
    private Integer age;

    @JsonIgnore  //** To ignore this field in teh Response Body.
    private String city;

    private String sex;

    public User(){

    }


    public User(Integer id, String name, Integer age, String city, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}


