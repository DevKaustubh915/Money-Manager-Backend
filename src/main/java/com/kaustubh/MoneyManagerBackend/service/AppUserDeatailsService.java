package com.kaustubh.MoneyManagerBackend.service;

import com.kaustubh.MoneyManagerBackend.entity.ProfileEntity;
import com.kaustubh.MoneyManagerBackend.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AppUserDeatailsService implements UserDetailsService {

    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ProfileEntity existinfProfile = profileRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found with email"));

        return User.builder()
                .username(existinfProfile.getEmail())
                .password(existinfProfile.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
