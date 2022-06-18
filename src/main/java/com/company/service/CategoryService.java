package com.company.service;

import com.company.dto.CategoryDTO;
import com.company.entity.CategoryEntity;
import com.company.enums.Language;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public CategoryDTO create(CategoryDTO dto) {
        isValid(dto);
        Optional<CategoryEntity> optional = categoryRepository.findByKey(dto.getKey());
        if (optional.isPresent()){
            throw new BadRequestException("category is exsits");
        }
        CategoryEntity entity1 = null;
        if(dto.getCategoryParentId()!=null){
            Optional<CategoryEntity> parentCategory = categoryRepository.findById(dto.getCategoryParentId());
            entity1= parentCategory.get();
        }
        CategoryEntity entity = new CategoryEntity();
        entity.setKey(dto.getKey());
        entity.setParentCategory(entity1);
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        categoryRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public void update(Integer id, CategoryDTO dto) {
        isValid(dto);
        CategoryEntity entity = get(id);
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        System.out.println("success update category");
        categoryRepository.save(entity);

    }

    public void delete(Integer id) {
        CategoryEntity entity = get(id);
        entity.setVisible(false);
        System.out.println("success delete category");
        categoryRepository.save(entity);
    }

    public List<CategoryDTO> getList(Language lang) {

        Iterable<CategoryEntity> all = categoryRepository.findAllByVisible(true);
        List<CategoryDTO> dtoList = new LinkedList<>();

        all.forEach(categoryEntity -> {
            CategoryDTO dto = new CategoryDTO();
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
            dtoList.add(dto);
        });
        return dtoList;
    }

    public List<CategoryDTO> getListOnlyForAdmin() {

        Iterable<CategoryEntity> all = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();

        all.forEach(categoryEntity -> {
            dtoList.add(toDTO(categoryEntity));
        });
        return dtoList;
    }

    public CategoryEntity get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Category Not found");
        });
    }

    public CategoryEntity get(String key) {
        return categoryRepository.findByKey(key).orElseThrow(() -> {
            throw new ItemNotFoundException("Category Not found");
        });
    }

    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        return dto;
    }

    private void isValid(CategoryDTO dto) {
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
    }


    public PageImpl pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<CategoryEntity> all = categoryRepository.findAll(pageable);
        List<CategoryEntity> list = all.getContent();
        List<CategoryDTO> dtoList = new LinkedList<>();
        list.forEach(categoryEntity -> {
            dtoList.add(toDTO(categoryEntity));
        });
        return new PageImpl(dtoList,pageable, all.getTotalElements());
    }

}
