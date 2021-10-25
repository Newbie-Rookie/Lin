package com.service;

import com.utils.FileProcessUtil;

import java.util.ArrayList;

/**
 *  答案对比类
 */
public class CompareAnswer {
    /**
     * 对比自填答案与题目答案，并将结果输出到Grade.txt文件中
     * @param answer
     * @return
     */
    public static int[] compare(ArrayList<String> answer){
        // 从Answers.txt文件中读出题目正确答案
        String answersPath = FileProcessUtil.resourceBundle("com/path","Answers.txt");
        String[] rightAnswerStr = FileContentProcess.read(answersPath).split("\n");
        // 题目数
        int len = answer.size();
        // 正确答案
        String[] rightAnswer = new String[len];
        // 剔除题号
        for(int i = 0;i < len;++i){
            rightAnswer[i] = rightAnswerStr[i].split("、")[1];
        }
        // 正确题目
        StringBuffer correct = new StringBuffer();
        // 错误题目
        StringBuffer wrong = new StringBuffer();
        // 循环判断答案
        for(int i = 0;i < len;++i){
            if(rightAnswer[i].equals(answer.get(i))){
                correct.append(i + 1);
                correct.append(",");
            }else{
                wrong.append(i + 1);
                wrong.append(",");
            }
        }
        // 正确/错误题目数
        int c = 0,w = 0;
        // 删除最后一个逗号
        if(correct.length() != 0){
            correct.deleteCharAt(correct.length()-1);
            c = correct.toString().split(",").length;
        }
        if(wrong.length() != 0){
            wrong.deleteCharAt(wrong.length()-1);
            w = wrong.toString().split(",").length;
        }
        // 生成结果
        String result = "Correct: " + c + " (" + correct + ")\n" +
                        "Wrong: " + w + " (" + wrong + ")";
        // 获取对比结果文件路径
        String gradePath = FileProcessUtil.resourceBundle("com/path","Grade.txt");
        // 对比结果写入文件
        FileContentProcess.write(gradePath,result);
        return new int[]{c,w};
    }
}
