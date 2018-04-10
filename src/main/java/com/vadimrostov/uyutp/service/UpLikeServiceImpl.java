package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.dao.UPLikeDAO;
import com.vadimrostov.uyutp.data.domain.UPPostLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpLikeServiceImpl implements UPLikeService {

    @Autowired
    UPLikeDAO upLikeDAO;

    public void update(UPPostLike UPPostLike) {
        upLikeDAO.update(UPPostLike);
    }

    public void delete(UPPostLike UPPostLike) {
        upLikeDAO.delete(UPPostLike);
    }

    public void save(UPPostLike UPPostLike) {
        upLikeDAO.save(UPPostLike);
    }
}
