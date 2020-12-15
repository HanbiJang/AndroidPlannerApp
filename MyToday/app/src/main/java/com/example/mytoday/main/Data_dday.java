package com.example.mytoday.main;

public class Data_dday {

    private String title;
    private Integer thisPlanNum;
    private Integer leaveday;
    private String plan_date_for_dday;

    public Integer getLeaveday() {
        return leaveday;
    }

    public void setLeaveDay(Integer leaveday) {
        this.leaveday = leaveday;
    }

    public String getPlan_date_for_dday() {
        return plan_date_for_dday;
    }

    public void setPlan_date_for_dday(String plan_date_for_dday) {
        this.plan_date_for_dday = plan_date_for_dday;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThisPlanNum(Integer thisPlanNum) {
        this.thisPlanNum = thisPlanNum;
    }

    public Integer getThisPlanNum() {
        return thisPlanNum;
    }
}