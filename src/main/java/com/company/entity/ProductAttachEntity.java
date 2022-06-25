package com.company.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_attachment")
public class ProductAttachEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;

    @JoinColumn(name = "color_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ColorEntity color;

    @JoinColumn(name = "attach_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AttachEntity attach;


    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    private Boolean visible = Boolean.TRUE;



}
