package com.kmhoon.catalogservice;

import com.kmhoon.catalogservice.config.ConfigProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final ConfigProperties configProperties;

    public HomeController(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @GetMapping("/")
    public String home() {
        return configProperties.getGreeting();
    }
}
