package com.company.dto.brand;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandDTO {
    private Integer id;
    private String name;
    private LocalDateTime createdDate;
    private Boolean visible;
}
