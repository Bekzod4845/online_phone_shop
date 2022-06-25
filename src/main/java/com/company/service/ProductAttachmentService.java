package com.company.service;

import com.company.dto.AttachDTO;
import com.company.dto.ColorDTO;
import com.company.dto.ProductAttachDTO;
import com.company.dto.ProductDTO;
import com.company.entity.AttachEntity;
import com.company.entity.ColorEntity;
import com.company.entity.ProductAttachEntity;
import com.company.entity.ProductEntity;
import com.company.exp.BadRequestException;
import com.company.repository.ProductAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Value("${server.url}")
    private String serverUrl;
    public ProductAttachDTO save(ProductAttachDTO productAttachDTO) {

        ProductAttachEntity productAttachEntity = new ProductAttachEntity();
        ProductEntity productEntity = productService.getId(productAttachDTO.getProductId());

        productAttachEntity.setProduct(productEntity);

        ColorEntity colorEntity = colorService.get(productAttachDTO.getColorId());
        productAttachEntity.setColor(colorEntity);




        productAttachDTO.getAttachList().forEach(attachId -> {
           AttachEntity attach = attachService.get(String.valueOf(attachId));
            boolean exists = productAttachmentRepository.existsAllByAttach(attach);
            if (exists) {
              throw new BadRequestException("exists image");
            }
            productAttachEntity.setAttach(attach);
       });


        productAttachmentRepository.save(productAttachEntity);

        ProductAttachDTO dto = new ProductAttachDTO();

        ColorDTO colorDTO = new ColorDTO();
        colorDTO.setKey(colorEntity.getKey());

        ProductDTO productDTO = new ProductDTO();
        productDTO.setModel(productEntity.getModel());
        productDTO.setPrice(productEntity.getPrice());

        AttachDTO attachDTO = new AttachDTO();
        attachDTO.setUrl(serverUrl + "/attach/open/" + productAttachEntity.getAttach().getId());
        dto.setAttachDTO(attachDTO);
        dto.setColorDTO(colorDTO);
        dto.setProductDTO(productDTO);


        return dto;
    }




}
