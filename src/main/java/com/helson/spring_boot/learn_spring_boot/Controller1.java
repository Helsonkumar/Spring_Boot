package com.helson.spring_boot.learn_spring_boot;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helson.spring_boot.learn_spring_boot.service.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@Validated
public class Controller1 {

    @Autowired
    UserDAO user_service;


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello! Helsonkumar";
    }

    @GetMapping("/profile")
    public Profile getProfile() {
        return new Profile("Helsonkumar", 32, "Chennai");
    }

    @GetMapping("/profile/{name}/{activity}")
    public String getActivity(@PathVariable String name , @PathVariable String activity) {
        return String.format("%s activity is %s as of %s" , name , activity , LocalDateTime.now());
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
       return user_service.getAllUsers();
    }

    /*//Here we shoow the demo of HATEOAS to return the link to other endpoints as well.
    @GetMapping("/user/{id}")
    //public User getUserId(@PathVariable Integer id) throws UserNotFoundException {
    public User getUserId(@PathVariable Integer id) throws UserNotFoundException {
            User user = user_service.retrieveUser(id);
            Resource<User> resource =  new Res
    }*/


    //In this method we access the current request and respond with the URI applcable for the resource created.
    //Location : Technically indicates the URI to which the request must be redirected.
    //@Valid : We are validating the request body in its bean.
    @PostMapping("/user/add")
    public ResponseEntity<Object> addUser(@RequestBody @Valid User user) {
        User new_user = user_service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(new_user.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    //Here we show how to send the Resoponse with Dynamic Filtering of Fields for diff methods.
    //Here we ignore the name and City.
    @GetMapping("/profiles1")
    public MappingJacksonValue getAllProfiles1(){
        List<Profile> profile_list = new ArrayList<>();
        profile_list.add(new Profile("Gladys" ,  37 , "Coimbatore"));
        profile_list.add(new Profile("Helson" , 33 , "Chennai"));

        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("name" , "city");

        FilterProvider filters = new SimpleFilterProvider().addFilter("ProfileBeanFilter" , filter);

        MappingJacksonValue mapping  = new MappingJacksonValue(profile_list);

        mapping.setFilters(filters);

        return mapping;


    }

    //Here we ignore the age field in the response
    @GetMapping("/profiles2")
    public MappingJacksonValue getAllProfiles2(){
        List<Profile> profile_list = new ArrayList<>();
        profile_list.add(new Profile("Gladys" ,  37 , "Coimbatore"));
        profile_list.add(new Profile("Helson" , 33 , "Chennai"));

        SimpleBeanPropertyFilter filter  = SimpleBeanPropertyFilter.filterOutAllExcept("age");

        SimpleFilterProvider filter_provider =  new SimpleFilterProvider().addFilter("ProfileBeanFilter" , filter);

        MappingJacksonValue mapping  = new MappingJacksonValue(profile_list);

        mapping.setFilters(filter_provider);

        return mapping;

    }

    //***************************************************************************************************
    //This is a demo to show the API versioning
    //Type1 : URI versioning : Using diff URI for diff versions.
    @GetMapping("/name/v1")
    public String getNamev1() {
        return "Helsonkumar";
    }

    @GetMapping("name/v2")
    public Name getNamev2(){
        return new Name("Helson" , "Kumar");
    }



    //Type2 : This uses the parms to identify the versioning.
    @GetMapping(value= "/name" , params="v1")
    public String getNameParamv1() {
        return "Helsonkumar";
    }

    @GetMapping(value= "/name" , params="v2")
    public Name getNameParamv2() {
        return new Name("Helson" , "Kumar");
    }

    //Type3: Header - Pass some argument in the headers.
    @GetMapping(value= "/name" , headers = "What-I-Need=v1")
    public String getNameHeaderv1() {
        return "Helsonkumar";
    }

    @GetMapping(value= "/name" , headers = "What-I-Need=v2")
    public Name getNameHeaderv2() {
        return new Name("Helson" , "Kumar");
    }


    //Type4 : Content Negotiation.Meida Type. Mime Type.pasing the content via the Accpet Header Argument.
    // Also called as Accept Header versioning.
    //It is highly granular since it versions based on the version of the resource which is returned.
    @GetMapping(value= "/name" , produces = "application/vnd.api-v1+json")
    public String getNameProducerv1() {
        return "Helsonkumar";
    }

    @GetMapping(value= "/name" , produces = "application/vnd.api-v2+json")
    public Name getNameProducerv2() {
        return new Name("Helson" , "Kumar");
    }

    //***************************************************************************************************


}
