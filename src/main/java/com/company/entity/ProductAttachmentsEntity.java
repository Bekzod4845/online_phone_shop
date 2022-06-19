package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_attachment")
public class ProductAttachmentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "product_color_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductColorEntity productColor;

    @JoinColumn(name = "attachment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AttachEntity attach;
}
