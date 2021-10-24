package com.controller;

import com.model.Operand;
import com.service.CompareAnswer;
import com.view.Grade;
import com.view.TopicsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                // 答案行数
                int row = table.getRowCount();
                // 存储自填答案
                ArrayList<String> answer = new ArrayList<>();
                // 获取自填答案
                for (int i = 0; i < row; i++) {
                    String value = (String)table.getValueAt(i,1);
                    // 判断输入答案格式是否正确
                    // 未填或填入0
                    if("".equals(value) || "0".equals(value)){
                        answer.add(value);
                        continue;
                    }
                    // 除0外的非负整数
                    if(value.matches("[1-9][0-9]*")){
                        answer.add(value);
                        continue;
                    }
                    // 分数
                    if(value.matches("[0-9]+/[0-9]+")){
                        answer.add(Operand.stringToOperand(value).toString());
                        continue;
                    }
                    showMessage("第" + (i + 1) + "题答案输入格式不正确！\n正确格式为整数正常输入，分数(真分数/假分数)均为分子/分母",0);
                    return;
                }
                // 计算对比结果并将结果写入文件
                int[] caw = CompareAnswer.compare(answer);
                // 生成提示信息
                showMessage("提交成功！",1);
                // 生成结果图
                new Grade(caw[0],caw[1]);
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
