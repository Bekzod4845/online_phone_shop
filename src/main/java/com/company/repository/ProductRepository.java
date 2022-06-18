package com.company.repository;

import com.company.entity.ProductEntity;
import com.company.enums.ProductStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity,String> {
    @Modifying
    @Transactional
    @Query("update ProductEntity a set a.status =:status, a.dateOnSale =:time, a.seller.id=:pid where a.id=:productId")
    void changeStatusToOnSale(@Param("productId") String productId,@Param("pid") Integer pId,
                              @Param("status") ProductStatus status,@Param("time") LocalDateTime time);
    @Modifying
    @Transactional
    @Query("update ProductEntity a set a.status =:status where a.id=:productId")
    void changeStatusNotOnSale( @Param("productId") String productId , @Param("status") ProductStatus status);




}
