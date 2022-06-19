package com.company.entity;

import com.company.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false,unique = true)
    private String model;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String analysis;

    @Column(nullable = false)
    private Integer ram;

    @Column(nullable = false)
    private Integer memory;

    @Column(nullable = false)
    private Double price;

    @Column(name = "technical_specification",nullable = false, columnDefinition = "TEXT")
    private String technicalSpecification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private ProfileEntity seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_parent_id")
    private CategoryEntity categoryParent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brend_id")
    private BrandEntity brend;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "date_on_sale")
    private LocalDateTime dateOnSale;
    @Column
    private Boolean visible = Boolean.TRUE;

    public ProductEntity(String id) {
        this.id = id;
    }

    public ProductEntity(String id, String model, Double price) {
        this.id = id;
        this.model = model;
        this.price = price;
    }
}
