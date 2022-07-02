package com.company.mapper.BrandMapper;


import com.company.dto.brand.BrandDTO;
import com.company.entity.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface BrandStructMapper {
    BrandDTO bookEntityToBookDTO(BrandEntity brand);

    BrandDTO bookEntityToBookDTOFull(BrandEntity brand);

    BrandEntity bookDTOToBookEntity(BrandDTO brandDTO);

    BrandEntity bookDTOToBookUpdate(BrandDTO brandDTO,BrandEntity brand);

}
