package com.kaustubh.MoneyManagerBackend.controller;

import com.kaustubh.MoneyManagerBackend.dto.ProfileDTO;
import com.kaustubh.MoneyManagerBackend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProfileController {

    private final ProfileService profileService;



    @PostMapping("/register")
    public ResponseEntity<ProfileDTO> register(@RequestBody ProfileDTO profileDTO ){

        log.info("Inside ProfileController : register()");

        ProfileDTO registeredProfile = profileService.register(profileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredProfile);
    }


    @GetMapping("/activate")
    public ResponseEntity<String> activateProfile(@RequestParam String token){
        boolean isActivated = profileService.activateProfile(token);
        if (isActivated){
            return ResponseEntity.ok("Profile activated sucessfully!!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activation token not found or already used");
        }
    }

}
