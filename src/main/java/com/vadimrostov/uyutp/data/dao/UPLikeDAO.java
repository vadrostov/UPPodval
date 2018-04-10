package com.vadimrostov.uyutp.data.dao;


import com.vadimrostov.uyutp.data.domain.UPPostLike;

public interface UPLikeDAO {

    public void save(UPPostLike UPPostLike);

    public void update(UPPostLike UPPostLike);

    public void delete(UPPostLike UPPostLike);
}
