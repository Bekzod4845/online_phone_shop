package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductAttachDTO {
    private String id;
    private String productId;
    private ProductDTO productDTO;
    private Integer colorId;
    private ColorDTO colorDTO;
    private List<UUID>attachList;
    private AttachDTO attachDTO;
}
