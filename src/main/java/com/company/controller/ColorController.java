package com.company.controller;

import com.company.common.ApiResponse;
import com.company.dto.ColorDTO;
import com.company.enums.Language;
import com.company.enums.ProfileRole;
import com.company.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/color")
public class ColorController {
    @Autowired
    private ColorService colorService;
   //Public
    @GetMapping("/public")
    public ResponseEntity<List<ColorDTO>> getList(@RequestHeader(value = "Accept-Language" ,defaultValue = "UZ") Language lang) {
        List<ColorDTO> list = colorService.getList(lang);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        PageImpl response = colorService.pagination(page, size);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    // SECURED
    @PostMapping("/admin")
    public ResponseEntity<ApiResponse> create(@RequestBody ColorDTO colorDTO ) {

        colorService.create(colorDTO);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully created color"),HttpStatus.CREATED);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ColorDTO>> getList( ) {

        List<ColorDTO> list = colorService.getListOnlyForAdmin();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    @PutMapping("/admin/{id}")
    private ResponseEntity<ApiResponse> update(@PathVariable("id") Integer id,
                                     @RequestBody ColorDTO dto) {

        colorService.update(id, dto);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully updated color"),HttpStatus.OK);
    }


    @DeleteMapping("/admin/{id}")
    private ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id) {

        colorService.delete(id);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully deleted color"),HttpStatus.OK);
    }

}
