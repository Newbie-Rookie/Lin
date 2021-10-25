package com.text;

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
    public void testGenerateExercises() throws Exception {
        GenerateExcerises.generateExercises(10000,10);
    }

    /**
     * 测试程序生成的答案是否完全正确，注意需要先生成题目文件和答案文件
     * @throws Exception
     */
    @Test
    public void testCheck() throws Exception {
        CompareAnswer.compare(new ArrayList<>());
    }
}
