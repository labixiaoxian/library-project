package com.wyu.dto;

import java.util.List;

/**
 * Created by XiaoXian on 2020/5/14.
 */
public class EchartSeries {
    private String name;
    private String type = "bar";
    private List<Integer> data;
    private String barWidth = "60%";

    public String getBarWidth() {
        return barWidth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }
}
