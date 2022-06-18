package com.company.entity.attach;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttachmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "file_orginal_name",nullable = false)
    private String fileOriginalName;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private  long size;
    @Column(name = "content_type",nullable = false)
    private String contentType;
    @Column(name = "file_url",nullable = false)
    private String fileUrl;
    @JoinColumn(name = "attachment_id")
    @OneToOne(fetch = FetchType.LAZY)
    private AttachmentEntity attachment;
    }

