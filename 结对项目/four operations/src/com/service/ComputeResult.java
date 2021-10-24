package com.service;

import com.model.Operand;
import com.utils.ExerciseToRpr;

import java.util.Stack;

/**
 *  计算结果类
 *  使用后缀表达式
 */
public class ComputeResult {
    /**
     * 计算题目结果并写入结果文件Answers.txt
     * @param exercise
     * @return
     */
    public static String compute(String exercise){
        // 计算题目结果
        // 先将中缀表达式转换为后缀表达式
        String rpr = ExerciseToRpr.exerciseToRpr(exercise);
        // 将后缀表达式字符串按照空格分割成操作单元数组
        String[] opers = rpr.split(" ");
        // 计算操作的辅助栈
        Stack<String> stack = new Stack<>();
        // 计算结果
        Operand result;
        for(int i = 0;i < opers.length;i++){
            if(!ExerciseToRpr.isOperator(opers[i])){
                // 若是数，则入栈
                stack.push(opers[i]);
            }else{
                // 若是运算符，则栈顶两数出栈计算结果再入栈
                String n2 = stack.pop();
                String n1 = stack.pop();
                // 将数字字符串转换为操作数对象
                Operand b = Operand.stringToOperand(n2);
                Operand a = Operand.stringToOperand(n1);
                // 根据运算符类型进行运算
                if(opers[i].equals("+")){
                    result = Operand.add(a,b);
                }else if(opers[i].equals("-")){
                    result = Operand.sub(a,b);
                }else if (opers[i].equals("*")) {
                    result = Operand.mul(a,b);
                }else{
                    result = Operand.div(a,b);
                }
                // 计算过程出现除数为0或计算结果为负时，丢弃表达式
                if(result == null){
                    return "unqualified";
                }
                // 把计算结果入栈
                stack.push(result.toString());
            }
        }
        return stack.peek();
    }
}
