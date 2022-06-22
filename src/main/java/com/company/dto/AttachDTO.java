package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// PROJECT NAME Kun_Uz
// TIME 17:02
// MONTH 06
// DAY 20

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class AttachDTO {
    private String id;
    private String originalName;
    private String extension;
    private Long size;
    private String path;
    private LocalDateTime createdDate;
    private String url;
    private String downloadUrl;

    List<String> uuidList = new ArrayList<>();
}
