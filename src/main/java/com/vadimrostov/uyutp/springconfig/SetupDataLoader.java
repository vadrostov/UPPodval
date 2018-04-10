package com.vadimrostov.uyutp.springconfig;

import com.vadimrostov.uyutp.service.UPPostCategoryService;
import com.vadimrostov.uyutp.service.UPTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UPTagService tagService;

    @Autowired
    UPPostCategoryService postCategoryService;

    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
