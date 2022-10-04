package com.fx.creationalpatterns.prototypepattern.clone;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data // 自动生成getter setter 和 toString方法
@Accessors(chain = true) // 开启链式编程
class WeekReport implements Cloneable {
    private int id;
    private String emp;
    private String summary;
    private String plain;
    private String suggestion;
    private String department;
    private Date submitDate;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        WeekReport cloneWeekReport = (WeekReport) super.clone();
        // Date类型需要自己手动进行克隆
        Date cloneSubmitDate = (Date) cloneWeekReport.getSubmitDate().clone();
        cloneWeekReport.setSubmitDate(cloneSubmitDate);
        return cloneWeekReport;
    }
}

public class AppTest {
    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
        WeekReport weekReport = new WeekReport();
        weekReport.setEmp("奈李")
                .setSummary("本周主要完成了七大设计原则和工厂模式的学习")
                .setPlain("在下周的工作中完成原型模式学习")
                .setDepartment("互联网事业部")
                .setSubmitDate(new Date());
        // 简单输出一下
        System.out.println("weekReport的时间：" + weekReport.getSubmitDate());
        // 第二周周报 直接copy第一周的周报改改进行
        WeekReport weekReport2 = (WeekReport) weekReport.clone();
        weekReport2.setSummary("本周主要完成了剩下设计模式的学习")
                .setPlain("在下周会完成阿里巴巴开发手册学习");
        // 这样才是修改同一个引用指向堆空间里面的值
        weekReport2.getSubmitDate().setTime(0);
        System.out.println(weekReport == weekReport2);
        System.out.println("weekReport2的时间：" + weekReport2.getSubmitDate());
        System.out.println("weekReport的时间：" + weekReport.getSubmitDate());
    }
}
