package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.domain.UPComment;
import com.vadimrostov.uyutp.web.dto.CommentDto;
import com.vadimrostov.uyutp.web.tree.CommentDtoTreeNode;

import java.util.List;
import java.util.Set;

public interface UPCommentService {

    public void saveComment(UPComment UPComment);

    public List<UPComment> getComments();

    public List<UPComment> getPostComments(Long id);

    public List<CommentDto> getCommentsDto(Set<UPComment> UPCommentSet);

    public List<CommentDtoTreeNode> getCommentTree(Long postId);

    public List<CommentDto> getCommentsDto(Long id);

    public long saveNewComment(CommentDto commentDto);

    public CommentDto getDtoById(Long id);

}
