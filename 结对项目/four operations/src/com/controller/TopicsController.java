package com.controller;

import com.service.CompareTheAnswer;
import com.view.Grade;
import com.view.TopicsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 *  题目窗口控制端
 */
public class TopicsController extends TopicsView {
    public TopicsController(){
        // 单击提交按钮时事件
        commitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArrayList<String> answer = new ArrayList<>();
                // 行数
                int row = table.getRowCount();
                // 获取自填答案
                for (int i = 0; i < row; i++) {
                    String value = (String)table.getValueAt(i,1);
                    answer.add(value);
                }
                // 获取对比结果
                String[] correctAndWrong = CompareTheAnswer.compare(answer);
                // 生成结果图
                Grade.grade(correctAndWrong[0],correctAndWrong[1]);
                // showMessage(result,0);
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
