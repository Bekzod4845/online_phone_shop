package com.company.controller;

import com.company.common.ApiResponse;
import com.company.dto.brand.BrandDTO;

import com.company.enums.ProfileRole;
import com.company.service.BrandService;
import com.company.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "5") int page,
                                                  @RequestParam(value = "size", defaultValue = "2") int size) {
        PageImpl response = brandService.pagination(page, size);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



    // SECURED
    @PostMapping("/admin")
    public ResponseEntity<ApiResponse> create(@RequestBody BrandDTO brandDTO, HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        brandService.create(brandDTO);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully created brand"),HttpStatus.CREATED);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<BrandDTO>> getList(HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        List<BrandDTO> list = brandService.getListOnlyForAdmin();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    @PutMapping("/admin/{id}")
    private ResponseEntity<ApiResponse> update(@PathVariable("id") Integer id,
                                     @RequestBody BrandDTO dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        brandService.update(id, dto);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully updated brand"),HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    private ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        brandService.delete(id);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully deleted brand"),HttpStatus.OK);
    }


}
