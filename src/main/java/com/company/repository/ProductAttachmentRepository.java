package com.company.repository;

import com.company.entity.ProductAttachmentsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductAttachmentRepository extends PagingAndSortingRepository<ProductAttachmentsEntity,Integer> {
}
