package com.company.entity;

import com.company.enums.ProductAttachmentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "product_attachment")
public class ProductAttachmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;

    @JoinColumn(name = "color_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ColorEntity color;

    @JoinColumn(name = "attachment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AttachEntity attach;


    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    private Boolean visible = Boolean.TRUE;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductAttachmentStatus status;
}
