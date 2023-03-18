package com.project.Backendapi.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchingController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
