package com.company.repository;

import com.company.entity.ProductAttachEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProductAttachmentRepository extends PagingAndSortingRepository<ProductAttachEntity,String> {

}
