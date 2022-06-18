package com.company.service;

import com.company.dto.ColorDTO;
import com.company.entity.ColorEntity;
import com.company.enums.Language;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;
    public ColorDTO create(ColorDTO dto) {
        isValid(dto);
        Optional<ColorEntity> optional = colorRepository.findByKey(dto.getKey());
        if (optional.isPresent()){
            throw new BadRequestException("color is exsits");
        }
        ColorEntity entity = new ColorEntity();
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setCode(dto.getCode());
        colorRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public void update(Integer id, ColorDTO dto) {
        isValid(dto);
        ColorEntity entity = get(id);
        System.out.println("success update color");


    }

    public void delete(Integer id) {
        ColorEntity entity = get(id);
        entity.setVisible(false);
        System.out.println("success delete color");
        colorRepository.save(entity);
    }

    public List<ColorDTO> getList(Language lang) {
        Iterable<ColorEntity> all = colorRepository.findAllByVisible(true);
        List<ColorDTO> dtoList = new LinkedList<>();
        all.forEach(colorEntity -> {
            ColorDTO dto = new ColorDTO();
            dto.setKey(colorEntity.getKey());
            dto.setCode(colorEntity.getCode());
            switch (lang) {
                case RU:
                    dto.setName(colorEntity.getNameRu());
                    break;
                case EN:
                    dto.setName(colorEntity.getNameEn());
                    break;
                case UZ:
                    dto.setName(colorEntity.getNameUz());
                    break;
            }
            dtoList.add(dto);
        });
        return dtoList;
    }

    public List<ColorDTO> getListOnlyForAdmin() {

        Iterable<ColorEntity> all = colorRepository.findAll();
        List<ColorDTO> dtoList = new LinkedList<>();

        all.forEach(categoryEntity -> {
            dtoList.add(toDTO(categoryEntity));
        });
        return dtoList;
    }

    public ColorEntity get(Integer id) {
        return colorRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Color Not found");
        });
    }
    public ColorEntity get(String key) {
        return colorRepository.findByKey(key).orElseThrow(() -> {
            throw new ItemNotFoundException("Color Not found");
        });
    }

    public ColorDTO toDTO(ColorEntity entity) {
        ColorDTO dto = new ColorDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        dto.setCode(entity.getCode());
        return dto;
    }


    private void isValid(ColorDTO dto) {
        if (dto.getKey() == null){
            throw new BadRequestException("is wrong key");
        }
        if (dto.getNameUz() == null){
            throw new BadRequestException("is wrong name uz");
        }
        if (dto.getNameEn() == null){
            throw new BadRequestException("is wrong name en");
        }
        if (dto.getNameRu() == null){
            throw new BadRequestException("is wrong name ru");
        }
        if (dto.getCode() == null){
            throw new BadRequestException("is wrong code");
        }
    }


    public PageImpl pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ColorEntity> all = colorRepository.findAll(pageable);
        List<ColorEntity> list = all.getContent();
        List<ColorDTO> dtoList = new LinkedList<>();
        list.forEach(colorEntity -> {
            dtoList.add(toDTO(colorEntity));
        });
        return new PageImpl(dtoList,pageable, all.getTotalElements());
    }

}
