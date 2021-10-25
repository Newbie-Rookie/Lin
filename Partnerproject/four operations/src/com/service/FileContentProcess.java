package com.service;

import java.io.*;

/**
 *  读写文件类
 */
public class FileContentProcess {
    /**
     * 读文件方法
     * @param filePath
     * @return
     */
    public static String read(String filePath){
        FileReader fr = null;
        // 存储读取文件的内容
        StringBuffer fileContent = new StringBuffer();
        try {
            // 创建字符输入流
            fr = new FileReader(filePath);
            // 创建字符数组进行读存储
            char[] chars = new char[512];
            // 统计每次读取字符数,读到文件末尾为-1
            int readChars;
            // 读文件
            while((readChars = fr.read(chars)) != -1){
                fileContent.append(new String(chars,0,readChars));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContent.toString();
    }

    /**
     * 写文件方法
     * @param filePath
     * @param fileContent
     */
    public static void write(String filePath,String fileContent){
        FileWriter fw = null;
        try {
            // 创建字符输出流对象
            fw = new FileWriter(filePath);
            // 写文件
            fw.write(fileContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fw != null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
