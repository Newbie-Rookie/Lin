package com.main;

import com.controller.InputController;

/**
 *  主启动类
 */
public class MainStart {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new InputController();
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }
}
