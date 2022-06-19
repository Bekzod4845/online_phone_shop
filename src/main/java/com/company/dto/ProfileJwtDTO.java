package com.company.dto;

import com.company.enums.ProfileRole;

public class ProfileJwtDTO {
    private String id;
    private ProfileRole role;

    public ProfileJwtDTO(String id, ProfileRole role) {
        this.id = id;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public ProfileRole getRole() {
        return role;
    }
}
