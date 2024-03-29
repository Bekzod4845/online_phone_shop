package com.company.service;

import com.company.dto.CategoryDTO;
import com.company.entity.CategoryEntity;
import com.company.enums.Language;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.mapper.CategoryMapper.CategoryStructMapper;
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

    @Autowired
    private CategoryStructMapper categoryStructMapper;
    public void create(CategoryDTO dto) {
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
        CategoryEntity category = categoryStructMapper.categoryDTOToCategoryEntity(dto);
        category.setParentCategory(entity1);
        categoryRepository.save(category);

    }

    public void update(String id, CategoryDTO dto) {
        isValid(dto);
        CategoryEntity entity = get(id);
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        System.out.println("success update category");
        categoryRepository.save(entity);

    }

    public void delete(String id) {
        CategoryEntity entity = get(id);
        entity.setVisible(false);
        System.out.println("success delete category");
        categoryRepository.save(entity);
    }

    public List<CategoryDTO> getList(Language lang) {

        List<CategoryEntity> all = categoryRepository.findAllByVisible(true);
        List<CategoryDTO> dtoList = new LinkedList<>();

        all.forEach(categoryEntity -> {
            CategoryDTO categoryDTO = categoryStructMapper.categoryEntityToCategoryDTOSelectLang(categoryEntity, lang);
            dtoList.add(categoryDTO);
        });
        return dtoList;
    }

    public List<CategoryDTO> getListOnlyForAdmin() {

        Iterable<CategoryEntity> all = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();

        all.forEach(categoryEntity -> {
            dtoList.add(categoryStructMapper.categoryEntityToCategoryDTO(categoryEntity));
        });
        return dtoList;
    }

    public CategoryEntity getId(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Category Not found");
        });
    }

    public CategoryEntity get(String key) {
        return categoryRepository.findByKey(key).orElseThrow(() -> {
            throw new ItemNotFoundException("Category Not found");
        });
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
            dtoList.add(categoryStructMapper.categoryEntityToCategoryDTO(categoryEntity));
        });
        return new PageImpl(dtoList,pageable, all.getTotalElements());
    }

}
