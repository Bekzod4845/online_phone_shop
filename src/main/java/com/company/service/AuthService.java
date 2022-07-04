package com.company.service;

import com.company.dto.*;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exp.BadRequestException;
import com.company.repository.ProfileRepository;
import com.company.util.springSicurityUtil.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;




    public void registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            throw new BadRequestException("User already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setStatus(ProfileStatus.NOT_ACTIVE);
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setPassword(BCryptUtil.getBCrypt(dto.getPassword()));
        entity.setEmail(dto.getEmail());
        profileRepository.save(entity);

    }







}
