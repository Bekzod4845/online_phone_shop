package com.company.controller;

import com.company.dto.ProductCreateDTO;
import com.company.dto.ProductDTO;
import com.company.enums.ProfileRole;
import com.company.service.ProductService;
import com.company.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/adm")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductCreateDTO dto,
                                             HttpServletRequest request) {
        Integer profileId = HttpHeaderUtil.getId(request, ProfileRole.SELLER);
        ProductDTO response = productService.create(dto, profileId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/adm/status/{id}")
    public ResponseEntity<Void> changeStatus(@PathVariable("id") String praductId,
                                             HttpServletRequest request) {
        Integer pId = HttpHeaderUtil.getId(request, ProfileRole.SELLER);
        productService.updateByStatus(praductId, pId);
        return ResponseEntity.ok().build();
    }
}
