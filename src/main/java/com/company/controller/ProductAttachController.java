package com.company.controller;

import com.company.dto.ProductAttachDTO;
import com.company.enums.ProfileRole;
import com.company.service.ProductAttachmentService;
import com.company.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("productAdd")
public class ProductAttachController {
    @Autowired
    private ProductAttachmentService productAttachmentService;
    @PostMapping("/admin/productAddattach")
    public ResponseEntity<?> addProduct(@RequestBody ProductAttachDTO productAttachDTO, HttpServletRequest request){
        String profileId = HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        productAttachmentService.save(productAttachDTO);
   return ResponseEntity.ok().body("cdscdscadca");
    }
}
