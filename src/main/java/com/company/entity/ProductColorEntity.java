package com.company.entity;

import com.company.enums.ProductColorStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "product_color")
public class ProductColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;

    @JoinColumn(name = "color_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ColorEntity color;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductColorStatus status;


    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    private Boolean visible = Boolean.TRUE;


}
