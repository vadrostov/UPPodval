package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.UPTag;

import java.util.List;

public interface UPTagDao {

    public void saveTag(UPTag tag);

    public UPTag getTagByName(String name);

    public List<UPTag> getTags();

}
