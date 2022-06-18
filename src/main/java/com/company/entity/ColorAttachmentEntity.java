package com.company.entity;

import com.company.entity.attach.AttachmentEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "color_attachment")
public class ColorAttachmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "color_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ColorEntity color;

    @JoinColumn(name = "attachment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AttachmentEntity attachment;

}
