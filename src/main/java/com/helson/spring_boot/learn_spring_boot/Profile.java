package com.helson.spring_boot.learn_spring_boot;

import com.fasterxml.jackson.annotation.JsonFilter;

//We add this just to denote this bean is dynamically filtered.
@JsonFilter("ProfileBeanFilter")
public class Profile {

    private String name;
    private int age;
    private String city;

    public Profile(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
