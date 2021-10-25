package com.view;

import com.model.MyTableModel;
import com.service.FileContentProcess;
import com.utils.FileProcessUtil;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 *  题目窗口
 */
public class TopicsView {
    // UI
    public JFrame frame;
    // 提示面板
    public JPanel messagePanel;
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
        frame.setSize(500, 400);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());

        // 窗口在屏幕中间显示
        Toolkit kit = Toolkit.getDefaultToolkit();
        int x = (kit.getScreenSize().width - frame.getWidth()) / 2;
        int y = (kit.getScreenSize().height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        // 题目说明 + 输入提示
        JLabel jLabel_1 = new JLabel("<HTML><font color=black>题目格式说明: </font><font color=blue>1'1/3为带分数，表示整数1+分数1/3，即假分数4/3</font></html>");
        jLabel_1.setFont(new Font("华文新魏",Font.BOLD,15));
        jLabel_1.setForeground(Color.BLUE);
        jLabel_1.setBackground(Color.white);
        JLabel jLabel_2 = new JLabel("<HTML><font color=black>答案格式要求: </font><font color=blue>真分数(分子/分母)，假分数(整数'分子/分母)</font></html>");
        jLabel_2.setFont(new Font("华文新魏",Font.BOLD,15));
        jLabel_2.setForeground(Color.BLUE);
        jLabel_2.setBackground(Color.white);
        // 提示面板
        messagePanel = new JPanel(new GridLayout(2,1));
        messagePanel.add(jLabel_1,"North");
        messagePanel.add(jLabel_2,"South");
        messagePanel.setBackground(Color.WHITE);
        frame.add(messagePanel,"North");

        // 表头
        String[] columnNames = {"题目", "答案"};
        // 读文件并取出题目
        String path = FileProcessUtil.resourceBundle("com/path","Exercises.txt");
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
        // 设置题目列的宽度
        table.getColumnModel().getColumn(0).setPreferredWidth(300);

        // 设置行高
        table.setRowHeight(30);
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
