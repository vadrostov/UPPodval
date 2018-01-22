package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.dao.UPLikeDAO;
import com.vadimrostov.uyutp.data.domain.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpLikeServiceImpl implements UPLikeService {

    @Autowired
    UPLikeDAO upLikeDAO;

    public void update(Like like) {
        upLikeDAO.update(like);
    }

    public void delete(Like like) {
        upLikeDAO.delete(like);
    }

    public void save(Like like) {
        upLikeDAO.save(like);
    }
}
