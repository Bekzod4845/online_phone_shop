package com.company.service;

import com.company.entity.AttachEntity;
import com.company.exp.BadRequestException;
import com.company.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class AttachService {
    @Autowired
    private AttachRepository attachRepository;

    @Value(value = "${upload.folder}")
    private String uploadFolder;


    public void  upload(MultipartFile multipartFile){
        AttachEntity attachEntity = new AttachEntity();
        attachEntity.setOriginalName(multipartFile.getOriginalFilename());
        attachEntity.setExtension(getExtension(multipartFile.getOriginalFilename()));
        attachEntity.setFileSize(multipartFile.getSize());
        attachEntity.setContentType(multipartFile.getContentType());

        attachRepository.save(attachEntity);
        Date now = new Date();
        File uploadFolder = new File(String.format("%s/upload_files/%d/",this.uploadFolder,getYmDString()));
        if (!uploadFolder.exists() && uploadFolder.mkdirs()){
            System.out.println("created mkdirs");
        }
        attachEntity.setId(attachEntity.getId());
        attachEntity.setUploadPath(String.format("upload_files/%d/%s.%s",
                getYmDString(),
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

    public String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day; // 2022/04/23
    }
    private String getExtension(String fileName){
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
    public AttachEntity getById(String id){
        Optional<AttachEntity> byId = attachRepository.findById(id);
        if (byId.isEmpty()){
            throw new BadRequestException("not fount file");
        }
        return byId.get();
    }


}
