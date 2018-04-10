package com.vadimrostov.uyutp.service;


import com.vadimrostov.uyutp.data.domain.UPPostLike;

public interface UPLikeService {

    public void save(UPPostLike UPPostLike);


    public void update(UPPostLike UPPostLike);

    public void delete(UPPostLike UPPostLike);
}
