package com.company.controller;

import com.company.dto.CategoryDTO;
import com.company.entity.AttachEntity;
import com.company.enums.ProfileRole;
import com.company.service.AttachService;
import com.company.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    @Value(value = "${upload.folder}")
    private String uploadFolder;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestParam(name = "file") MultipartFile file) {
        attachService.upload(file);
        return ResponseEntity.ok().body(file.getOriginalFilename()+" Successfully created");
    }

    @GetMapping("/preview/{id}")
    public ResponseEntity<?> preview(@PathVariable("id") String id) throws MalformedURLException {
        AttachEntity byId = attachService.getById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline; fileName=\"" + URLEncoder.encode(byId.getExtension()))
                .contentType(MediaType.parseMediaType(byId.getContentType()))
                .contentLength(byId.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s",uploadFolder,byId.getUploadPath())));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> download(@PathVariable("id") String id) throws MalformedURLException {
        AttachEntity byId = attachService.getById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\"" + URLEncoder.encode(byId.getExtension()))
                .contentType(MediaType.parseMediaType(byId.getContentType()))
                .contentLength(byId.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s",uploadFolder,byId.getUploadPath())));
    }


}
