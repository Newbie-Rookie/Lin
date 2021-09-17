package com.utils;

import java.io.*;

/**
 *  文件处理工具类：读文件、写文件、获取文件名
 */
public class FileUtil {
    /**
     * 读取文件内容的方法
     * @param path 传入文件的绝对路径
     * @return 将文件内容转化为字符串输出
     */
    public static String read(String path) {
        FileInputStream fis = null;
        // 存放读取到的文件内容
        StringBuffer fileContent = new StringBuffer();
        try {
            // 创建文件字节输入流
            fis = new FileInputStream(path);
            // 创建1M大小的字节数组进行文件读取
            byte[] bytes = new byte[1024];
            // 读取的字节数,读到文件末尾为-1
            int readCount = 0;
            // 读取文件,直到文件末尾
            while((readCount = fis.read(bytes)) != -1){
                fileContent.append(new String(bytes,0,readCount));
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭文件字节输入流
            try {
                if(fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 返回文件内容(以字符串形式字符串)
        return fileContent.toString();
    }

    /**
     * 向指定文件中写入内容的方法
     * @param path 传入文件的绝对路径
     * @param fileContent 传入想写入的内容
     */
    public static void write(String path,String fileContent) {
        FileWriter fos = null;
        try {
            // 创建文件对象
            // 如果存放结果的文件不存在,会自动创建一个txt文件
            File file = new File(path);
            // 创建文件字符输出流
            fos = new FileWriter(file);
            // 写数据
            fos.write(fileContent);
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            // 关闭文件字节输出流
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取文件名的方法
     * @param path 传入文件的绝对路径
     * @return 返回文件名
     */
    public static String getFileName(String path) {
        // 将文件的绝对路径以字符"/"进行分割
        String[] strs = path.split("/");
        // 如果传入的文件路径是以字符"\\"隔开
        // 需要将字符"\\"替换为"/"
        if(strs.length == 1){
            path = path.replace('\\','/');
            strs = path.split("/");
        }
        // 返回文件名
        return strs[strs.length-1];
    }
}