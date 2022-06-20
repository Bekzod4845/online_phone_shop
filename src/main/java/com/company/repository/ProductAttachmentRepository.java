package com.company.repository;

import com.company.entity.ProductAttachmentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductAttachmentRepository extends PagingAndSortingRepository<ProductAttachmentEntity,Integer> {
}
