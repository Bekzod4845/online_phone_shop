package com.company.controller;

import com.company.dto.brand.BrandDTO;

import com.company.enums.ProfileRole;
import com.company.service.BrandService;
import com.company.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    //PUBLIC
    @GetMapping("/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        PageImpl response = brandService.pagination(page, size);
        return ResponseEntity.ok().body(response);
    }



    // SECURED
    @PostMapping("/admin")
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO, HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        brandService.create(brandDTO);
        return ResponseEntity.ok().body("Successfully created");
    }

    @GetMapping("/admin")
    public ResponseEntity<List<BrandDTO>> getList(HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        List<BrandDTO> list = brandService.getListOnlyForAdmin();
        return ResponseEntity.ok().body(list);
    }


    @PutMapping("/admin/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody BrandDTO dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        brandService.update(id, dto);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @DeleteMapping("/admin/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        brandService.delete(id);
        return ResponseEntity.ok().body("Successfully deleted");
    }


}
