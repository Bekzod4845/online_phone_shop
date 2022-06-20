package com.company.service;

import com.company.entity.AttachEntity;
import com.company.entity.ProductAttachmentEntity;
import com.company.repository.ProductAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAttachmentService {
    @Autowired
    private ProductAttachmentRepository productAttachmentRepository;



}
