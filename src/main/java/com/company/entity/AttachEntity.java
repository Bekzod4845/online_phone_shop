package com.company.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String extension;

    @Column(name = "original_name",nullable = false)
    private String originalName;

    @Column(name = "file_size",nullable = false)
    private  long fileSize;

    @Column
    private String  uploadPath;

    @Column(name = "content_type",nullable = false)
    private String contentType;


    public AttachEntity(String attachId) {
    }


}

