package com.kaustubh.MoneyManagerBackend.controller;

import com.kaustubh.MoneyManagerBackend.dto.ProfileDTO;
import com.kaustubh.MoneyManagerBackend.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/status", "/health"})
public class HomeController {


    @GetMapping
    public String HealthCheck(){
        return "Application  is running";
    }


}
