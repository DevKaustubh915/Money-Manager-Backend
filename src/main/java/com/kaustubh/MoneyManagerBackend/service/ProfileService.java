package com.kaustubh.MoneyManagerBackend.service;

import com.kaustubh.MoneyManagerBackend.dto.ProfileDTO;
import com.kaustubh.MoneyManagerBackend.entity.ProfileEntity;
import com.kaustubh.MoneyManagerBackend.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileDTO register(ProfileDTO profileDTO){
        log.info("Inside ProfileService : register()");

        ProfileEntity newProfile = toEntity(profileDTO);
        newProfile.setActivationToken(UUID.randomUUID().toString());
        newProfile = profileRepository.save(newProfile);

        return toDTO(newProfile);

    }

    public ProfileEntity toEntity(ProfileDTO profileDTO){
        log.info("Inside ProfileService : toEntity()");

        return ProfileEntity.builder()
                .id(profileDTO.getId())
                .fullName(profileDTO.getFullName())
                .email(profileDTO.getEmail())
                .password(profileDTO.getPassword())
                .profileImageUrl(profileDTO.getProfileImageUrl())
                .createdAt(profileDTO.getCreatedAt())
                .updatedAt(profileDTO.getUpdatedAt())
                .build();
    }

    public ProfileDTO toDTO(ProfileEntity profileEntity){
        log.info("Inside ProfileService : toDTO()");

        return ProfileDTO.builder()
                .id(profileEntity.getId())
                .fullName(profileEntity.getFullName())
                .email(profileEntity.getEmail())
                .profileImageUrl(profileEntity.getProfileImageUrl())
                .createdAt(profileEntity.getCreatedAt())
                .updatedAt(profileEntity.getUpdatedAt())
                .build();
    }

}
