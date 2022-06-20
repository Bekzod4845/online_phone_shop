package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    // admin
    public ProfileDTO create(ProfileDTO dto) {
        isValid(dto);
        Optional<ProfileEntity> optional = profileRepository.findByPhoneNumber(dto.getPhoneNumber());
        if (optional.isPresent()) {
            throw new BadRequestException("User already exists");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(ProfileRole.SELLER);
        profileRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public void update(String profileId, ProfileDTO dto) {;
        Optional<ProfileEntity> optional = profileRepository.findById(profileId);
        if (optional.isEmpty()){
            throw new BadRequestException("User Not Fount");
        }
        ProfileEntity profileEntity = optional.get();
        profileEntity.setName(dto.getName());
        profileEntity.setSurname(dto.getSurname());
        profileEntity.setPhoneNumber(dto.getPhoneNumber());
        profileRepository.save(profileEntity);
      //  profileRepository.update(profileId,dto.getName(),dto.getSurname(),dto.getPhoneNumber());

    }

    public ProfileEntity get(String id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Profile Not found");
        });
    }

    private void isValid(ProfileDTO dto) {
        if (dto.getSurname() == null){
            throw new BadRequestException("surname is wrong");
        }
        if (dto.getName() == null){
            throw new BadRequestException("name is wrong");
        }
        if (dto.getPhoneNumber() == null ){
            throw new BadRequestException("phone is wrong ");
        }

    }

    public List<ProfileDTO> getAll() {
        Iterable<ProfileEntity> entities = profileRepository.findAll();
        List<ProfileDTO> dtoList = new LinkedList<>();
        entities.forEach(profileEntity -> {
            ProfileDTO dto = new ProfileDTO();
            dto.setName(profileEntity.getName());
            dto.setSurname(profileEntity.getSurname());
            dto.setPhoneNumber(profileEntity.getPhoneNumber());
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

