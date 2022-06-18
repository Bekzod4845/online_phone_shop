package com.company.repository;

import com.company.entity.ProductEntity;
import com.company.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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


    Optional<ProductEntity> findByModel(String model);

    @Modifying
    @Transactional
    @Query("update ProductEntity a set  a.visible =true ,a.seller.id=:pid where a.id=:productId")
    void deleteByProductVisible(@Param("productId") String productId, @Param("pid") Integer pId);
    @Modifying
    @Transactional
    @Query("update ProductEntity a set a.visible = false ,a.dateOnSale = null where a.id=:productId")
    void deleteByNotProductVisible(@Param("productId") String productId);




    @Query("SELECT new ProductEntity(p.id,p.model,p.price) " +
            " from ProductEntity as p inner join p.category as c  " +
            " inner join p.brend as b " +
            " where p.visible = true and c.key =:categoryKey and p.status = 'ON_SALE' and b.name =:brand order by p.dateOnSale")
    List<ProductEntity> findLastPhone(@Param("brand") String brand,@Param("categoryKey") String categoryKey);

    @Query("SELECT new ProductEntity(p.id,p.model,p.price) " +
            " from ProductEntity as p where p.visible = true and p.status = 'ON_SALE' " +
            " order by p.dateOnSale")
    Page<ProductEntity> findLastBy(Pageable pageable);

}
