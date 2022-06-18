package com.company.dto;


import com.company.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private String id;
    private String analysis;
    private String model;
    private Integer ram;
    private Integer memory;
    private Double price;
    private String technicalSpecification;
    private ProfileDTO seller;
    private CategoryDTO category;
    private BrandDTO brand;
    private ProductStatus status;
    private Boolean visible;
    private LocalDateTime createdDate;
    private LocalDateTime dateOnSale;
}
