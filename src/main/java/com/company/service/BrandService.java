package com.company.service;

import com.company.dto.BrandDTO;
import com.company.entity.BrandEntity;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
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

    public BrandDTO create(BrandDTO dto) {

        Optional<BrandEntity> optional = brendRepository.findByName(dto.getName());
        if (optional.isPresent()){
            throw new BadRequestException("brend is exsits");
        }

        BrandEntity entity = new BrandEntity();
        entity.setName(dto.getName());
        brendRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public void update(Integer id, BrandDTO dto) {
        isValid(dto);
        BrandEntity entity = get(id);
        entity.setName(dto.getName());
        System.out.println("success update brend");
        brendRepository.save(entity);

    }

    public void delete(Integer id) {
        BrandEntity entity = get(id);
        entity.setVisible(false);
        System.out.println("success delete brend");
        brendRepository.save(entity);
    }
    public List<BrandDTO> getListOnlyForAdmin() {

        Iterable<BrandEntity> all = brendRepository.findAll();
        List<BrandDTO> dtoList = new LinkedList<>();

        all.forEach(brandEntity -> {
            dtoList.add(toDTO(brandEntity));
        });

       return dtoList;
    }

    public BrandEntity get(Integer id) {
        return brendRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Brend Not found");
        });
    }

    public BrandEntity get(String name) {
        return brendRepository.findByName(name).orElseThrow(() -> {
            throw new ItemNotFoundException("Brend Not found");
        });
    }

    public BrandDTO toDTO(BrandEntity entity) {
        BrandDTO dto = new BrandDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    private void isValid(BrandDTO dto) {
        if (dto.getName() == null){
            throw new BadRequestException(" name is wrong ");
        }

    }


    public PageImpl pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<BrandEntity> all = brendRepository.findAll(pageable);
        List<BrandEntity> list = all.getContent();
        List<BrandDTO> dtoList = new LinkedList<>();
        list.forEach(brandEntity -> {
            dtoList.add(toDTO(brandEntity));
        });
        return new PageImpl(dtoList,pageable, all.getTotalElements());
    }


}
