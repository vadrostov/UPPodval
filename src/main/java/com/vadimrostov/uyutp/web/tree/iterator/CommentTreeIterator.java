package com.vadimrostov.uyutp.web.tree.iterator;

import com.vadimrostov.uyutp.web.dto.CommentDto;
import com.vadimrostov.uyutp.web.tree.CommentDtoTreeNode;

import java.util.Iterator;

public class CommentTreeIterator<CommentDto> implements Iterator<CommentDtoTreeNode<CommentDto>> {

    enum ProcessStages {
        ProcessParent, ProcessChildCurNode, ProcessChildSubNode
    }

    private CommentDtoTreeNode treeNode;

    public CommentTreeIterator(CommentDtoTreeNode treeNode) {
        this.treeNode = treeNode;
        this.doNext = ProcessStages.ProcessParent;
        this.childrenCurNodeIter = treeNode.getChildren().iterator();
    }

    private ProcessStages doNext;
    private CommentDtoTreeNode next;
    private Iterator<CommentDtoTreeNode> childrenCurNodeIter;
    private Iterator<CommentDtoTreeNode> childrenSubNodeIter;


    public boolean hasNext() {

        if (this.doNext == ProcessStages.ProcessParent) {
            this.next = this.treeNode;
            this.doNext = ProcessStages.ProcessChildCurNode;
            return true;
        }

        if (this.doNext == ProcessStages.ProcessChildCurNode) {
            if (childrenCurNodeIter.hasNext()) {
                CommentDtoTreeNode childDirect = childrenCurNodeIter.next();
                childrenSubNodeIter = childDirect.iterator();
                this.doNext = ProcessStages.ProcessChildSubNode;
                return hasNext();
            }

            else {
                this.doNext = null;
                return false;
            }
        }

        if (this.doNext == ProcessStages.ProcessChildSubNode) {
            if (childrenSubNodeIter.hasNext()) {
                this.next = childrenSubNodeIter.next();
                return true;
            }
            else {
                this.next = null;
                this.doNext = ProcessStages.ProcessChildCurNode;
                return hasNext();
            }
        }

        return false;
    }


    public CommentDtoTreeNode next() {
        return this.next;
    }


    public void remove() {
        throw new UnsupportedOperationException();
    }
}
