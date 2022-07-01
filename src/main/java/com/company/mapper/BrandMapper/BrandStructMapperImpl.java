package com.company.mapper.BrandMapper;


import com.company.dto.brand.BrandDTO;
import com.company.entity.BrandEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-07-13T14:45:56+0200",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17"
)
@Component
public class BrandStructMapperImpl implements BrandStructMapper {
    @Override
    public BrandDTO bookEntityToBookDTO(BrandEntity brand) {
        if (brand == null) {
            return null;
        }
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(brand.getId());
        brandDTO.setName(brand.getName());

        return brandDTO;
    }

    @Override
    public BrandDTO bookEntityToBookDTOFull(BrandEntity brand) {
        if (brand == null) {
            return null;
        }
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(brand.getId());
        brandDTO.setName(brand.getName());
        brandDTO.setVisible(brand.getVisible());
        brandDTO.setCreatedDate(brand.getCreatedDate());
        return brandDTO;
    }

    @Override
    public BrandEntity bookDTOToBookEntity(BrandDTO brandDTO) {
        if (brandDTO == null){
            return null;
        }

        BrandEntity entity = new BrandEntity();
        entity.setName(brandDTO.getName());

        return entity;
    }

    @Override
    public BrandEntity bookDTOToBookUpdate(BrandDTO brandDTO, BrandEntity brand) {
        if (brandDTO == null){
            return null;
        }
        if (brand == null) {
            return null;
        }
        brand.setName(brandDTO.getName());
        return brand;
    }
}
