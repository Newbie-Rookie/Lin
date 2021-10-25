package com.test;

import com.service.CompareAnswer;
import com.service.GenerateExcerises;
import org.junit.Test;

import java.util.ArrayList;

public class TestAll {
    /**
     * 测试生成10000道题目
     * @throws Exception
     */
    @Test
    public void testGenerateExercises() {
        long start = System.currentTimeMillis();
        try {
            GenerateExcerises.generateExercises(10000,10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 测试程序生成的答案是否完全正确，注意需要先生成题目文件和答案文件
     */
    @Test
    public void testCheck() {
        long start = System.currentTimeMillis();
        CompareAnswer.compare(new ArrayList<>());
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }
}
