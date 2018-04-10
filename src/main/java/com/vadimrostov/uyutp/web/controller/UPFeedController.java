package com.vadimrostov.uyutp.web.controller;


import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.service.UPPostService;
import com.vadimrostov.uyutp.web.bean.UPSearchBean;
import com.vadimrostov.uyutp.web.dto.ArticleDto;
import com.vadimrostov.uyutp.web.dto.DateSearchDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/feed")
public class UPFeedController {

    @Autowired
    UPPostService upPostService;

    ModelAndView modelAndView= new ModelAndView();

    private static final String RATE_BEHAVIOR="rate";
    private static final String DATE_BEHAVIOR="date";
    private static final String HOT_BEHAVIOR="hot";

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/new")
    public ModelAndView showNew(){

        modelAndView.addObject("search", new UPSearchBean());
        return operateBehavior(DATE_BEHAVIOR);
    }



    @RequestMapping("/searchnew")
    public ModelAndView showSearchNew(){

        modelAndView.addObject("datesearch", new DateSearchDTO());
        modelAndView.setViewName("searchfeed");

        return modelAndView;
    }

    @RequestMapping(value = "/searchnew", method = RequestMethod.POST)
    public ModelAndView showRatedForDate(DateSearchDTO dateSearchDTO){

        List<ArticleDto> dtoList=upPostService.getListByDate(RATE_BEHAVIOR, dateSearchDTO.getDate());
        modelAndView.addObject("cardList", upPostService.getGardList(dtoList));

        modelAndView.addObject("postList", dtoList);
        modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
        boolean b=dtoList.size()>6;
        modelAndView.addObject("dateStr", dateSearchDTO.getDate());
        modelAndView.addObject("isSevenPostsExist", b);
        modelAndView.setViewName("showfeedj");
        return modelAndView;
    }

    @RequestMapping("/tag")
    public ModelAndView showHot(@RequestParam String tagname){
        List<UPArticlePost> posts=upPostService.getByTag(tagname);
        modelAndView.addObject("postList", posts);
        modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.setViewName("showfeed");
        return modelAndView;
    }

    @RequestMapping("/ratedj")
    public ModelAndView shouJournalRated(){

            List<ArticleDto> dtoList=upPostService.getAllArticleDto(RATE_BEHAVIOR);
            modelAndView.addObject("cardList", upPostService.getGardList(dtoList));
            modelAndView.addObject("postList", dtoList);
            modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
            modelAndView.setViewName("showfeedj");
            return modelAndView;

    }

    @RequestMapping("/rated")
    public ModelAndView showRated(@RequestParam(value = "date", required = false) String date){
        modelAndView.addObject("datesearch", new DateSearchDTO());


        if (StringUtils.isEmpty(date)){
            SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");

            String strDate=format.format(new Date());
            List<ArticleDto> dtoList=upPostService.getListByDate(RATE_BEHAVIOR, strDate);
            modelAndView.addObject("cardList", upPostService.getGardList(dtoList));
            modelAndView.addObject("postList", dtoList);
            modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
            modelAndView.setViewName("showfeed");
            return modelAndView;
        }
        else{
            List<ArticleDto> dtoList=upPostService.getListByDate(RATE_BEHAVIOR, date);
            modelAndView.addObject("cardList", upPostService.getGardList(dtoList));
            modelAndView.addObject("postList", dtoList);
            modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
            modelAndView.setViewName("showfeed");
            return modelAndView;
        }
    }

    @RequestMapping("/hot")
    public ModelAndView showHot(){


        modelAndView.addObject("datesearch", new DateSearchDTO());

        return operateBehavior(HOT_BEHAVIOR);
    }


    public ModelAndView operateBehavior(String beh){
        List<ArticleDto> dtoList=upPostService.getAllArticleDto(beh);
        modelAndView.addObject("cardList", upPostService.getGardList(dtoList));
        modelAndView.addObject("postList", dtoList);
        modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.setViewName("showfeed");
        return modelAndView;
    }
}
