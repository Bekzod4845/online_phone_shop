package com.company.service;

import com.company.entity.AttachEntity;
import com.company.enums.AttachStatus;
import com.company.exp.BadRequestException;
import com.company.repository.AttachRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class AttachService {
    @Autowired
    private AttachRepository attachRepository;

    @Value(value = "${upload.folder}")
    private String uploadFolder;


    public void  save (MultipartFile multipartFile){
        AttachEntity attachEntity = new AttachEntity();
        attachEntity.setName(multipartFile.getOriginalFilename());
        attachEntity.setOriginalName(getOriginalName(multipartFile.getOriginalFilename()));
        attachEntity.setFileSize(multipartFile.getSize());
        attachEntity.setContentType(multipartFile.getContentType());
        attachEntity.setStatus(AttachStatus.NOT_ACTIVE);
        attachRepository.save(attachEntity);
        Date now = new Date();
        File uploadFolder = new File(String.format("%s/upload_files/%d/%d/%d/",this.uploadFolder,1900+now.getYear(),1 + now.getMonth(),now.getDate()));
        if (!uploadFolder.exists() && uploadFolder.mkdirs()){
            System.out.println("created mkdirs");
        }
        attachEntity.setId(attachEntity.getId());
        attachEntity.setUploadPath(String.format("upload_files/%d/%d/%d/%s.%s"
                ,1900+now.getYear()
                ,1 + now.getMonth(),
                now.getDate(),
                attachEntity.getId(),
                attachEntity.getOriginalName()));
        attachRepository.save(attachEntity);

        uploadFolder = uploadFolder.getAbsoluteFile();
        File file = new File(uploadFolder,String.format("%s.%s",attachEntity.getId(),attachEntity.getOriginalName()));
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getOriginalName(String fileName){
        String name = null;
        if (fileName != null && !fileName.isEmpty()){
            int dot = fileName.lastIndexOf(".");
            if (dot > 0 && dot <= fileName.length() - 2){
                 name = fileName.substring(dot + 1);
            }
        }
        return name;
    }


    @Transactional(readOnly = true)
    public AttachEntity getByHashId(String id){
        Optional<AttachEntity> byHashId = attachRepository.findById(id);
        if (byHashId.isEmpty()){
            throw new BadRequestException("not fount file");
        }
        return byHashId.get();
    }


}
