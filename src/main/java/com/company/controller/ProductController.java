package com.company.controller;

import com.company.dto.ProductCreateDTO;
import com.company.dto.ProductDTO;
import com.company.enums.ProfileRole;
import com.company.service.ProductService;
import com.company.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //PUBLIC
    @GetMapping("/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "4") int size) {
        PageImpl response = productService.pagination(page, size);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/list/")
    public ResponseEntity<List<ProductDTO>> getLastProductBrandAndCategoryKey(@RequestParam(name = "brand") String brand,@RequestParam(name = "categoryKey") String  categoryKey) {
        List<ProductDTO> response = productService.getLastPhone(brand,categoryKey);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> getLast() {
        List<ProductDTO> response = productService.getLast5ArticleBy();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/seller")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductCreateDTO dto,
                                             HttpServletRequest request) {
        Integer profileId = HttpHeaderUtil.getId(request, ProfileRole.SELLER);
        ProductDTO response = productService.create(dto, profileId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/seller/status/{id}")
    public ResponseEntity<Void> changeStatus(@PathVariable("id") String praductId,
                                             HttpServletRequest request) {
        Integer pId = HttpHeaderUtil.getId(request, ProfileRole.SELLER);
        productService.updateByStatus(praductId, pId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/seller/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String praductId,
                                             HttpServletRequest request) {
        Integer pId = HttpHeaderUtil.getId(request, ProfileRole.SELLER);
        productService.deleteByProduct(praductId, pId);
        return ResponseEntity.ok().build();
    }
}
