package com.company.controller;

import com.company.dto.ProductAttachDTO;
import com.company.dto.ShortInfoProduct;
import com.company.enums.ProfileRole;
import com.company.service.ProductAttachmentService;
import com.company.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("productAdd")
public class ProductAttachController {
    @Autowired
    private ProductAttachmentService productAttachmentService;
    @PostMapping("/admin/productAddAttach")
    public ResponseEntity<?> addProduct(@RequestBody ProductAttachDTO productAttachDTO, HttpServletRequest request){
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        productAttachmentService.save(productAttachDTO);
        return ResponseEntity.ok("Successfully add attach");
    }


    @GetMapping("/admin/productList")
    public ResponseEntity<?>getList(HttpServletRequest request){
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id") String id,HttpServletRequest request){
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        productAttachmentService.updateChangeStatus(id);
        return ResponseEntity.ok().body("successfully visible product");
    }


    @GetMapping("/list")
    public ResponseEntity<?>list(){
       List<ShortInfoProduct> productAttachDTOS=  productAttachmentService.getProductAttachList();
        return ResponseEntity.ok().body(productAttachDTOS);
    }





}
