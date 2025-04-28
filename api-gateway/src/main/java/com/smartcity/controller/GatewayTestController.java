package com.smartcity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayTestController {
    @GetMapping("/gateway-status")
    public String status() {
        return "API Gateway Operational";
    }
}