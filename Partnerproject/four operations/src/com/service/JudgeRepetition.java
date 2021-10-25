package com.service;

import com.model.BinaryTreeNode;

/**
 *  判断重复类
 *  两树是否同构
 */
public class JudgeRepetition {
    /**
     * 利用树的同构原理，判断两棵树是否同构，即两个题目是否重复
     * @param t1 题1二叉树根结点
     * @param t2 题2二叉树根结点
     * @return 判断结果(true则同构)
     */
    public static boolean judge(BinaryTreeNode t1,BinaryTreeNode t2) {
        if(t1 == null && t2 == null)
            return true;
        if(t1 == null && t2 != null){
            return false;
        }
        if(t1 != null && t2 == null){
            return false;
        }
        if(t1.getValue() != t2.getValue()){
            return false;
        }
        return judge(t1.getLeftNode(),t2.getLeftNode()) && judge(t1.getRightNode(),t2.getRightNode())
                || judge(t1.getLeftNode(),t2.getRightNode()) && judge(t1.getRightNode(),t2.getLeftNode());
    }
}
