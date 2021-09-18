package com.utils;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

import java.io.File;
import java.util.List;

/**
 *  文本内容分词工具类
 */
public class WordsUtil {
    /**
     * 文件内容进行分词 —— 调用分词算法
     * @param fileContent 传入文件内容
     * @return List<Word> 返回分词结果
     */
    public static List<Word> cutFileContent(String fileContent){
        if("".equals(fileContent)){
            throw new NullPointerException("文件内容为空! ");
        }
        // 调用word分词器中的分词函数
        List<Word> cutFileContent = WordSegmenter.segWithStopWords(fileContent);
        // 返回分词后的内容
        return cutFileContent;
    }

    /**
     * 文件内容进行分词 —— 调用分词算法
     * @param file 传入文件对象
     * @return List<Word> 返回分词结果
     */
    public static List<Word> cutFileContent(File file){
        // 传入文件对象,获取文件的绝对路径
        String fileContent = FileUtil.read(file.getAbsolutePath());
        // 调用word分词器中的分词函数
        List<Word> cutFileContent = WordSegmenter.segWithStopWords(fileContent);
        // 返回分词后的内容
        return cutFileContent;
    }
}