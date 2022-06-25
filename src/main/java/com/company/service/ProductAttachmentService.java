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
import com.company.exp.ItemNotFoundException;
import com.company.repository.ProductAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void save(ProductAttachDTO productAttachDTO) {


        ProductEntity productEntity = productService.getId(productAttachDTO.getProductId());



        ColorEntity colorEntity = colorService.get(productAttachDTO.getColorId());


//        productAttachDTO.getAttachList().forEach(attachId -> {
//           AttachEntity attach = attachService.get(String.valueOf(attachId));
//            boolean exists = productAttachmentRepository.existsAllByAttach(attach);
//            if (exists) {
//              throw new BadRequestException("exists image");
//            }
//            productAttachEntity.setAttach(attach);
//       });

        productAttachDTO.getAttachList().forEach(attachId ->{
            AttachEntity attach = attachService.get(attachId);
            boolean exists = productAttachmentRepository.existsByAttach(attach);
            if (exists) {
              throw new BadRequestException("exists image");
            }
            ProductAttachEntity productAttachEntity = new ProductAttachEntity();
            productAttachEntity.setProduct(productEntity);
            productAttachEntity.setColor(colorEntity);
            productAttachEntity.setAttach(attach);
            productAttachmentRepository.save(productAttachEntity);
        } );

    }

    public  ProductAttachEntity get(String id){
        return productAttachmentRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("product Not found");
        });

    }


    public void updateChangeStatus(String id) {
        ProductAttachEntity productAttachEntity = get(id);
        productAttachmentRepository.updateVisible(productAttachEntity.getId());
    }
}
