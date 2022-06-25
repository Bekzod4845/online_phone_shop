package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShortInfoProduct {
    private String model;
    private String title;
    private Double price;
    private String imageUrl;
}
