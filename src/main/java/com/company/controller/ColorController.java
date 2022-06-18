package com.company.controller;

import com.company.dto.CategoryDTO;
import com.company.dto.ColorDTO;
import com.company.enums.Language;
import com.company.enums.ProfileRole;
import com.company.service.ColorService;
import com.company.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/color")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping("/public")
    public ResponseEntity<List<ColorDTO>> getList(@RequestHeader(value = "Accept-Language" ,defaultValue = "UZ") Language lang) {
        List<ColorDTO> list = colorService.getList(lang);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        PageImpl response = colorService.pagination(page, size);
        return ResponseEntity.ok().body(response);
    }
    // SECURED
    @PostMapping("/admin")
    public ResponseEntity<?> create(@RequestBody ColorDTO colorDTO ,
                                    HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        colorService.create(colorDTO);
        return ResponseEntity.ok().body("Successfully created");
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ColorDTO>> getList( HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        List<ColorDTO> list = colorService.getListOnlyForAdmin();
        return ResponseEntity.ok().body(list);
    }


    @PutMapping("/admin/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody ColorDTO dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        colorService.update(id, dto);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @DeleteMapping("/admin/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        colorService.delete(id);
        return ResponseEntity.ok().body("Successfully deleted");
    }

}
