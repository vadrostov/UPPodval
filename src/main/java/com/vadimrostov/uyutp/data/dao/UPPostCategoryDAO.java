package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.UPPostCategory;

import java.util.List;

public interface UPPostCategoryDAO {

    public List<UPPostCategory> getPostCategories();
}
