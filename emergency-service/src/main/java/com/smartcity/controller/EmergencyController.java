package com.smartcity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
//@RequestMapping("/emergency")
public class EmergencyController {
	@GetMapping("/test")
    public String testEndpoint() {
        return "Emergency Service is working!";
    }
}
