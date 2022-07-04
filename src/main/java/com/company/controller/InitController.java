package com.company.controller;

import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.repository.ProfileRepository;
import com.company.util.springSicurityUtil.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/initAdmin")
    public String initAdmin() {
        Optional<ProfileEntity> optional = profileRepository.findByEmail("admin@gmail.com");
        if (optional.isPresent()) {
            return "Exists";
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName("Bekzod");
        entity.setSurname("Qoraxonov");
        entity.setEmail("admin@gmail.com");
        entity.setPassword(BCryptUtil.getBCrypt("5555"));
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(ProfileRole.ADMIN);
        profileRepository.save(entity);
        return "Success";
    }
}
