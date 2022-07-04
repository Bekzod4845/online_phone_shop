package com.company.controller;

import com.company.common.ApiResponse;
import com.company.dto.CategoryDTO;
import com.company.enums.Language;
import com.company.enums.ProfileRole;
import com.company.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    // PUBLIC
    @GetMapping("/public")
    public ResponseEntity<List<CategoryDTO>> getList(@RequestHeader(value = "Accept-Language" ,defaultValue = "UZ") Language lang) {
        List<CategoryDTO> list = categoryService.getList(lang);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        PageImpl response = categoryService.pagination(page, size);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // SECURED
    @PostMapping("/admin")
    public ResponseEntity<ApiResponse> create(@RequestBody CategoryDTO categoryDto) {

        categoryService.create(categoryDto);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully created category"), HttpStatus.CREATED);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<CategoryDTO>> getList() {
        List<CategoryDTO> list = categoryService.getListOnlyForAdmin();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    @PutMapping("/admin/{id}")
    private ResponseEntity<ApiResponse> update(@PathVariable("id") String id,
                                     @RequestBody CategoryDTO dto) {

        categoryService.update(id, dto);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Successfully update category"),HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    private ResponseEntity<ApiResponse> delete(@PathVariable("id") String id) {
        categoryService.delete(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Successfully delete category"),HttpStatus.OK);
    }



}
