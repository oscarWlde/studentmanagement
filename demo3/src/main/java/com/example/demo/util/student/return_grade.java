package com.example.demo.util.student;

public class return_grade {
    private String courseId;
    private String courseName;
    private String teacherName;
    private String credit;
    private String pgrade;
    private String grade;
    private String totalgrade;


    public String getTotalgrade() {
        return totalgrade;
    }

    public void setTotalgrade(String totalgrade) {
        this.totalgrade = totalgrade;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPgrade() {
        return pgrade;
    }

    public void setPgrade(String pgrade) {
        this.pgrade = pgrade;
    }



    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


}
