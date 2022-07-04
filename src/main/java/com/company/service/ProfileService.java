package com.company.service;

import com.company.config.springConfig.CustomUserDetails;
import com.company.dto.ProfileDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import com.company.util.springSicurityUtil.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    // admin
    public void create(ProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            throw new BadRequestException("User already exists");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(BCryptUtil.getBCrypt(dto.getPassword()));
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(ProfileRole.ROLE_SELLER);
        profileRepository.save(entity);
    }

    public void update(String profileId, ProfileDTO dto) {;
        Optional<ProfileEntity> optional = profileRepository.findById(profileId);
        if (optional.isEmpty()){
            throw new BadRequestException("User Not Fount");
        }
        ProfileEntity profileEntity = optional.get();
        profileEntity.setName(dto.getName());
        profileEntity.setSurname(dto.getSurname());
        profileEntity.setEmail(dto.getEmail());
        profileEntity.setPassword(dto.getPassword());
        profileRepository.save(profileEntity);
    }

    public ProfileEntity get(String id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Profile Not found");
        });
    }

    public CustomUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }


    public List<ProfileDTO> getAll() {
        Iterable<ProfileEntity> entities = profileRepository.findAll();
        List<ProfileDTO> dtoList = new LinkedList<>();
        entities.forEach(profileEntity -> {
            ProfileDTO dto = new ProfileDTO();
            dto.setName(profileEntity.getName());
            dto.setSurname(profileEntity.getSurname());
            dto.setEmail(profileEntity.getEmail());
            dtoList.add(dto);
        });
        return dtoList;

    }

    public void profileBlock(String id) {
        ProfileEntity entity = get(id);
        entity.setStatus(entity.getStatus().equals(ProfileStatus.ACTIVE) ? ProfileStatus.NOT_ACTIVE : ProfileStatus.ACTIVE);
        System.out.println("success change profile status");
        profileRepository.save(entity);
    }

    public void profileDelete(String id) {
        ProfileEntity entity = get(id);
        entity.setVisible(!entity.getVisible());
        System.out.println("success  profile delete");
        profileRepository.save(entity);
    }
}

