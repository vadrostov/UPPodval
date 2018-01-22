package com.vadimrostov.uyutp.data.dao;


import com.vadimrostov.uyutp.data.domain.Like;

public interface UPLikeDAO {

    public void save(Like like);

    public void update(Like like);

    public void delete(Like like);
}
