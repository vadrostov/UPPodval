package com.vadimrostov.uyutp.web.dto;

import java.io.Serializable;
import java.util.Set;

public class FlowDto implements Serializable {

    private Set<TagDto> tagDtos;

    private Set<CategoryDto> categoryDtos;

    public Set<TagDto> getTagDtos() {
        return tagDtos;
    }

    public void setTagDtos(Set<TagDto> tagDtos) {
        this.tagDtos = tagDtos;
    }

    public Set<CategoryDto> getCategoryDtos() {
        return categoryDtos;
    }

    public void setCategoryDtos(Set<CategoryDto> categoryDtos) {
        this.categoryDtos = categoryDtos;
    }
}
