package com.company.controller;

import com.company.common.ApiResponse;
import com.company.dto.brand.BrandDTO;

import com.company.enums.ProfileRole;
import com.company.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "4") int page,
                                                  @RequestParam(value = "size", defaultValue = "1") int size) {
        PageImpl response = brandService.pagination(page, size);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // SECURED
    @PostMapping("/admin")
    public ResponseEntity<BrandDTO> create(@RequestBody BrandDTO dto) {
        BrandDTO brandDTO = brandService.create(dto);
        return new ResponseEntity<>(brandDTO,HttpStatus.CREATED);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<BrandDTO>> getList() {

        List<BrandDTO> list = brandService.getListOnlyForAdmin();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    @PutMapping("/admin/{id}")
    private ResponseEntity<ApiResponse> update(@PathVariable("id") Integer id,
                                     @RequestBody BrandDTO dto) {

        brandService.update(id, dto);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully updated brand"),HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    private ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id
                                    ) {

        brandService.delete(id);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully deleted brand"),HttpStatus.OK);
    }


}
