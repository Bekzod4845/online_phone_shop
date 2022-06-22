package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ProductAttachDTO {
    private String id;
    private String productId;
    private String colorKey;
    private List<String>attachList;
}
