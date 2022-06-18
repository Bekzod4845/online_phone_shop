package com.company.controller;

import com.company.dto.TypesDTO;
import com.company.enums.Language;
import com.company.enums.ProfileRole;
import com.company.service.TypesService;
import com.company.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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
    @GetMapping("/public")
    public ResponseEntity<List<TypesDTO>> getList(@RequestHeader(value = "Accept-Language" ,defaultValue = "UZ")Language lang) {
        List<TypesDTO> list = typesService.getList(lang);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        PageImpl response = typesService.pagination(page, size);
        return ResponseEntity.ok().body(response);
    }

    // SECURED

    @PostMapping("/admin")
    public ResponseEntity<?> create(@RequestBody TypesDTO dto, HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        typesService.create(dto);
        return ResponseEntity.ok().body("Succsessfully created");
    }


    @GetMapping("/admin")
    public ResponseEntity<List<TypesDTO>> getlist(HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        List<TypesDTO> list = typesService.getListOnlyForAdmin();
        return ResponseEntity.ok().body(list);
    }


    @PutMapping("/admin/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody TypesDTO dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        typesService.update(id, dto);
        return ResponseEntity.ok().body("Succsessfully updated");
    }

    @DeleteMapping("/admin/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        typesService.delete(id);
        return ResponseEntity.ok().body("Sucsessfully deleted");
    }


}


