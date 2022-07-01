package com.company.mapper.CategoryMapper;


import com.company.dto.CategoryDTO;
import com.company.dto.brand.BrandDTO;
import com.company.entity.BrandEntity;
import com.company.entity.CategoryEntity;
import com.company.enums.Language;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface CategoryStructMapper {

    CategoryDTO categoryEntityToCategoryDTO(CategoryEntity categoryEntity);

    CategoryDTO categoryEntityToCategoryDTOSelectLang(CategoryEntity categoryEntity, Language language);

    CategoryEntity categoryDTOToCategoryEntity(CategoryDTO categoryDTO);

    CategoryEntity categoryEntityToCategoryDTOUpdate(CategoryDTO categoryDTO,CategoryEntity categoryEntity);


}
