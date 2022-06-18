package com.company.service;

import com.company.dto.ProductCreateDTO;
import com.company.dto.ProductDTO;
import com.company.entity.BrandEntity;
import com.company.entity.CategoryEntity;
import com.company.entity.ProductEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.ProductStatus;
import com.company.exp.ItemNotFoundException;
import com.company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductColorService productColorService;
    @Autowired
    private ProductRepository productRepository;
    public ProductDTO create(ProductCreateDTO dto, Integer profileId) {
        ProductEntity entity = new ProductEntity();
        entity.setModel(dto.getModel());
        entity.setAnalysis(dto.getAnalysis());
        entity.setTechnicalSpecification(dto.getTechnicalSpecification());
        entity.setMemory(dto.getMemory());
        entity.setRam(dto.getRam());
        entity.setPrice(dto.getPrice());

        BrandEntity brand = brandService.get(dto.getBrandId());
        entity.setBrend(brand);

        CategoryEntity category = categoryService.get(dto.getCategoryId());
        entity.setCategory(category);

        ProfileEntity seller = new ProfileEntity();
        seller.setId(profileId);
        entity.setSeller(seller);
        entity.setStatus(ProductStatus.NOT_ON_SALE);

        productRepository.save(entity);
        productColorService.create(entity, dto.getColorList()); // color

        ProductDTO productDTO = new ProductDTO();
        productDTO.setModel(entity.getModel());
        productDTO.setAnalysis(entity.getAnalysis());
        productDTO.setRam(entity.getRam());
        productDTO.setPrice(entity.getPrice());
        productDTO.setMemory(entity.getMemory());
        productDTO.setTechnicalSpecification(entity.getTechnicalSpecification());
        productDTO.setStatus(entity.getStatus());

        return productDTO;
    }
    public void updateByStatus(String productId, Integer pId) {
        Optional<ProductEntity> optional = productRepository.findById(productId);

        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Product not found ");
        }

        ProductEntity product = optional.get();
        if (product.getStatus().equals(ProductStatus.NOT_ON_SALE)) {
            productRepository.changeStatusToOnSale(productId, pId, ProductStatus.ON_SALE, LocalDateTime.now());
        } else if (product.getStatus().equals(ProductStatus.ON_SALE)) {
            productRepository.changeStatusNotOnSale(productId, ProductStatus.NOT_ON_SALE);
        }

    }
}
