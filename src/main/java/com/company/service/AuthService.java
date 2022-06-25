package com.company.service;

import com.company.dto.*;
import com.company.entity.ProfileEntity;
import com.company.entity.SmsEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exp.BadRequestException;
import com.company.repository.ProfileRepository;
import com.company.repository.SmsRepository;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private SmsService smsService;

    public ProfileDTO login(AuthDTO authDTO) {
        Optional<ProfileEntity> optional = profileRepository.findByPhoneNumber(authDTO.getPhoneNumber());
        if (optional.isEmpty()) {
            throw new BadRequestException("User not found");
        }
        ProfileEntity profile = optional.get();

        ProfileDTO dto = new ProfileDTO();
        dto.setName(profile.getName());
        dto.setSurname(profile.getSurname());
        dto.setJwt(JwtUtil.encode(profile.getId(), profile.getRole()));

        return dto;
    }

    public ProfileDTO registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByPhoneNumber(dto.getPhoneNumber());
        if (optional.isPresent()) {
            throw new BadRequestException("User already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setStatus(ProfileStatus.NOT_ACTIVE);
        entity.setRole(ProfileRole.USER);
        entity.setPhoneNumber(dto.getPhoneNumber());
        profileRepository.save(entity);
        ProfileDTO dto1 = new ProfileDTO();
        dto1.setName(entity.getName());
        dto1.setSurname(entity.getSurname());
        dto1.setJwt(JwtUtil.encode(entity.getId(), entity.getRole()));
       // smsService.sendRegistrationSms(dto.getPhoneNumber());


        return dto1;
    }


    public String verification(VerificationDTO dto) {

        Optional<SmsEntity> optional = smsRepository.findTopByPhoneOrderByCreatedDateDesc(dto.getPhone());
        if (optional.isEmpty()) {
            return "Phone Not Found";
        }

        SmsEntity sms = optional.get();
        LocalDateTime validDate = sms.getCreatedDate().plusMinutes(1);

        if (!sms.getCode().equals(dto.getCode())) {
            return "Code Invalid";
        }
        if (validDate.isBefore(LocalDateTime.now())) {
            return "Time is out";
        }

        profileRepository.updateStatusByPhone(dto.getPhone(), ProfileStatus.ACTIVE);
        return "Verification Done";
    }



    public ResponseInfoDTO resendSms(String phone) {
        Long count = smsService.getSmsCount(phone);
        if (count >= 4) {
            return new ResponseInfoDTO(-1, "Limit dan o'tib getgan");
        }
        smsService.sendRegistrationSms(phone);
        return new ResponseInfoDTO(1);
    }
}
