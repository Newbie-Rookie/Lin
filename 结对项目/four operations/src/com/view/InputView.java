package com.view;

import javax.swing.*;
import java.awt.*;

/**
 *  输入窗口
 */
public class InputView {
    // UI
    public JFrame frame;
    // 面板
    public JPanel settingPanel;
    // 题目个数输入框、数值范围输入框
    public JTextField numberTextField, scopeTextField;
    // 重置按钮、提交按钮
    public JButton resetButton, commitButton;

    /**
     * 构造方法
     */
    public InputView() {
        initUI();
    }

    /**
     * UI初始化方法
     */
    private void initUI() {
        // 设置窗口标题、大小以及布局
        frame = new JFrame("四则运算程序");
        frame.setFont(new Font("Times New Roman",Font.BOLD,15));
        frame.setSize(450, 350);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        // 配置面板
        settingPanel = new JPanel();
        // 设置布局为一行八列
        settingPanel.setLayout(new GridLayout(3, 2,10,5));
        settingPanel.setBackground(Color.WHITE);
        // 为配置面板添加组件
        JLabel jlabel_1 = new JLabel("生成题目数(自然数)",JLabel.CENTER);
        jlabel_1.setFont(new Font("华文新魏",Font.PLAIN,20));
        settingPanel.add(jlabel_1);

        JLabel jlabel_2 = new JLabel("数值范围(自然数)",JLabel.CENTER);
        jlabel_2.setFont(new Font("华文新魏",Font.PLAIN,20));
        settingPanel.add(jlabel_2);

        // 面板(设置默认参数)
        numberTextField = new JTextField("10");
        numberTextField.setHorizontalAlignment(JTextField.CENTER);
        numberTextField.setFont(new Font("Times New Roman",Font.BOLD,30));
        numberTextField.setDisabledTextColor(Color.RED);
        numberTextField.setBackground(Color.LIGHT_GRAY);
        numberTextField.setForeground(Color.BLUE);
        settingPanel.add(numberTextField);

        scopeTextField = new JTextField("10");
        scopeTextField.setHorizontalAlignment(JTextField.CENTER);
        scopeTextField.setFont(new Font("Times New Roman",Font.BOLD,30));
        scopeTextField.setDisabledTextColor(Color.RED);
        scopeTextField.setBackground(Color.LIGHT_GRAY);
        scopeTextField.setForeground(Color.BLUE);
        settingPanel.add(scopeTextField);

        // 提交组件
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Times New Roman",Font.BOLD,20));
        resetButton.setForeground(Color.CYAN);
        resetButton.setBackground(Color.BLACK);
        settingPanel.add(resetButton);

        commitButton = new JButton("Commit");
        commitButton.setFont(new Font("Times New Roman",Font.BOLD,20));
        commitButton.setForeground(Color.CYAN);
        commitButton.setBackground(Color.BLACK);
        settingPanel.add(commitButton);

        // 向窗口中添加面板
        frame.add(settingPanel, "Center");
        frame.setVisible(true);

        // 设置按钮以及文本框的默认状态
        serviceUISetting(false);
    }

    /**
     * 标签、组件控制方法
     * @param isCommitted
     */
    public void serviceUISetting(boolean isCommitted) {
        numberTextField.setEnabled(!isCommitted);
        scopeTextField.setEnabled(!isCommitted);
        commitButton.setEnabled(!isCommitted);
        resetButton.setEnabled(isCommitted);
    }
}
