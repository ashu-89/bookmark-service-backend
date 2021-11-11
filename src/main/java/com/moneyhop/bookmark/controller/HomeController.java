package com.moneyhop.bookmark.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(path = "/home")
    public String home(){
        return "This is homepage.";
    }

    @GetMapping(path = "/admin")
    public String admin(){
        return "This is admin page.";
    }
}
