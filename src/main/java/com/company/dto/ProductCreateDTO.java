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
    private Integer memory;
    private Double price;
    private String technicalSpecification;
    private Integer categoryId;
    private Integer categoryParentId;
    private List<Integer> colorList;
}
