package com.exception;

/**
 *  自定义异常类：处理空字符串(文件内容为空)
 */
public class NullStringException extends Exception{
    public NullStringException() {
    }

    public NullStringException(String message) {
        super(message);
    }
}
