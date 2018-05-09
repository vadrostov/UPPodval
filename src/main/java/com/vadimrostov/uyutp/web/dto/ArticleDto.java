package com.vadimrostov.uyutp.web.dto;

import com.vadimrostov.uyutp.service.UPPostCategoryService;
import com.vadimrostov.uyutp.service.sortarticle.ArticleSortBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticleDto implements Comparable<ArticleDto>, Serializable{



    private ArticleSortBehavior sortBehavior;


    private Long id;


    @NotNull
    private String name;


    private String description;

    @NotNull
    private String content;

    private String tags;

    private String author;

    private Date date;

    private String created;

    private Long rate;

    private List<TagDto> tagDtoList;

    private List<CommentDto> commentDtoList;

    private int countWords;

    private Set<CategoryDto> categoryDtos;


    private Set<TagDto> tagSet;

    List<CommentDto> commentDtos;

    public ArticleDto() {

       categoryDtos=new HashSet<CategoryDto>();
       categoryDtos.add(new CategoryDto(1L, "Game", true));
        categoryDtos.add(new CategoryDto(2L, "Music", true));
        categoryDtos.add(new CategoryDto(3L, "Cartoon", true));
        categoryDtos.add(new CategoryDto(4L, "Books", true));
        categoryDtos.add(new CategoryDto(5L, "Comics", true));
        categoryDtos.add(new CategoryDto(6L, "BoardGames", true));


    }

    public ArticleDto(ArticleSortBehavior sortBehavior) {
        this.sortBehavior = sortBehavior;
    }

    public String getName() {
        return name;
    }

    public List<TagDto> getTagDtoList() {
        return tagDtoList;
    }

    public void setTagDtoList(List<TagDto> tagDtoList) {
        this.tagDtoList = tagDtoList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public List<CommentDto> getCommentDtoList() {
        return commentDtoList;
    }

    public void setCommentDtoList(List<CommentDto> commentDtoList) {
        this.commentDtoList = commentDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Set<CategoryDto> getCategoryDtos() {
        return categoryDtos;
    }

    public Set<TagDto> getTagSet() {
        return tagSet;
    }

    public List<CommentDto> getCommentDtos() {
        return commentDtos;
    }

    public void setCommentDtos(List<CommentDto> commentDtos) {
        this.commentDtos = commentDtos;
    }

    public void setTagSet(Set<TagDto> tagSet) {
        this.tagSet = tagSet;
    }

    public void setCategoryDtos(Set<CategoryDto> categoryDtos) {
        this.categoryDtos = categoryDtos;
    }

    public int compareTo(ArticleDto o) {
        if(sortBehavior==null){
        return 0;}
        else return sortBehavior.compareTo(this, (ArticleDto)o);
    }


}
