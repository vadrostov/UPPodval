package com.vadimrostov.uyutp.service;


import com.vadimrostov.uyutp.data.dao.UPTagDao;
import com.vadimrostov.uyutp.data.domain.UPTag;
import com.vadimrostov.uyutp.web.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<TagDto> getDtoTagList(Set<UPTag> tagList) {
        List<TagDto> tagDtos=new ArrayList<TagDto>();
        for(UPTag tag:tagList){
            TagDto tagDto=new TagDto();
            tagDto.setName(tag.getName());
            tagDtos.add(tagDto);
        }
        return tagDtos;
    }

    public Set<TagDto> getTags() {
        List<UPTag> tags=upTagDao.getTags();
        Set<TagDto> tagDtoSet=new HashSet<TagDto>();

        for(UPTag tag: tags){
            TagDto dto=new TagDto();
            dto.setName(tag.getName());
        }


        return tagDtoSet;
    }

    public UPTag getByName(String name) {
        return upTagDao.getTagByName(name);
    }
}
