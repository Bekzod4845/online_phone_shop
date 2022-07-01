package com.company.mapper.CategoryMapper;


import com.company.dto.CategoryDTO;
import com.company.dto.brand.BrandDTO;
import com.company.entity.BrandEntity;
import com.company.entity.CategoryEntity;
import com.company.enums.Language;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-07-13T14:45:56+0200",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17"
)
@Component
public class CategoryStructMapperImpl implements CategoryStructMapper {

    @Override
    public CategoryDTO categoryEntityToCategoryDTO(CategoryEntity categoryEntity) {
        if (categoryEntity == null){
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setKey(categoryEntity.getKey());
        categoryDTO.setNameEn(categoryEntity.getNameEn());
        categoryDTO.setNameRu(categoryEntity.getNameRu());
        categoryDTO.setNameUz(categoryEntity.getNameUz());
        return categoryDTO;
    }

    @Override
    public CategoryDTO categoryEntityToCategoryDTOSelectLang(CategoryEntity categoryEntity, Language lang) {
        if (categoryEntity == null) {
            return null;
        }
        CategoryDTO dto = new CategoryDTO();
        dto.setId(categoryEntity.getId());
        dto.setKey(categoryEntity.getKey());
        switch (lang) {
            case RU:
                dto.setName(categoryEntity.getNameRu());
                break;
            case EN:
                dto.setName(categoryEntity.getNameEn());
                break;
            case UZ:
                dto.setName(categoryEntity.getNameUz());
                break;

        }
        return dto;
    }


    @Override
    public CategoryEntity categoryDTOToCategoryEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        CategoryEntity category = new CategoryEntity();
        category.setId(categoryDTO.getId());
        category.setKey(categoryDTO.getKey());
        category.setNameUz(categoryDTO.getNameUz());
        category.setNameEn(categoryDTO.getNameEn());
        category.setNameRu(categoryDTO.getNameRu());

        return category;
    }

    @Override
    public CategoryEntity categoryEntityToCategoryDTOUpdate(CategoryDTO categoryDTO, CategoryEntity categoryEntity) {
        if (categoryDTO == null) {
            return null;
        }
        if (categoryEntity == null){
            return null;
        }
        categoryEntity.setId(categoryDTO.getId());
        categoryEntity.setKey(categoryDTO.getKey());
        categoryEntity.setNameUz(categoryDTO.getNameUz());
        categoryEntity.setNameEn(categoryDTO.getNameEn());
        categoryEntity.setNameRu(categoryDTO.getNameRu());

        return categoryEntity;
    }
}
