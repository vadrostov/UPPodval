package com.vadimrostov.uyutp.web.dto;

import java.io.Serializable;

public class CategoryDto implements Serializable{

    private Long id;

    private String name;

    private boolean checked;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name, boolean checked) {

        this.id = id;
        this.name = name;
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
