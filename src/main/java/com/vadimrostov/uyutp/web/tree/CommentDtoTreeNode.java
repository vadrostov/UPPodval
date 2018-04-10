package com.vadimrostov.uyutp.web.tree;

import com.vadimrostov.uyutp.web.tree.iterator.CommentTreeIterator;

import java.util.*;

public class CommentDtoTreeNode<CommentDto> implements Iterable<Object> {

    CommentDto data;

    CommentDtoTreeNode parent;
    Set<CommentDtoTreeNode> children;

    List<CommentDtoTreeNode> elementsIndex;


    public CommentDtoTreeNode(CommentDto data) {
        this.data = data;

        this.children = new HashSet<CommentDtoTreeNode>();
        this.elementsIndex=new ArrayList<CommentDtoTreeNode>();
        elementsIndex.add(this);
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    public CommentDtoTreeNode addChild(CommentDto child) {
        CommentDtoTreeNode childNode = new CommentDtoTreeNode(child);
        childNode.parent = this;
        this.children.add(childNode);
        this.registerChildForSearch(childNode);
        return childNode;
    }

    public int getLevel() {
        if (this.isRoot())
            return 0;
        else
            return parent.getLevel() + 1;
    }

    private void registerChildForSearch(CommentDtoTreeNode node) {
        elementsIndex.add(node);
        if (parent != null)
            parent.registerChildForSearch(node);
    }



    public Iterator<Object> iterator() {
        return new CommentTreeIterator(this);
    }

    public CommentDto getData() {
        return data;
    }

    public CommentDtoTreeNode getParent() {
        return parent;
    }

    public Set<CommentDtoTreeNode> getChildren() {
        return children;
    }

    public List<CommentDtoTreeNode> getElementsIndex() {
        return elementsIndex;
    }
}
