package com.model;

/**
 *  二叉树类
 */
public class BinaryTreeNode {
    // 结点值
    private String value;
    // 左结点
    private BinaryTreeNode leftNode;
    // 右结点
    private BinaryTreeNode rightNode;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(String value) {
        this.value = value;
        this.leftNode = null;
        this.rightNode = null;
    }

    public BinaryTreeNode(String value, BinaryTreeNode lNode, BinaryTreeNode rNode) {
        this.value = value;
        this.leftNode = lNode;
        this.rightNode = rNode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BinaryTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinaryTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public BinaryTreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinaryTreeNode rightNode) {
        this.rightNode = rightNode;
    }
}
