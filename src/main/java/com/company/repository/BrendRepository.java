package com.company.repository;

import com.company.entity.BrandEntity;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface BrendRepository extends PagingAndSortingRepository<BrandEntity, Integer> {

    Optional<BrandEntity> findByName(String name);
}
