package com.controller;

import com.service.ProduceProgram;
import com.view.InputView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *  输入窗口控制端
 */
public class InputController extends InputView {
    // Status
    // 判断是否提交参数
    private boolean isCommitted;

    /**
     * 构造方法
     * 用户事件绑定
     */
    public InputController() {
        // 单击重置按钮时事件
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isCommitted) {
                    // 恢复初始参数
                    numberTextField.setText("10");
                    scopeTextField.setText("10");
                    // 组件控制
                    isCommitted = false;
                    serviceUISetting(isCommitted);
                }
            }
        });

        // 单击提交按钮时事件
        commitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isCommitted){
                    // 获取参数并判断参数是否要求
                    String s1 = numberTextField.getText().trim();
                    String s2 = scopeTextField.getText().trim();
                    // 生成题目数不为自然数
                    if(!s1.matches("[0-9]+")){
                        showMessage("输入的生成题目数不符合要求！",0);
                        return;
                    }
                    // 数值范围不为自然数
                    if(!s2.matches("[0-9]+")){
                        showMessage("输入的数值范围不符合要求！",0);
                        return;
                    }
                    // 组件控制
                    isCommitted = true;
                    serviceUISetting(isCommitted);
                    showMessage("提交成功！",1);
                    // 调用produce()方法生成题目
                    int number = Integer.parseInt(s1);
                    int scope = Integer.parseInt(s2);
                    ProduceProgram.produce(number,scope);
                }
            }
        });

        // 关闭窗口时事件
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 告知用户信息 —— 窗口
     * @param message
     * @param type
     */
    private void showMessage(String message,int type) {
        UIManager.put("Label.font",new Font("华文新魏",Font.BOLD,18));
        UIManager.put("Button.font",new Font("华文新魏",Font.BOLD,18));
        UIManager.put("OptionPane.background", Color.LIGHT_GRAY);
        UIManager.put("Panel.background", Color.LIGHT_GRAY);
        UIManager.put("Button.foreground", Color.CYAN);
        UIManager.put("Button.background", Color.BLACK);
        // 根据消息类型生成对应窗口
        switch(type){
            case JOptionPane.INFORMATION_MESSAGE:
                JOptionPane.showMessageDialog(frame, message, "Inform", type);
                break;
            case JOptionPane.ERROR_MESSAGE:
                JOptionPane.showMessageDialog(frame, message, "Error", type);
                break;
            default:
                break;
        }
    }
}
