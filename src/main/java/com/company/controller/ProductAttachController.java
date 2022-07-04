package com.company.controller;

import com.company.common.ApiResponse;
import com.company.dto.ProductAttachDTO;
import com.company.dto.ShortInfoProduct;
import com.company.enums.ProfileRole;
import com.company.service.ProductAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductAttachDTO productAttachDTO){
        productAttachmentService.save(productAttachDTO);
       return new ResponseEntity<>(new ApiResponse(true,"Successfully created product"), HttpStatus.CREATED);
    }


    @GetMapping("/admin/productList")
    public ResponseEntity<?>getList(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id") String id,HttpServletRequest request){
        productAttachmentService.updateChangeStatus(id);
        return new ResponseEntity<>(new ApiResponse(true ,"successfully  updated visible product"),HttpStatus.OK);
    }


    @GetMapping("/admin/list")
    public ResponseEntity<?>list(){
       List<ShortInfoProduct> productAttachDTOS=  productAttachmentService.getProductAttachList();
        return new ResponseEntity<>(productAttachDTOS,HttpStatus.OK);
    }





}
