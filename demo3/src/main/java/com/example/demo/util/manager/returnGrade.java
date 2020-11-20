package com.example.demo.util.manager;

import org.springframework.stereotype.Service;

public class returnGrade {
    private String stuId;
    private String stuName;
    private String grade;
    private String totalgrade;


    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTotalgrade() {
        return totalgrade;
    }

    public void setTotalgrade(String totalgrade) {
        this.totalgrade = totalgrade;
    }
}
