package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttachDTO {
    private String id;
    private String name;
    private String originalName;
    private  long fileSize;
    private String hashId;
    private String  uploadPath;
    private String contentType;
    private AttachStatus status;
    private LocalDateTime createdDate ;

}
