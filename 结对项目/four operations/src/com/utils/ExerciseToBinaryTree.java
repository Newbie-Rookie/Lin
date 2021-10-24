package com.utils;

import com.model.BinaryTreeNode;

import java.util.Queue;
import java.util.Stack;

/**
 * 表达式转换为二叉树类
 */
public class ExerciseToBinaryTree {
    /**
     * 将正常算术表达式(中缀表达式)转换为二叉树
     * 先将中缀表达式转换为后缀表达式
     * @param exercise
     * @return
     */
    public static BinaryTreeNode exerciseToBinaryTree(String exercise) {
        // 先将中缀表达式转换为后缀表达式链表
        Queue<String> rprQueue = ExerciseToRpr.tranToRpr(exercise);
        // 用于转化二叉树操作的辅助栈
        Stack<BinaryTreeNode> nodeStack = new Stack<>();
        // 将逆波兰表达式队列转化为二叉树形式
        while(!rprQueue.isEmpty()){
            // 弹出队首元素并新建节点
            String oper = rprQueue.poll();
            BinaryTreeNode node = new BinaryTreeNode(oper);
            if(ExerciseToRpr.isOperator(oper)){
                // 若为运算符节点，则弹出栈顶两个操作数节点作为运算符结点的左右节点
                // 先弹出的作为右节点，后弹出的作为左节点
                node.setRightNode(nodeStack.pop());
                node.setLeftNode(nodeStack.pop());
                // 将运算符节点入栈
                nodeStack.push(node);
            }else{
                // 若为操作数结点，则入栈
                nodeStack.push(node);
            }
        }
        // 返回二叉树根节点
        return nodeStack.pop();
    }
}