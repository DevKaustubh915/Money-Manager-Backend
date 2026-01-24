package com.kaustubh.MoneyManagerBackend.controller;

import com.kaustubh.MoneyManagerBackend.dto.AuthDTO;
import com.kaustubh.MoneyManagerBackend.dto.ProfileDTO;
import com.kaustubh.MoneyManagerBackend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

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


    //security section 3

    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthDTO authDTO){
        try {
            if (!profileService.isAccountActive(authDTO.getEmail())){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("mesage" , "Account is not activated. /n Please activate your account"));
            }

            Map<String , Object> response = profileService.authenticateAndgenerateToken(authDTO);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Message", e.getMessage()));
        }
    }

    @GetMapping("/test")
    public String test(){
        return "test successful";
    }

}
