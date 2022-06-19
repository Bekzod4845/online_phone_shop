package com.company.entity;

import com.company.enums.Language;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "profile")
@NoArgsConstructor
public class ProfileEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(name = "phone",nullable = false,unique = true)
    private String phoneNumber;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Column
    @Enumerated(EnumType.STRING)
    private Language language = Language.UZ;


    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    private Boolean visible = Boolean.TRUE;

    public ProfileEntity(String id) {
        this.id = id;
    }
}
