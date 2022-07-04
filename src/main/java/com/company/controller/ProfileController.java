package com.company.controller;

import com.company.common.ApiResponse;
import com.company.dto.ProfileDTO;
import com.company.enums.ProfileRole;
import com.company.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse> create(@RequestBody ProfileDTO dto) {
         profileService.create(dto);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully created seller"), HttpStatus.CREATED);
    }
    @PutMapping ("/admin/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") String id,
                                            @RequestBody ProfileDTO dto) {
        profileService.update(id, dto);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully update seller"),HttpStatus.OK);
    }

    @PutMapping("/admin/profileBlock/{id}")
    public ResponseEntity<ApiResponse> profileBlock(@PathVariable("id") String id) {
        profileService.profileBlock(id);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully  profile block "),HttpStatus.OK);
    }

    @DeleteMapping ("/admin/{id}")
    public ResponseEntity<ApiResponse> profileDelete(@PathVariable("id") String id) {
        profileService.profileDelete(id);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully deleted seller"),HttpStatus.OK);
    }

//    @PutMapping("/updateDetail")
//    public ResponseEntity<ProfileDTO> detailUpdate(@RequestBody ProfileDTO dto,
//                                                   HttpServletRequest request) {
//        String id = HttpHeaderUtil.getId(request);
//        profileService.update(id, dto);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/admin")
    public ResponseEntity<List<ProfileDTO> > getAll(){
        List<ProfileDTO> all = profileService.getAll();
       return  new ResponseEntity<>(all,HttpStatus.OK);
    }


}
