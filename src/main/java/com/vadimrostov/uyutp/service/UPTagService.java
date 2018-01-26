package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.domain.UPTag;

public interface UPTagService {

    public UPTag getOrCreateTag(String tagStr);

    public UPTag getByName(String name);




}
