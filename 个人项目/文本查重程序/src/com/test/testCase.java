package com.test;

import com.main.StartClass;
import com.utils.Tf_IdfUtil;
import com.utils.WordsUtil;
import org.apdplat.word.segmentation.Word;
import org.junit.Test;

import java.util.List;

/**
 * 测试类
 */
public class testCase {
    // 测试主启动类
    @Test
    public void testStartClass() {
        StartClass.main(new String[]{"C:\\Users\\a\\Desktop\\test\\orig.txt","C:\\Users\\a\\Desktop\\test\\orig_0.8_add.txt","C:\\Users\\a\\Desktop\\test\\test.txt"});
    }

    // 测试分词功能
    @Test
    public void testWordsUtil() {
        // 测试正常情况下对文本进行分词
        List<Word> cutFileContent1 = WordsUtil.cutFileContent("今天是星期天,天气晴,今天晚上我要去看电影。");
        // 测试字符串倒序情况下对文本进行分词
        List<Word> cutFileContent2 = WordsUtil.cutFileContent("影电看去要我上晚天今,晴气天,天期星是天今。");
        // 测试字符串中加入乱码情况下对文本进行分词
        List<Word> cutFileContent3 = WordsUtil.cutFileContent("今!#%天是星期&&天,天气晴,今天晚**上我要去(看电@影。");
        // 测试字符串中有空格情况下对文本进行分词
        List<Word> cutFileContent4 = WordsUtil.cutFileContent("今天是星期  天,天  气晴,今天晚  上我要去看电  影。");
        // 测试空串情况下对文本进行分词
        List<Word> cutFileContent5 = WordsUtil.cutFileContent("");
        // 结果输出
        System.out.println("正常情况下: " + cutFileContent1);
        System.out.println("字符串倒序情况下: " + cutFileContent2);
        System.out.println("字符串中加入乱码情况下: " + cutFileContent3);
        System.out.println("字符串中有空格情况下" + cutFileContent4);
        System.out.println("空串情况下: " + cutFileContent5);
    }

    // 测试文本相关度算法
    @Test
    public void testTf_IdfUtil() {
        List<Word> sourceCutFileContent = WordsUtil.cutFileContent("今天是星期天,天气晴,今天晚上我要去看电影。");
        // 测试字符串相同情况
        List<Word> copyCutFileContent = WordsUtil.cutFileContent("今天是星期天,天气晴,今天晚上我要去看电影。");
        double duplicateCheckingRate = Tf_IdfUtil.similarity(sourceCutFileContent, copyCutFileContent);
        System.out.println("字符串相同的情况: " + duplicateCheckingRate);

        // 测试字符串倒序情况
        copyCutFileContent = WordsUtil.cutFileContent("影电看去要我上晚天今,晴气天,天期星是天今。");
        duplicateCheckingRate = Tf_IdfUtil.similarity(sourceCutFileContent, copyCutFileContent);
        System.out.println("字符串倒序的情况: " + duplicateCheckingRate);

        // 测试字符串增加符号情况
        copyCutFileContent = WordsUtil.cutFileContent("今!#%天是星期&&天,天气晴,今天晚**上我要去(看电@影。");
        duplicateCheckingRate = Tf_IdfUtil.similarity(sourceCutFileContent, copyCutFileContent);
        System.out.println("字符串增加符号情况: " + duplicateCheckingRate);

        // 测试字符串存在部分改动情况
        copyCutFileContent = WordsUtil.cutFileContent("今天是周末,天气晴朗,我晚上要去看电影。");
        duplicateCheckingRate = Tf_IdfUtil.similarity(sourceCutFileContent, copyCutFileContent);
        System.out.println("字符串存在部分改动情况: " + duplicateCheckingRate);

        // 测试字符串为空串的情况
        copyCutFileContent = WordsUtil.cutFileContent("今天是星期,天晴,今天晚我要去电影。");
        duplicateCheckingRate = Tf_IdfUtil.similarity(sourceCutFileContent, copyCutFileContent);
        System.out.println("字符串为空串情况: " + duplicateCheckingRate);

        // 测试字符串部分缺失情况
        sourceCutFileContent = WordsUtil.cutFileContent("");
        copyCutFileContent = WordsUtil.cutFileContent("");
        duplicateCheckingRate = Tf_IdfUtil.similarity(sourceCutFileContent, copyCutFileContent);
        System.out.println("字符串部分缺失情况: " + duplicateCheckingRate);
    }
}