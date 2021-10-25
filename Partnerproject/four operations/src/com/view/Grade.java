package com.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Grade {
    // 父窗口
    public JFrame fatherFrame;
    // UI(图表窗口)
    public ChartFrame frame;
    // 图表
    public JFreeChart chart;

    public Grade(JFrame fatherFrame,int correct,int wrong){
        this.fatherFrame = fatherFrame;
        initUI(correct,wrong);
    }

    public void initUI(int correct,int wrong) {
        // 标题
        String title = "详细结果查看Grade.txt文件";
        // 图标数据
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Correct",correct);
        dataset.setValue("Wrong",wrong);
        // 主标题、图标显示的数据集合、是否显示子标题、是否生成提示的标签、是否生成URL链接
        chart = ChartFactory.createPieChart3D("",dataset,true,true,true);
        chart.setTitle(new TextTitle(title,new Font("华文新魏",Font.BOLD,25)));
        chart.setBackgroundPaint(Color.WHITE);
        // 处理图形上的乱码
        // 处理主标题的乱码
        chart.getTitle().setFont(new Font("华文新魏",Font.BOLD,15));
        // 处理子标题乱码
        chart.getLegend().setItemFont(new Font("华文新魏", Font.BOLD,15));
        chart.getLegend().setBackgroundPaint(Color.WHITE);
        // 获取图表区域对象
        PiePlot3D pie = (PiePlot3D)chart.getPlot();
        // 处理图像上的乱码
        pie.setLabelFont(new Font("华文新魏",Font.BOLD,15));
        // 设置图形的生成格式为 (Wrong: 2道 (10%))
        String format = "{0}: {1}道 ({2})";
        pie.setLabelGenerator(new StandardPieSectionLabelGenerator(format,NumberFormat.getNumberInstance(),new DecimalFormat( "0.00%")));
        pie.setLabelBackgroundPaint(Color.ORANGE);
        // 指定section轮廓线的颜色
        pie.setBaseSectionOutlinePaint(Color.WHITE);
        // 指定section轮廓线的厚度
        pie.setSectionOutlineStroke(new BasicStroke(3));
        // 指定section的色彩
        pie.setSectionPaint(0,Color.BLUE);
        pie.setSectionPaint(1,Color.PINK);

        frame = new ChartFrame("答题结果",chart);
        frame.setVisible(true);
        frame.setSize(520,350);

        // 窗口在屏幕中间显示
        Toolkit kit = Toolkit.getDefaultToolkit();
        int x = (kit.getScreenSize().width - frame.getWidth()) / 2;
        int y = (kit.getScreenSize().height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        // 关闭窗口时事件
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fatherFrame.setEnabled(true);
            }
        });
    }
}