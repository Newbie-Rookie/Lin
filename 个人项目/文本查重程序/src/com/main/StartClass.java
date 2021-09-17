package com.main;

import com.utils.FileUtil;
import com.utils.ResultUtil;
import com.utils.Tf_IdfUtil;
import com.utils.WordsUtil;
import org.apdplat.word.segmentation.Word;

import java.util.List;

/**
 *  主启动类
 */
public class StartClass {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 从命令行输入的路径名读取对应的文件
        // 将原文件的内容转化为对应的字符串
        String sourceFileContent = FileUtil.read(args[0]);
        // 将抄袭文件的内容转化为对应的字符串
        String copyFileContent = FileUtil.read(args[1]);

        // 对文本内容进行分词
        List<Word> sourceCutFileContent = WordsUtil.cutFileContent(sourceFileContent);
        List<Word> copyCutFileContent = WordsUtil.cutFileContent(copyFileContent);

        // 计算文本相似度
        double duplicateCheckingRate = Tf_IdfUtil.similarity(sourceCutFileContent,copyCutFileContent);

        // 原文件名
        String sourceFileName = FileUtil.getFileName(args[0]);
        // 抄袭文件名
        String copyFileName = FileUtil.getFileName(args[1]);
        // 结果文件名
        String ansFileName = FileUtil.getFileName(args[2]);

        // 处理写入结果文件的内容
        String writeContent = ResultUtil.writeContent(sourceFileName,copyFileName,duplicateCheckingRate);

        // 把文本相似度写入结果文件中
        FileUtil.write(args[2],writeContent);
        System.out.println("查重结果已写入文件: " + ansFileName);
        System.out.println("该文件绝对路径为: " + args[2]);

        // 输出此次耗时
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
        // 退出程序
        System.exit(0);
    }
}