package com.vadimrostov.uyutp.web.controller.web.jsonbeans;

public class DeletePostResponse {

    boolean isDeleted;

    Long id;

    public DeletePostResponse() {
    }


    public DeletePostResponse(boolean isDeleted, Long id) {
        this.isDeleted = isDeleted;
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
