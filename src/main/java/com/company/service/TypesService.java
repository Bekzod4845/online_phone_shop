package com.company.service;


import com.company.dto.TypesDTO;
import com.company.entity.TypesEntity;
import com.company.enums.Language;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.TypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TypesService {

    @Autowired
    private TypesRepository typesRepository;
    public void create(TypesDTO dto) {

        Optional<TypesEntity> optional = typesRepository.findByKey(dto.getKey());
        if (optional.isPresent()){
            throw new BadRequestException(" type is exsits");
        }
        TypesEntity entity = new TypesEntity();
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        typesRepository.save(entity);

    }

    public List<TypesDTO> getList(Language lang) {
        Iterable<TypesEntity> all = typesRepository.findAllByVisible(true);
        List<TypesDTO> dtoList = new LinkedList<>();

        all.forEach(typesEntity ->  {
            TypesDTO dto = new TypesDTO();
            dto.setKey(typesEntity.getKey());
            switch (lang) {
                case RU:
                    dto.setName(typesEntity.getNameRu());
                    break;
                case EN:
                    dto.setName(typesEntity.getNameEn());
                    break;
                case UZ:
                    dto.setName(typesEntity.getNameUz());
                    break;
            }
            dtoList.add(dto);
        });
        return dtoList;
    }

    public List<TypesDTO> getListOnlyForAdmin() {

        Iterable<TypesEntity> all = typesRepository.findAll();
        List<TypesDTO> dtoList = new LinkedList<>();

        all.forEach(typesEntity -> {
            dtoList.add(toDTO(typesEntity));
        });
        return dtoList;
    }

    public TypesDTO toDTO(TypesEntity entity) {
        TypesDTO dto = new TypesDTO();
         dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        return dto;
    }

    public void update(Integer id, TypesDTO dto) {
        TypesEntity entity = get(id);
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        System.out.println("success update  type");
        typesRepository.save(entity);
    }

    public void delete(Integer id) {
        TypesEntity entity = get(id);
        entity.setVisible(false);
        System.out.println("success delete  type");
    }


    public TypesEntity get(Integer id) {
        return typesRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("type Not found");
        });
    }


    public PageImpl pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<TypesEntity> all = typesRepository.findAll(pageable);
        List<TypesEntity> list = all.getContent();
        List<TypesDTO> dtoList = new LinkedList<>();
        list.forEach(typesEntity -> {
            dtoList.add(toDTO(typesEntity));
        });
        return new PageImpl(dtoList,pageable, all.getTotalElements());
    }



}
