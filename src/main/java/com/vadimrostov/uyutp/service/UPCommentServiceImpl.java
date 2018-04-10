package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.dao.UPArticlePostDAO;
import com.vadimrostov.uyutp.data.dao.UPCommentDAO;
import com.vadimrostov.uyutp.data.dao.UPUserDao;
import com.vadimrostov.uyutp.data.domain.UPComment;
import com.vadimrostov.uyutp.web.dto.CommentDto;
import com.vadimrostov.uyutp.web.dto.UserDto;
import com.vadimrostov.uyutp.web.tree.CommentDtoTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UPCommentServiceImpl implements UPCommentService {


    @Autowired
    UPCommentDAO upCommentDAO;

    @Autowired
    UPArticlePostDAO upArticlePostDAO;

    @Autowired
    UPUserDao upUserDao;

    public void saveComment(UPComment UPComment){
        upCommentDAO.save(UPComment);
    }

    public List<UPComment> getPostComments(Long id) {
        return upCommentDAO.getCommentsForPost(id);
    }

    public List<CommentDtoTreeNode> getCommentTree(Long postId) {
        List<CommentDtoTreeNode> nodes=new ArrayList<CommentDtoTreeNode>();
        List<CommentDto> dtos=getCommentsDto(new HashSet<UPComment>(getPostComments(postId)));

        Collections.sort(dtos);
        List<CommentDto> dtosCopy=new ArrayList<CommentDto>(dtos);
        for (CommentDto commentDto: dtosCopy){
            if (commentDto.getParent()==null){
                nodes.add(new CommentDtoTreeNode(commentDto));
                dtos.remove(commentDto);
            }
        }
        for(CommentDto commentDto:dtos){
            CommentDtoTreeNode parrent=new CommentDtoTreeNode(null);
            for (CommentDtoTreeNode node:nodes){
                CommentDto commentDto1=(CommentDto)node.getData();
                if(commentDto.getParent().equals(commentDto1.getId()))
                { parrent=node;
                break;}
                for(Object node1: node){
                    CommentDtoTreeNode node2=(CommentDtoTreeNode) node1;
                    CommentDto commentDto2=(CommentDto)node2.getData();
                    if(commentDto.getParent().equals(commentDto2.getId())){
                        parrent=node2;}
                }
            }



            parrent.addChild(commentDto);
        }

        return nodes;
    }

    public List<CommentDto> getCommentsDto(Set<UPComment> UPCommentSet) {
        List<CommentDto> commentDtos=new ArrayList<CommentDto>();
        for(UPComment UPComment : UPCommentSet){
            CommentDto commentDto=new CommentDto();
            commentDto.setAuthor(UPComment.getAuthor());
            commentDto.setContent(UPComment.getContent());
            commentDto.setTimestamp(UPComment.getTimestamp());
            commentDto.setLevel(UPComment.getLevel());
            commentDto.setParent(UPComment.getParrentComment());
            commentDto.setId(UPComment.getId());
            UserDto userDto=new UserDto();
            if (UPComment.getUser().getLogin()!=null) {
                userDto.setLogin(UPComment.getUser().getLogin());
            }

            commentDtos.add(commentDto);

        }

        return commentDtos;
    }

    public List<UPComment> getComments() {
        return upCommentDAO.getComments();
    }

    public List<CommentDto> getCommentsDto(Long id) {
        Set<UPComment> UPComments =new HashSet<UPComment>(getPostComments(id));
        List<CommentDto> commentDtos=getCommentsDto(UPComments);
        Collections.sort(commentDtos);
        return commentDtos;
    }

    public CommentDto getDtoById(Long id) {
        CommentDto commentDto=new CommentDto();
        UPComment UPComment =upCommentDAO.getCommentById(id);
        commentDto.setPostId(UPComment.getUpArticlePost().getId());
        commentDto.setContent(UPComment.getContent());
        commentDto.setAuthor(UPComment.getAuthor());
        commentDto.setTimestamp(UPComment.getTimestamp());
        commentDto.setLevel(UPComment.getLevel());
        commentDto.setParent(UPComment.getParrentComment());
        commentDto.setId(UPComment.getId());
        return commentDto;
    }

    public long saveNewComment(CommentDto commentDto) {
        UPComment UPComment =new UPComment();
        UPComment.setUser(upUserDao.findByLogin(commentDto.getAuthor()));
        UPComment.setAuthor(commentDto.getAuthor());
        UPComment.setTimestamp(commentDto.getTimestamp());
        UPComment.setParrentComment(commentDto.getParent());
        UPComment.setLevel(commentDto.getLevel());
        UPComment.setContent(commentDto.getContent());
        UPComment.setUpArticlePost(upArticlePostDAO.getArticle(commentDto.getPostId()));
        upCommentDAO.save(UPComment);
        return UPComment.getId();
    }
}
