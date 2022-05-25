package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {

    @GetMapping("/s")
    public String s(){
        return "s";
    }

    @PostMapping("/test")
    public String test(MultipartFile file){
        System.out.println(file.getOriginalFilename());
        return "shy";
    }
}
