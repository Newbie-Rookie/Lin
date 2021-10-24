package com.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 *  文件读写相关的工具类
 */
public class FileProcessUtil {
    /**
     * 绑定配置文件并获取配置文件内容
     * @param srcPath
     * @param name
     * @return
     */
    public static String resourceBundle(String srcPath, String name){
        ResourceBundle bundle = ResourceBundle.getBundle(srcPath);
        String source = bundle.getString(name);
        return source;
    }

    /**
     * 获取写文件相应的字符输出流
     * @param filePath 文件绝对路径
     * @return 返回字符输出流
     */
    public static FileWriter write(String filePath){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileWriter;
    }

    /**
     * 获取读文件相应的字符输入流
     * @param filePath 文件绝对路径
     * @return 返回字符输入流
     */
    public static FileReader read(String filePath){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileReader;
    }
}
