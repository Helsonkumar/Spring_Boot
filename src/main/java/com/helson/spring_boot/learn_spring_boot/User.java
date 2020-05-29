package com.helson.spring_boot.learn_spring_boot;


import org.springframework.stereotype.Component;

@Component
public class User {

    private Integer id;
    private String name;
    private Integer age;
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


