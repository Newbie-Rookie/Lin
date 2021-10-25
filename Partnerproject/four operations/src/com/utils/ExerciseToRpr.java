package com.utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 表达式类型转换类
 */
public class ExerciseToRpr {
    /**
     * 将中缀表达式转换为后缀表达式
     * @param exercise 中缀表达式
     * @return 后缀表达式
     */
    public static String exerciseToRpr(String exercise){
        // 调用tranToRpr()方法将中缀表达式转换为后缀表达式队列
        Queue<String> rprQueue = tranToRpr(exercise);
        // 存储后缀表达式
        StringBuffer rpr = new StringBuffer();
        for(String str : rprQueue){
            rpr.append(str + " ");
        }
        return rpr.toString();
    }

    /**
     * 使用逆波兰法将中缀表达式转换为后缀表达式队列
     * @param exercise 中缀表达式
     * @return 后缀表达式链表
     */
    public static Queue<String> tranToRpr(String exercise){
        // 将中缀表达式按空格分割成操作单元数组
        String[] opers = exercise.split(" ");
        // 存储中缀表达式转换得到的后缀表达式队列
        Queue<String> rprQueue = new LinkedList<>();
        // 存储运算符的辅助栈
        Stack<String> operatorsStack = new Stack<>();
        // 将分割产生的操作单元数组转化为后缀表达式队列
        for(String oper : opers){
            // 若为数，则进入后缀表达式队列
            if(!isOperator(oper)){
                rprQueue.offer(oper);
            }else{
                if("(".equals(oper)){
                    // 若为"("，入栈
                    operatorsStack.push(oper);
                }else if(")".equals(oper)){
                    // 若为")"，将栈中上一个"("之间的操作符弹出并进入后缀表达式队列
                    while(!operatorsStack.isEmpty()){
                        if(operatorsStack.peek().equals("(")){
                            // 找到"("，与")"抵消，弹出
                            operatorsStack.pop();
                            break;
                        }else{
                            // 将栈中上一个"("之间的操作符弹出并进入后缀表达式队列
                            rprQueue.offer(operatorsStack.pop());
                        }
                    }
                }else{
                    // 处理除括号外的操作符(+ - * ÷)
                    while(!operatorsStack.isEmpty()){
                        if("(".equals(operatorsStack.peek())){
                            // 若栈顶是"("，入栈
                            operatorsStack.push(oper);
                            break;
                        }else if(isPrior(operatorsStack.peek(),oper)){
                            // 若栈顶运算符优先级大于或等于oper，将栈顶运算符弹出进入后缀表达式队列
                            // 优先级相同，则从左往右算
                            rprQueue.offer(operatorsStack.pop());
                        }else if(isPrior(oper,operatorsStack.peek())){
                            // 若栈顶运算符优先级小于oper，运算符入栈
                            operatorsStack.push(oper);
                            break;
                        }
                    }
                    // 当栈空时入栈
                    if(operatorsStack.isEmpty()){
                        operatorsStack.push(oper);
                    }
                }
            }
        }
        // 栈中剩余运算符弹出进入后缀表达式队列
        while(!operatorsStack.isEmpty()){
            rprQueue.offer(operatorsStack.pop());
        }
        // 返回后缀表达式链表
        return rprQueue;
    }

    /**
     * 是否为运算符
     * @param o
     * @return 判断结果
     */
    public static boolean isOperator(String o) {
        if("(".equals(o) || ")".equals(o) || "+".equals(o) || "-".equals(o) || "*".equals(o) || "÷".equals(o)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 运算符优先级判断
     * @param oper1 运算符1
     * @param oper2 运算符2
     * @return 当 运算符1优先级 >= 运算符2优先级 时，返回true，否则返回false
     */
    public static boolean isPrior(String oper1, String oper2) {
        if(ofPriority(oper1) >= ofPriority(oper2)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取运算符优先级
     * @param oper
     * @return 运算符优先级
     */
    public static int ofPriority(String oper) {
        if("+".equals(oper) || "-".equals(oper)){
            return 1;
        }else if ("*".equals(oper) || "÷".equals(oper)){
            return 2;
        }else{
            throw new IllegalArgumentException("表达式中存在未知(非法)运算符！");
        }
    }
}