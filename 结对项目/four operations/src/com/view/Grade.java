package com.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Grade {
    public static void main(String[] args) {
        grade("1,2,3","4,5");
    }

    public static void grade(String correct,String wrong) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        // 添加数据
        int c = (int)Math.ceil(correct.length()/2.0);
        dataset.setValue("Correct",c);
        int w = (int)Math.ceil(wrong.length()/2.0);
        dataset.setValue("Wrong",w);
        // 主标题、图标显示的数据集合、是否显示子标题、是否生成提示的标签、是否生成URL链接
        String title = "Correct: " + c + "道 (" + correct + ")  ,  " + "Wrong: " + w + "道 (" + wrong + ")";
        JFreeChart chart = ChartFactory.createPieChart3D("",dataset,true,true,true);
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

        ChartFrame frame = new ChartFrame("答题结果",chart);
        frame.setVisible(true);
        frame.setSize(520,350);
    }
}