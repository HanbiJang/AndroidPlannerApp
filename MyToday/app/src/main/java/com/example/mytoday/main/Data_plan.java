package com.example.mytoday.main;

public class Data_plan {

    private String title;
    private String content;
    private Integer thisPlanNum;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setThisPlanNum(Integer thisPlanNum) {
        this.thisPlanNum = thisPlanNum;
    }

    public Integer getThisPlanNum() {
        return thisPlanNum;
    }
}