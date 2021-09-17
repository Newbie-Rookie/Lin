package com.utils;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

import java.io.File;
import java.util.List;

/**
 *  文本内容分词工具类
 */
public class WordsUtil {
    public static void main(String[] args) {
        List<Word> list = cutfileContent("今天是星期天，天气晴，今天晚上我要去看电影。");
        for(Word word : list){
            System.out.println(word);
        }

        File file = new File("C:\\Users\\a\\Desktop\\课程\\大三\\软件工程\\博客园\\Project1\\测试文本\\orig.txt");
        List<Word> list1 = cutfileContent(file);
        for(Word word : list1){
            System.out.println(word);
        }
    }

    /**
     * 文件内容进行分词 —— 调用分词算法
     * @param fileContent 传入文件内容
     * @return List<Word> 返回分词结果
     */
    public static List<Word> cutfileContent(String fileContent){
        // 调用word分词器中的分词函数
        List<Word> cutfileContent = WordSegmenter.segWithStopWords(fileContent);
        // 返回分词后的内容
        return cutfileContent;
    }

    /**
     * 文件内容进行分词 —— 调用分词算法
     * @param file 传入文件对象
     * @return List<Word> 返回分词结果
     */
    public static List<Word> cutfileContent(File file){
        // 传入文件对象,获取文件的绝对路径,
        String fileContent = FileUtil.read(file.getAbsolutePath());
        // 调用word分词器中的分词函数
        List<Word> cutfileContent = WordSegmenter.segWithStopWords(fileContent);
        // 返回分词后的内容
        return cutfileContent;
    }
}