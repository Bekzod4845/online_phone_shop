package com.company.controller;

import com.company.common.ApiResponse;
import com.company.dto.TypesDTO;
import com.company.enums.Language;
import com.company.enums.ProfileRole;
import com.company.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/types")
public class TypesController {
    @Autowired
    private TypesService typesService;

    // PUBLIC
    @GetMapping("/public/list")
    public ResponseEntity<List<TypesDTO>> getList(@RequestHeader(value = "Accept-Language" ,defaultValue = "UZ")Language lang) {
        List<TypesDTO> list = typesService.getList(lang);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/public/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        PageImpl response = typesService.pagination(page, size);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // SECURED

    @PostMapping("/admin/list")
    public ResponseEntity<ApiResponse> create(@RequestBody TypesDTO dto) {
        typesService.create(dto);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully created type"),HttpStatus.CREATED);
    }


    @GetMapping("/admin/create")
    public ResponseEntity<List<TypesDTO>> getList() {
        List<TypesDTO> list = typesService.getListOnlyForAdmin();
       return new ResponseEntity<>(list,HttpStatus.OK);
    }


    @PutMapping("/admin/{id}")
    private ResponseEntity<ApiResponse> update(@PathVariable("id") Integer id,
                                     @RequestBody TypesDTO dto) {
        typesService.update(id, dto);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully updated type"),HttpStatus.OK);
    }
    @DeleteMapping("/admin/{id}")
    private ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id) {
        typesService.delete(id);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully updated type"),HttpStatus.OK);
    }


}


