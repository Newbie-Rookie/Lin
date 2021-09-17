package com.utils;

import org.apdplat.word.segmentation.Word;

import java.util.ArrayList;
import java.util.List;

/**
 *  TF-IDF算法计算文本相似度
 */
public class Tf_IdfUtil {
    public static void main(String[] args) {
        String sourceFileContent = FileUtil.read("C:\\Users\\a\\Desktop\\课程\\大三\\软件工程\\博客园\\Project1\\测试文本\\orig.txt");
        String copyFileContent = FileUtil.read("C:\\Users\\a\\Desktop\\课程\\大三\\软件工程\\博客园\\Project1\\测试文本\\orig_0.8_add.txt");
        List<Word> word1 = WordsUtil.cutfileContent(sourceFileContent);
        List<Word> word2 = WordsUtil.cutfileContent(copyFileContent);
        for(Word word:word1){
            System.out.println(word);
        }
        for(Word word:word2){
            System.out.println(word);
        }
        System.out.println(similarity(word1,word2));
    }

    /**
     * 计算文本相似度算法
     * @param word1 传入原文件的分词内容
     * @param word2 传入抄袭文件的分词内容
     * @return 返回文本相似度
     */
    public static double similarity(List<Word> word1,List<Word> word2){
        // 构建词集wordSet
        List<Word> wordSet = new ArrayList<>();
        // 遍历原文件分词结果
        for(Word word : word1){
            if(!wordSet.contains(word)){
                wordSet.add(word);
            }
        }
        // 遍历抄袭文件分词结果
        for(Word word : word2){
            if(!wordSet.contains(word)){
                wordSet.add(word);
            }
        }

        // 计算两文件的分词词频
        // 计算原文件的分词词频
        int[] sourceWordFrequency = new int[wordSet.size()];
        int index = 0;
        for(Word word : wordSet){
            int num = 0;
            for(Word w : word1){
                if(word.equals(w)){
                    num++;
                }
            }
            sourceWordFrequency[index++] = num;
        }
        // 计算抄袭文件的分词词频
        int[] copyWordFrequency = new int[wordSet.size()];
        index = 0;
        for(Word word : wordSet){
            int num = 0;
            for(Word w : word2){
                if(word.equals(w)){
                    num++;
                }
            }
            copyWordFrequency[index++] = num;
        }

        // 计算文本相似度
        double molecule = 0;
        double denominator1 = 0;
        double denominator2 = 0;
        for(int i = 0;i < sourceWordFrequency.length;++i){
            // 分子
            molecule += sourceWordFrequency[i]*copyWordFrequency[i];
            // 分母组成
            denominator1 += Math.pow(sourceWordFrequency[i],2);
            denominator2 += Math.pow(copyWordFrequency[i],2);
        }
        // 分母
        double denominator = Math.sqrt(denominator1)*Math.sqrt(denominator2);
        // 文本相似度
        double duplicateCheckingRate = molecule/denominator;
        // 返回文本相似度
        return duplicateCheckingRate;
    }
}
