package com.vadimrostov.uyutp.service;


import com.vadimrostov.uyutp.data.dao.UPArticlePostDAO;
import com.vadimrostov.uyutp.data.domain.UPComment;
import com.vadimrostov.uyutp.data.domain.UPPostLike;
import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.data.domain.UPTag;
import com.vadimrostov.uyutp.service.sortarticle.ArticleSortBehavior;
import com.vadimrostov.uyutp.service.sortarticle.impl.ArticleSortByDateBehavior;
import com.vadimrostov.uyutp.service.sortarticle.impl.ArticleSortByHotBehavior;
import com.vadimrostov.uyutp.service.sortarticle.impl.ArticleSortByRateBehavior;
import com.vadimrostov.uyutp.web.dto.ArticleCardDto;
import com.vadimrostov.uyutp.web.dto.ArticleDto;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UPPostServiceImpl implements UPPostService{


    @Autowired
    UPArticlePostDAO upArticlePostDAO;


    @Autowired
    UPTagService upTagService;

    @Autowired
    UserService userService;

    @Autowired
    UPCommentService commentService;

    private static final long MILLIS_IN_DAY=86400000;




    public UPArticlePost saveNewPost(ArticleDto articleDto) {
        UPArticlePost upArticlePost = new UPArticlePost();
        upArticlePost.setUPPostLike(new HashSet<UPPostLike>());
        upArticlePost.setAuthor(articleDto.getAuthor());
        upArticlePost.setContent(articleDto.getContent());
        upArticlePost.setUser(userService.getByLogin(articleDto.getAuthor()));
        upArticlePost.setDate(articleDto.getDate());
        upArticlePost.setUPComments(new HashSet<UPComment>());
        upArticlePost.setName(articleDto.getName());
        upArticlePost.setRate(new Long(0));upArticlePost.setCreationDate(articleDto.getCreated());
        if(!StringUtils.isEmpty(articleDto.getTags())){
            String[] tags=articleDto.getTags().split("_");
            Set<UPTag> tagSet=new HashSet<UPTag>();
            for (String tag: tags){

                tagSet.add(upTagService.getOrCreateTag(tag));
            }
            upArticlePost.setTags(tagSet);
        }

        upArticlePost.setTags(getTagList(articleDto.getTags()));

        try{
        upArticlePostDAO.save(upArticlePost);
        return upArticlePost;}
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    public List<ArticleDto> searchPost(String sortStr) {
        String[]sent=sortStr.split("[!?.,\\s]");
        Set<String> stringSet= new HashSet<String>();
        Collections.addAll(stringSet,sent);
        for (String str: stringSet){
            if(!str.equals("")){

            }
        }
        return null;
    }

    public List<ArticleDto> getAllArticleDto(String behavior){
        List<UPArticlePost> posts=upArticlePostDAO.getArticles();
        return getArticleDto(behavior, posts);
    }

    public List<ArticleDto> getListByDate(String behavior, String date) {
        List<UPArticlePost> posts=upArticlePostDAO.getArticlesByDate(date);
        return getArticleDto(behavior, posts);
    }

    public List<ArticleDto> getArticleDto(String behavior, List<UPArticlePost> listPost) {
        List<ArticleDto> dtoList=new ArrayList<ArticleDto>();

        ArticleSortBehavior articleSortBehavior;
        if (behavior.equals("rate")) articleSortBehavior=new ArticleSortByRateBehavior();
        else if (behavior.equals("date")) articleSortBehavior= new ArticleSortByDateBehavior();
        else {
            articleSortBehavior=new ArticleSortByHotBehavior();
            long curtime=new Date().getTime();
            List<UPArticlePost>postsClone=new ArrayList<UPArticlePost>(listPost);
            for (UPArticlePost post:postsClone){
                long posttime=post.getDate().getTime();
                if (posttime<(curtime-MILLIS_IN_DAY)){
                    listPost.remove(post);
                }

            }
        }
        for (UPArticlePost upArticlePost: listPost){


            ArticleDto dto=new ArticleDto(articleSortBehavior);
            dto.setAuthor(upArticlePost.getAuthor());
            dto.setDate(upArticlePost.getDate());
            dto.setDescription(upArticlePost.getDescription());
            dto.setName(upArticlePost.getName());
            dto.setContent(upArticlePost.getContent());
            dto.setRate(upArticlePost.getRate());
            dto.setTagDtoList(upTagService.getDtoTagList(upArticlePost.getTags()));
            dto.setCommentDtoList(commentService.getCommentsDto(upArticlePost.getUPComments()));
            dto.setId(upArticlePost.getId());
            dtoList.add(dto);



        }
        Collections.sort(dtoList);
        Collections.reverse(dtoList);
        return dtoList;
    }




    public List<UPArticlePost> getByDate(String date){


        return upArticlePostDAO.getArticlesByDate(date);
    }

    public List<UPArticlePost> getByTag(String tagname) {
        UPTag tag= upTagService.getByName(tagname);
        if (tag!=null){
            return upArticlePostDAO.getArticlesByTag(tag);
        }
        else         return null;
    }

    public Set<UPTag> getTagList(String tagStr){
        String[] tagArray=tagStr.split(",");
        Set<UPTag>tagSet= new HashSet<UPTag>();

        for (String tagString: tagArray){
            tagString=tagString.trim();
            UPTag tag=upTagService.getOrCreateTag(tagString);
            tagSet.add(tag);
        }

        return tagSet;


    }

    @Secured("ROLE_ADMIN")
    public void saveArticlePost(UPArticlePost upArticlePost){

        upArticlePostDAO.save(upArticlePost);

    }

    public ArticleDto dtoFromObject(UPArticlePost articlePost){
        ArticleDto articleDto=new ArticleDto();
        return null;

    }

    public UPArticlePost getArticlePost(Long id) {
        return upArticlePostDAO.getArticle(id);

    }

    public int getRate(Long id) {
        UPArticlePost upArticlePost=upArticlePostDAO.getArticle(id);
        int i=0;
        for (UPPostLike UPPostLike : upArticlePost.getUPPostLike()){
            if(UPPostLike.isValue()){
                i++;
            }
            else i--;
        }
        return i;
    }

    public List<UPArticlePost> getArticles() {
        return upArticlePostDAO.getArticlesToShow();
    }

    public void update(UPArticlePost upArticlePost) {
        upArticlePostDAO.update(upArticlePost);
    }

    public List<ArticleCardDto> getGardList(List<ArticleDto> dtoList) {

        List<ArticleCardDto> cardDtos=new ArrayList<ArticleCardDto>();
        for (ArticleDto dto: dtoList){
            if (dto!=null){
                String jsop=Jsoup.parse(dto.getContent()).text();
                if(jsop.length()>200){
                    jsop=jsop.substring(0, 200)+"...";

                }
                ArticleCardDto cardDto=new ArticleCardDto(dto.getId(), dto.getName(), jsop, dto.getAuthor(), dto.getDate());
                cardDtos.add(cardDto);
            }

        }
        return cardDtos;
    }

    public List<ArticleCardDto> getCards(int[] ids) {
        List<ArticleCardDto> cardDtos=new ArrayList<ArticleCardDto>();
        for(int id: ids){
            UPArticlePost post=upArticlePostDAO.getArticle((long)id);


            String jsop=Jsoup.parse(post.getContent()).text();
            if(jsop.length()>200){
                jsop=jsop.substring(0, 200)+"...";

            }
            cardDtos.add(new ArticleCardDto(post.getId(), post.getName(), jsop, post.getAuthor(), post.getDate()));
        }

        return cardDtos;
    }
}
