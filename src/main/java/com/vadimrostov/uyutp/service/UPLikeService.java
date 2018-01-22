package com.vadimrostov.uyutp.service;


import com.vadimrostov.uyutp.data.domain.Like;

public interface UPLikeService {

    public void save(Like like);


    public void update(Like like);

    public void delete(Like like);
}
