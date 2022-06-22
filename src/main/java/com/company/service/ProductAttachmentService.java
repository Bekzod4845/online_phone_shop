package com.company.service;

import com.company.dto.ProductAttachDTO;
import com.company.entity.AttachEntity;
import com.company.entity.ColorEntity;
import com.company.entity.ProductAttachEntity;
import com.company.entity.ProductEntity;
import com.company.exp.BadRequestException;
import com.company.repository.ProductAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Optional;

@Service
public class ProductAttachmentService {
    @Autowired
    private ProductAttachmentRepository productAttachmentRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private AttachService attachService;

    @Autowired
    private ColorService colorService;

    public void save(ProductAttachDTO productAttachDTO) {
        ProductAttachEntity productAttachEntity = new ProductAttachEntity();
        Optional<ProductAttachEntity> productAttachEntityOptional = productAttachmentRepository.findById(productAttachDTO.getId());
        if (productAttachEntityOptional.isPresent()){
            throw new BadRequestException("product exsits");
        }
        productAttachEntity.setId(productAttachDTO.getId());

        ProductEntity productEntity = productService.getId(productAttachDTO.getProductId());
        productAttachEntity.setProduct(productEntity);

        ColorEntity colorEntity = colorService.get(productAttachDTO.getColorKey());
        productAttachEntity.setColor(colorEntity);

       List<AttachEntity>attachEntities = attachService.getAttachs(productAttachDTO.getAttachList());


    }
}
