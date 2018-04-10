package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.domain.UPTag;
import com.vadimrostov.uyutp.web.dto.TagDto;

import java.util.List;
import java.util.Set;

public interface UPTagService {

    public UPTag getOrCreateTag(String tagStr);

    public UPTag getByName(String name);

    public List<TagDto> getDtoTagList(Set<UPTag> tagList);

    public Set<TagDto> getTags();




}
