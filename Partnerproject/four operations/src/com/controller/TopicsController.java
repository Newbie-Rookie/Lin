package com.controller;

import com.model.Operand;
import com.service.CompareAnswer;
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
    public TopicsController(JFrame fatherFrame){
        super(fatherFrame);

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
                    // 未填、填入0、除0外的整数
                    if("".equals(value) || "0".equals(value) || value.matches("-?[1-9][0-9]*")){
                        answer.add(value);
                        continue;
                    }
                    // 真分数和带分数
                    if(value.matches("-?[0-9]+/[0-9]+") || value.matches("-?[0-9]+'[0-9]+/[0-9]+")){
                        answer.add(Operand.stringToOperand(value).toString());
                        continue;
                    }
                    showMessage("第" + (i + 1) + "题答案输入格式不正确！\n正确格式:整数(非负整数)，真分数(分子/分母)，假分数(整数'分子/分母)",0);
                    return;
                }
                // 计算对比结果并将结果写入文件
                int[] caw = CompareAnswer.compare(answer);
                // 生成提示信息
                showMessage("提交成功！",1);
                // 将当前窗口锁定
                frame.setEnabled(false);
                // 生成结果图
                new Grade(frame,caw[0],caw[1]);
            }
        });

        // 关闭窗口时事件
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 新建一输入窗口
                fatherFrame.setVisible(true);
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
