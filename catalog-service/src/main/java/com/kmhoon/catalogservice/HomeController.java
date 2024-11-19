package com.kmhoon.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "도서 카탈로그에 오신 것을 환영합니다.";
    }
}
