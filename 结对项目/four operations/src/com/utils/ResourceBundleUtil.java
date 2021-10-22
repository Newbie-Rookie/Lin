package com.utils;

import java.util.ResourceBundle;

/**
 *  资源绑定工具类
 */
public class ResourceBundleUtil {
    /**
     * 绑定配置文件
     * @param srcPath
     * @param name
     * @return
     */
    public static String resourceBundle(String srcPath,String name){
        ResourceBundle bundle = ResourceBundle.getBundle(srcPath);
        String source = bundle.getString(name);
        return source;
    }
}
