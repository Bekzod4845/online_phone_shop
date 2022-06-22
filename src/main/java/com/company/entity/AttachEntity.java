package com.company.entity;
// PROJECT NAME Kun_Uz
// TIME 16:59
// MONTH 06
// DAY 20

import com.company.enums.ProductAttachmentStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String originalName;
    @Column(nullable = false)
    private String extension;
    @Column(nullable = false)
    private Long size;
    @Column(nullable = false)
    private String path;
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

}
