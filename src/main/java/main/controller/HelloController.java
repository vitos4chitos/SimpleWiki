package main.controller;

import main.service.ImportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloController {

    @Autowired
    ImportDataService importDataService;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }


}