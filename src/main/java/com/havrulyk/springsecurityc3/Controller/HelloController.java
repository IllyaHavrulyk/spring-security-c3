package com.havrulyk.springsecurityc3.Controller;

import com.havrulyk.springsecurityc3.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping({"/hello"})
    public String hello(){
        return "Hello";
    }

    @PostMapping(value = {"/user"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsManager.createUser(user);
    }
}
