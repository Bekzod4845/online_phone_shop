package com.company.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "color")
@NoArgsConstructor
public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String key;
    @Column(name = "name_uz",nullable = false)
    private String nameUz;
    @Column(name = "name_ru",nullable = false)
    private String nameRu;
    @Column(name = "name_en",nullable = false)
    private String nameEn;
    @Column(name = "color_code",nullable = false,unique = true)
    private String code;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column
    private Boolean visible = Boolean.TRUE;

    public ColorEntity(Integer id) {
        this.id = id;
    }
}
