package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ColorAttachmentDTO {
    private Integer colorId;
    private List<String>attachment;
}
