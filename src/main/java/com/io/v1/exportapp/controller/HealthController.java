package com.io.v1.exportapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping
    public String checkHealth(){
        return "<html><body><center> API up and running </center></body></html>";
    }

}
