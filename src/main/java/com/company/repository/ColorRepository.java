package com.company.repository;


import com.company.entity.ColorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ColorRepository extends PagingAndSortingRepository<ColorEntity, Integer> {

    Iterable<ColorEntity> findAllByVisible(boolean b);

    Optional<ColorEntity> findByKey(String key);
}
