package com.company.service;

import com.company.dto.ProductCreateDTO;
import com.company.dto.ProductDTO;
import com.company.entity.*;
import com.company.enums.ProductStatus;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.mapper.ProductShortInfo;
import com.company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
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

    public PageImpl pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "dateOnSale");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProductEntity> all = productRepository.findAll(pageable);
        List<ProductEntity> list = all.getContent();
        List<ProductDTO> dtoList = new LinkedList<>();
        list.forEach(productEntity -> {
            dtoList.add(shortDTOInfo(productEntity));
        });
        return new PageImpl(dtoList,pageable, all.getTotalElements());
    }

    public ProductDTO create(ProductCreateDTO dto, String profileId) {
        ProductEntity entity = new ProductEntity();
        Optional<ProductEntity> optional= productRepository.findByModel(dto.getModel());
        if (optional.isPresent()){
           throw new BadRequestException("model is wrong");
        }
        entity.setModel(dto.getModel());

        entity.setAnalysis(dto.getAnalysis());
        entity.setTechnicalSpecification(dto.getTechnicalSpecification());
        entity.setMemory(dto.getMemory());
        entity.setRam(dto.getRam());
        entity.setPrice(dto.getPrice());

        BrandEntity brand = brandService.get(dto.getBrandId());
        entity.setBrend(brand);
        if (dto.getCategoryId() != null) {
            CategoryEntity category = categoryService.getId(dto.getCategoryId());
            entity.setCategory(category);
        }
        CategoryEntity categoryParent = categoryService.getId(dto.getCategoryParentId());
        entity.setCategoryParent(categoryParent);

        ProfileEntity seller = new ProfileEntity();
        seller.setId(profileId);
        entity.setSeller(seller);
        entity.setStatus(ProductStatus.NOT_ON_SALE);
        productRepository.save(entity);
        productColorService.create(entity,dto.getColorList()); // color
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


    public void updateByStatus(String productId, String pId) {
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

    public void deleteByProduct(String productId, String pId) {
        Optional<ProductEntity> optional = productRepository.findById(productId);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Product not found ");
        }
        ProductEntity product = optional.get();
        if (product.getVisible().equals(false)) {
            productRepository.deleteByProductVisible(productId, pId);
        } else if (product.getVisible().equals(true)) {
            productRepository.deleteByNotProductVisible(productId);
        }

    }


    public List<ProductDTO> getLastPhone(String brand,String categoryKey) {
        List<ProductEntity> articlePage = productRepository.findLastPhone(brand,categoryKey
                );

        List<ProductDTO> dtoList = new LinkedList<>();
        articlePage.forEach(productEntity -> {
            dtoList.add(shortDTOInfo(productEntity));
        });
        return dtoList;
    }

    public List<ProductDTO> getLast5ArticleBy() {
        Pageable pageable = PageRequest.of(0, 8);
        Page<ProductEntity> articlePage = productRepository.findLastBy(pageable);
        List<ProductDTO> dtoList = new LinkedList<>();
        articlePage.getContent().forEach(productEntity -> {
            dtoList.add(shortDTOInfo(productEntity));
        });
        return dtoList;
    }

    private ProductDTO shortDTOInfo(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setModel(entity.getModel());
        dto.setPrice(entity.getPrice());
        // TODO image
        return dto;
    }
    private ProductDTO shortDTOInfo(ProductShortInfo entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setModel(entity.getModel());
        dto.setPrice(entity.getPrice());
        // TODO image
        return dto;
    }
}
