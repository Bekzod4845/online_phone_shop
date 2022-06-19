package com.company.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "category")
@NoArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(unique = true,nullable = false)
    private String key;
    @Column(name = "name_uz",nullable = false)
    private String nameUz;
    @Column(name = "name_ru",nullable = false)
    private String nameRu;
    @Column(name = "name_en",nullable = false)
    private String nameEn;
    @JoinColumn(name = "parent_category_id")
    @ManyToOne
    CategoryEntity parentCategory;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column
    private Boolean visible = Boolean.TRUE;

    public CategoryEntity(String id) {
        this.id = id;
    }
}
