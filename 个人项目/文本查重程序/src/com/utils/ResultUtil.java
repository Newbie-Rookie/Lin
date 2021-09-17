package com.utils;

import java.text.DecimalFormat;

/**
 *  查重结果处理工具类
 */
public class ResultUtil {
    /**
     * 获得写入查重结果文件的内容的方法
     * @param sourceFileName 传入原文件名
     * @param copyFileName 传入抄袭文件名
     * @param duplicateCheckingRate 传入查重率
     * @return
     */
    public static String writeContent(String sourceFileName, String copyFileName, double duplicateCheckingRate) {
        // 查重率表示为百分比形式
        duplicateCheckingRate *= 100;
        // 查重率保留小数点后两位
        DecimalFormat df = new DecimalFormat("##.##");
        String rate = df.format(duplicateCheckingRate);
        // 返回写入查重结果文件的内容
        return "原文件: " + sourceFileName + "\n" +
                "抄袭文件: " + copyFileName + "\n" +
                "查重率: " + rate + "%";
    }
}
