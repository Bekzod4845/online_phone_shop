package com.company.repository;

import com.company.entity.AttachEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AttachRepository extends PagingAndSortingRepository<AttachEntity,String> {

}
