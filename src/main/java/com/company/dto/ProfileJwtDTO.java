package com.company.dto;

import com.company.enums.ProfileRole;

public class ProfileJwtDTO {
    private Integer id;
    private ProfileRole role;

    public ProfileJwtDTO(Integer id, ProfileRole role) {
        this.id = id;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public ProfileRole getRole() {
        return role;
    }
}
