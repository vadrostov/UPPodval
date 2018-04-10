package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.dao.UPPostCategoryDAO;
import com.vadimrostov.uyutp.data.domain.UPPostCategory;
import com.vadimrostov.uyutp.web.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UpPostCategoryServiceImpl implements UPPostCategoryService {

    @Autowired
    UPPostCategoryDAO upPostCategoryDAO;

    public Set<CategoryDto> getCategoryDtos() {
        Set<CategoryDto> categoryDtos=new HashSet<CategoryDto>();

        List<UPPostCategory> postCategories=upPostCategoryDAO.getPostCategories();

        for(UPPostCategory postCategory: postCategories){
            CategoryDto dto=new CategoryDto();
            dto.setChecked(false);
            dto.setId(postCategory.getId());
            dto.setName(postCategory.getCategoryName());
            categoryDtos.add(dto);
        }

        return categoryDtos;
    }
}
