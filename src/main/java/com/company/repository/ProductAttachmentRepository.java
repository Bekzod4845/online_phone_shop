package com.company.repository;

import com.company.entity.AttachEntity;
import com.company.entity.ProductAttachEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductAttachmentRepository extends PagingAndSortingRepository<ProductAttachEntity,String> {
    boolean existsAllByAttach(AttachEntity attach );

    @Modifying
    @Transactional
    @Query("update  ProductAttachEntity p set p.visible= false where p.id=:id")
    void updateVisible(String id);

    boolean existsByAttach(AttachEntity attach);
}
