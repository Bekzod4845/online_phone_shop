package com.company.service;

import com.company.entity.ColorEntity;
import com.company.entity.ProductColorEntity;
import com.company.entity.ProductEntity;
import com.company.repository.ProductColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductColorService {
    @Autowired
    private ProductColorRepository productColorRepository;
    public void create(ProductEntity product, List<Integer> colorList) {
        for (Integer productId : colorList) {
            ProductColorEntity productColorEntity = new ProductColorEntity();
            productColorEntity.setProduct(product);
            productColorEntity.setColor(new ColorEntity(productId));
            productColorRepository.save(productColorEntity);
        }
    }

}
