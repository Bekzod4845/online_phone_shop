package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypesDTO {
    private Integer id;
    private String key;
    private String nameUz;
    private String nameEn;
    private String nameRu;
    private String name;
    private LocalDateTime createdDate;
    private Boolean visible;
}
