package com.company.service;

import com.company.dto.brand.BrandDTO;
import com.company.entity.BrandEntity;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.mapper.BrandMapper.BrandStructMapper;
import com.company.repository.BrendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    private BrendRepository brendRepository;


    @Autowired
    private BrandStructMapper brandStructMapper;
    public void create(BrandDTO dto) {

        Optional<BrandEntity> optional = brendRepository.findByName(dto.getName());
        if (optional.isPresent()){
            throw new BadRequestException("brend is exsits");
        }
        brendRepository.save(brandStructMapper.bookDTOToBookEntity(dto));
    }

    public void update(Integer id, BrandDTO dto) {
        Optional<BrandEntity> optional = brendRepository.findById(id);
        if (!optional.isPresent()){
           throw  new  BadRequestException("Not fount brand");
        }
        Boolean aBoolean = brendRepository.existsByName(dto.getName());
        if (aBoolean){
          throw   new ItemNotFoundException("exsist brend");
        }
        BrandEntity entity = optional.get();
        brendRepository.save(brandStructMapper.bookDTOToBookUpdate(dto,entity));
    }

    public void delete(Integer id) {
        BrandEntity entity = get(id);
        entity.setVisible(false);
        brendRepository.save(entity);
    }
    public List<BrandDTO> getListOnlyForAdmin() {

        Iterable<BrandEntity> all = brendRepository.findAll();
        List<BrandDTO> dtoList = new LinkedList<>();

        all.forEach(brandEntity -> {
            dtoList.add(brandStructMapper.bookEntityToBookDTOFull(brandEntity));
        });

       return dtoList;
    }

    public BrandEntity get(Integer id) {
        return brendRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Brand Not found");
        });
    }







    public PageImpl pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<BrandEntity> all = brendRepository.findAll(pageable);
        List<BrandEntity> list = all.getContent();
        List<BrandDTO> dtoList = new LinkedList<>();
        list.forEach(brandEntity -> {
            dtoList.add(brandStructMapper.bookEntityToBookDTO(brandEntity));
        });
        return new PageImpl(dtoList,pageable, all.getTotalElements());
    }


}
