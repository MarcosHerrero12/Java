package com.Java.app_security.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/welcome")
public class WelcomeController {

    @GetMapping
    public Map<String,String> welcome (){
        return Collections.singletonMap("msj", "welcome");
    }
    @RequestMapping(path = "/loans")
    @GetMapping
    public Map<String, String> loans(){
        //business logic
        return Collections.singletonMap("msj", "loans");

    }
    @RequestMapping(path = "/cards")
    @GetMapping
    public Map<String, String> cards(){
        //business logic
        return Collections.singletonMap("msj", "cards");
    }
    @RequestMapping(path = "/accounts")
    @GetMapping
    public Map<String, String> accounts(){
        //business logic
        return Collections.singletonMap("msj", "accounts");
    }

    @RequestMapping(path = "/balance")
    @GetMapping
    public Map<String, String> balance(){
        //business logic
        return Collections.singletonMap("msj", "balance");
    }

    @RequestMapping(path = "/about_us")
    @GetMapping
    public Map<String, String> aboutAs(){
        //business logic
        return Collections.singletonMap("msj", "aboutUs");
    }





}
