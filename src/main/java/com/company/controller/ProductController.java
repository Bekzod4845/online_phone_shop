package com.company.controller;

import com.company.dto.ProductCreateDTO;
import com.company.dto.ProductDTO;
import com.company.enums.ProfileRole;
import com.company.service.ProductService;
import com.company.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProfileService profileService;

    //PUBLIC
    @GetMapping("/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "4") int size) {
        PageImpl response = productService.pagination(page, size);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/list/")
    public ResponseEntity<List<ProductDTO>> getLastProductBrandAndCategoryKey(@RequestParam(name = "brand") String brand,@RequestParam(name = "categoryKey") String  categoryKey) {
        List<ProductDTO> response = productService.getLastPhone(brand,categoryKey);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> getLast() {
        List<ProductDTO> response = productService.getLast5ArticleBy();
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/seller")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductCreateDTO dto) {
        String id = profileService.getCurrentUser().getProfile().getId();
        ProductDTO response = productService.create(dto,id);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PutMapping("/seller/status/{id}")
    public ResponseEntity<Void> changeStatus(@PathVariable("id") String productId) {
        String pId = profileService.getCurrentUser().getProfile().getId();
          productService.updateByStatus(productId, pId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/seller/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String productId) {
        String pId = profileService.getCurrentUser().getProfile().getId();
        productService.deleteByProduct(productId, pId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
