package com.service;

import com.model.BinaryTreeNode;
import com.model.Operand;
import com.utils.ExerciseToBinaryTree;
import com.utils.FileProcessUtil;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 随机生成题目的类
 */
public class GenerateExcerises {
    // 存储生成的题目及其答案的Map，其中key是题目，value是答案
    // 便于进行查重
    private static HashMap<String,String> map = new HashMap<>();
    // 四则运算符
    private static final String[] operators = {" + "," - "," * "," ÷ "};
    // 括号
    private static final String[] bracket = {"( "," )"};

    /**
     * 生成题目，计算答案，判断是否符合条件，最后写入到指定文件中
     * @param number 题目数量
     * @param range 数值范围
     * @throws Exception
     */
    public static void generateExercises(int number,int range) throws Exception {
        // 从配置文件中获取存储题目和答案的文件绝对路径，再获取题目和答案字符输入流
        FileWriter exercises = FileProcessUtil.write(FileProcessUtil.resourceBundle("com/path","Exercises.txt"));
        FileWriter answers = FileProcessUtil.write(FileProcessUtil.resourceBundle("com/path","Answers.txt"));
        // 循环生成题目
        for (int i = 0;i < number;i++) {
            repeat:
            while(true){
                // 生成一道题目
                String exercise = generateExercise(range);
                // 计算题目结果
                String answer = ComputeResult.compute(exercise);
                // 不符合要求的结果，重新生成题目
                if(answer.equals("unqualified")){
                    continue;
                }
                // 判断题目是否重复
                // 先判断已生成的题目中是否存在与新生成题目答案相同的，存在则再进行查重
                if (map.containsValue(answer)){
                    // 将Map中与当前生成题目答案相同的题目提取出来存到一个List集合
                    ArrayList<String> arrayList = getKey(map,answer);
                    // 将新生成题目转化为二叉树形式
                    BinaryTreeNode treeNode = ExerciseToBinaryTree.exerciseToBinaryTree(exercise);
                    // 利用二叉树的同构判断题目是否重复
                    for (String eachExercise : arrayList) {
                        if (JudgeRepetition.judge(treeNode,ExerciseToBinaryTree.exerciseToBinaryTree(eachExercise))){
                            // 若两棵二叉树同构，即两题重复，重新生成题目
                            continue repeat;
                        }
                    }
                }
                // 生成题目不重复，则添加到Map集合中
                map.put(exercise,answer);
                // 向Exercises.txt文件中写入题目
                exercises.write(i + 1 + "、" + exercise + " =\n");
                // 向Answers.txt文件中写入该题答案
                answers.write(i + 1 + "、" + answer + "\n");
                break;
            }
        }
        // 关闭资源
        exercises.close();
        answers.close();
    }

    /**
     * 生成一道四则运算题目
     * @param range 数值范围
     * @return 一道四则运算题目
     */
    public static String generateExercise(int range){
        // exercise存储题目真分数形式
        // 后续计算再统一将操作数字符转为操作数对象，计算方便
        String exercise = null;
        // 随机生成四则运算符个数(1~3)
        int operatorNumber = (int)Math.floor((Math.random()*3)+1);
        // 随机生成一道题目的运算符数组
        String[] opera = generateOperators(operatorNumber);
        // 随机生成一道题目的操作数数组
        Operand[] oper = generateOperands(operatorNumber+1,range);
        // 随机生成题目是否包含括号：1 —— 有，0 —— 无（50%的概率）
        int isContainBracket = (int)Math.round(Math.random());

        // 若运算符为1个
        if(operatorNumber == 1){
            exercise = oper[0].toString()+opera[0]+oper[1].toString();
        }else{
            // 若题目包含括号
            if(isContainBracket == 1){
                // 若运算符为3个
                if (operatorNumber == 3){
                    // 随机生成一种表达式
                    int num = (int)Math.floor(Math.random()*10);
                    switch(num){
                        case 0:
                            exercise = bracket[0]+oper[0].toString()+opera[0]+oper[1].toString()+bracket[1]+opera[1]+oper[2].toString()+opera[2]+oper[3].toString();
                            break;
                        case 1:
                            exercise = oper[0].toString()+opera[0]+bracket[0]+oper[1].toString()+opera[1]+oper[2].toString()+bracket[1]+opera[2]+oper[3].toString();
                            break;
                        case 2:
                            exercise = oper[0].toString()+opera[0]+oper[1].toString()+opera[1]+bracket[0]+oper[2].toString()+opera[2]+oper[3].toString()+bracket[1];
                            break;
                        case 3:
                            exercise = bracket[0]+oper[0].toString()+opera[0]+oper[1].toString()+opera[1]+oper[2].toString()+bracket[1]+opera[2]+oper[3].toString();
                            break;
                        case 4:
                            exercise = oper[0].toString()+opera[0]+bracket[0]+oper[1].toString()+opera[1]+oper[2].toString()+opera[2]+oper[3].toString()+bracket[1];
                            break;
                        case 5:
                            exercise = bracket[0]+bracket[0]+oper[0].toString()+opera[0]+oper[1].toString()+bracket[1]+opera[1]+oper[2].toString()+bracket[1]+opera[2]+oper[3].toString();
                            break;
                        case 6:
                            exercise = bracket[0]+oper[0].toString()+opera[0]+bracket[0]+oper[1].toString()+opera[1]+oper[2].toString()+bracket[1]+bracket[1]+opera[2]+oper[3].toString();
                            break;
                        case 7:
                            exercise = oper[0].toString()+opera[0]+bracket[0]+bracket[0]+oper[1].toString()+opera[1]+oper[2].toString()+bracket[1]+opera[2]+oper[3].toString()+bracket[1];
                            break;
                        case 8:
                            exercise = oper[0].toString()+opera[0]+bracket[0]+oper[1].toString()+opera[1]+bracket[0]+oper[2].toString()+opera[2]+oper[3].toString()+bracket[1]+bracket[1];
                            break;
                        case 9:
                            exercise = bracket[0]+oper[0].toString()+opera[0]+oper[1].toString()+bracket[1]+opera[1]+bracket[0]+oper[2].toString()+opera[2]+oper[3].toString()+bracket[1];
                            break;
                    }
                }else {
                    // 若运算符为2个
                    // 随机生成一种表达式
                    int num = (int)Math.floor(Math.random()*2);
                    switch(num){
                        case 0:
                            exercise = bracket[0]+oper[0].toString()+opera[0]+oper[1].toString()+bracket[1]+opera[1]+oper[2].toString();
                            break;
                        case 1:
                            exercise = oper[0].toString()+opera[0]+bracket[0]+oper[1].toString()+opera[1]+oper[2].toString()+bracket[1];
                            break;
                    }
                }
            }else{
                // 题目不包含括号
                if(operatorNumber == 3){
                    // 若运算符为3个
                    exercise = oper[0].toString()+opera[0]+oper[1].toString()+opera[1]+oper[2].toString()+opera[2]+oper[3].toString();
                }else{
                    // 若运算符为2个
                    exercise = oper[0].toString()+opera[0]+oper[1].toString()+opera[1]+oper[2].toString();
                }
            }
        }
        return exercise;
    }

    /**
     * 随机生成一道题目的运算符数组
     * @param number 运算符数量
     * @return 运算符数组
     */
    public static String[] generateOperators(int number){
        // 运算符数组
        String[] oper = new String[number];
        for(int i = 0; i < number; i++){
            // 随机生成一个下标指定运算符
            oper[i] = operators[(int)(Math.random()*4)];
        }
        return oper;
    }

    /**
     * 随机生成一道题目的操作数数组
     * @param num 操作数数量
     * @param scope 操作数数值范围
     * @return 操作数数组
     */
    public static Operand[] generateOperands(int num,int scope){
        // 操作数数组
        Operand[] operands = new Operand[num];
        for (int i = 0; i < num; i++) {
            // 随机生成一个操作数对象
            operands[i] = generateOperand(scope);
        }
        return operands;
    }

    /**
     * 随机生成一个操作数对象
     * 操作数统一用分数存储，整数的分母为1
     * @param scope 操作数范围
     * @return 操作数对象
     */
    public static Operand generateOperand(int scope){
        // 分子
        int molecule = (int)(Math.random()*scope);
        // 分子不为0
        if(molecule == 0){
            molecule = 1;
        }
        // 分母
        int denominator;
        // 一半几率产生整数
        if(Math.random() < 0.5){
            denominator = 1;
        }else{
            denominator = (int)(Math.random()*scope);
            // 分母不为0和1，分子分母相同时，重置分母
            if(denominator <= 1 || denominator == molecule){
                denominator = scope-1;
            }
        }
        return new Operand(molecule,denominator);
    }

    /**
     * 根据Map中的value获取key集
     * @param map
     * @param value
     * @return 指定value的key集
     */
    public static ArrayList<String> getKey(Map<String,String> map, String value){
        ArrayList<String> list = new ArrayList<>();
        for(String key : map.keySet()){
            if(map.get(key).equals(value)){
                list.add(key);
            }
        }
        return list;
    }
}
