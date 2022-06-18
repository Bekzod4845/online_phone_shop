package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.enums.ProfileRole;
import com.company.service.ProfileService;
import com.company.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/profile")
@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/admin/create")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto, HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        ProfileDTO profileDTO = profileService.create(dto);
        return ResponseEntity.ok(profileDTO);
    }
    @PutMapping ("/admin/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") Integer id,
                                             @RequestBody ProfileDTO dto,
                                             HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        profileService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/profileBlock/{id}")
    public ResponseEntity<ProfileDTO> profileBlock(@PathVariable("id") Integer id,
                                                   HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        profileService.profileBlock(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/admin/{id}")
    public ResponseEntity<ProfileDTO> profileDelete(@PathVariable("id") Integer id,
                                                    HttpServletRequest request) {
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        profileService.profileDelete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileDTO> detailUpdate(@RequestBody ProfileDTO dto,
                                                   HttpServletRequest request) {
        Integer id = HttpHeaderUtil.getId(request);
        profileService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAll(HttpServletRequest request){
        HttpHeaderUtil.getId(request,ProfileRole.ADMIN);
        List<ProfileDTO> all = profileService.getAll();
        return ResponseEntity.ok().body(all);
    }


}
