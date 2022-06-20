package com.company.controller;

import com.company.dto.CategoryDTO;
import com.company.enums.Language;
import com.company.enums.ProfileRole;
import com.company.service.CategoryService;
import com.company.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        PageImpl response = categoryService.pagination(page, size);
        return ResponseEntity.ok().body(response);
    }

    // SECURED
    @PostMapping("/admin")
    public ResponseEntity<?> create(@RequestBody CategoryDTO categoryDto,
                                    HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        categoryService.create(categoryDto);
        return ResponseEntity.ok().body("Successfully created");
    }

    @GetMapping("/admin")
    public ResponseEntity<List<CategoryDTO>> getList( HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        List<CategoryDTO> list = categoryService.getListOnlyForAdmin();
        return ResponseEntity.ok().body(list);
    }


    @PutMapping("/admin/{id}")
    private ResponseEntity<?> update(@PathVariable("id") String id,
                                     @RequestBody CategoryDTO dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        categoryService.update(id, dto);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @DeleteMapping("/admin/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") String id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        categoryService.delete(id);
        return ResponseEntity.ok().body("Successfully deleted");
    }



}
