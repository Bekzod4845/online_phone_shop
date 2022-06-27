package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCreateDTO {
    private String id;
    private String model;
    private Integer brandId;
    private String analysis;
    private Integer ram;
    private Double price;
    private Integer memory;
    private String technicalSpecification;
    private String categoryId;
    private String categoryParentId;
}
