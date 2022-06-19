package com.company.dto;

import com.company.enums.Language;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {

    private String id;

    private String name;

    private String surname;

    private String phoneNumber;

    private ProfileStatus status;

    private ProfileRole role;

    private Boolean visible;

    private LocalDateTime createdDate;

    private Language language;

    private String jwt;


}
