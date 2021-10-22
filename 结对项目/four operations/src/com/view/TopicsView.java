package com.view;

import com.model.MyTableModel;
import com.service.FileContentProcess;
import com.utils.ResourceBundleUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 *  题目窗口
 */
public class TopicsView {
    // UI
    public JFrame frame;
    // 题目表格
    public JTable table;
    // 滚动面板
    public JScrollPane jscrollPane;
    // 提交面板
    public JPanel commitPanel;
    // 提交按钮
    public JButton commitButton;

    /**
     * 构造方法
     */
    public TopicsView () {
        initUI();
    }

    /**
     * UI初始化方法
     */
    private void initUI() {
        // 设置窗口标题、大小以及布局
        frame = new JFrame("答题");
        frame.setFont(new Font("Times New Roman",Font.BOLD,15));
        frame.setSize(450, 350);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());

        // 表头
        String[] columnNames = {"题目", "答案"};
        // 读文件并取出题目
        String path = ResourceBundleUtil.resourceBundle("com/path","Exercises.txt");
        String[] problems = FileContentProcess.read(path).split("\n");
        String[][] tableValues = new String[problems.length][2];
        for (int i = 0; i < problems.length; i++) {
            tableValues[i][0] = problems[i];
            tableValues[i][1] = "";
        }
        table = new JTable(new MyTableModel(columnNames,tableValues));

        // 表头设置
        JTableHeader head = table.getTableHeader();
        head.setFont(new Font("华文新魏",Font.BOLD,20));
        head.setForeground(Color.ORANGE);
        head.setBackground(Color.BLACK);
        // 设置行高
        table.setRowHeight(30);
        // 设置表格居中显示
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
        // 设置列不可拖动
        table.getTableHeader().setReorderingAllowed(false);
        table.setGridColor(Color.PINK);
        table.setFont(new Font("华文新魏",Font.BOLD,15));
        table.setForeground(Color.BLACK);
        table.setSelectionForeground(Color.BLUE);
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        // 将表格添加到滚动面板中
        jscrollPane = new JScrollPane(table);
        frame.getContentPane().add(jscrollPane,"Center");

        // 提交按钮
        commitButton = new JButton("Commit");
        commitButton.setFont(new Font("Times New Roman",Font.BOLD,20));
        commitButton.setForeground(Color.CYAN);
        commitButton.setBackground(Color.BLACK);
        commitPanel = new JPanel(new GridLayout(1, 1,10,5));
        commitPanel.add(commitButton,"Center");
        frame.add(commitPanel,"South");
        frame.setVisible(true);

        // 设置按钮以及文本框的默认状态
        serviceUISetting(false);
    }

    /**
     * 标签、组件控制方法
     * @param isCommitted
     */
    public void serviceUISetting(boolean isCommitted) {
        commitButton.setEnabled(!isCommitted);
    }
}
