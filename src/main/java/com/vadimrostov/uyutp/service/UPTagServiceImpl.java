package com.vadimrostov.uyutp.service;


import com.vadimrostov.uyutp.data.dao.UPTagDao;
import com.vadimrostov.uyutp.data.domain.UPTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UPTagServiceImpl implements UPTagService {

    @Autowired
    UPTagDao upTagDao;

    public UPTag getOrCreateTag(String tagStr) {
        UPTag upTag=upTagDao.getTagByName(tagStr);
        if(upTag!=null){
            return upTag;
        }
        else{
            upTag =new UPTag();
            upTag.setName(tagStr);
            upTagDao.saveTag(upTag);
            return upTag;
        }

    }

    public UPTag getByName(String name) {
        return upTagDao.getTagByName(name);
    }
}
