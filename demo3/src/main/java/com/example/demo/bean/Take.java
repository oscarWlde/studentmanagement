package com.example.demo.bean;


public class Take {

  private String takeId;
  private String classroomId;
  private String takeDate;

  public String getSemster() {
    return semster;
  }

  public void setSemster(String semster) {
    this.semster = semster;
  }

  private String startTime;
  private String endTime;

  @Override
  public String toString() {
    return "Take{" +
            "takeId='" + takeId + '\'' +
            ", classroomId='" + classroomId + '\'' +
            ", takeDate='" + takeDate + '\'' +
            ", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", type='" + type + '\'' +
            ", information='" + information + '\'' +
            ", day='" + day + '\'' +
            ", week='" + week + '\'' +
            ", year='" + year + '\'' +
            ", semster='" + semster + '\'' +
            '}';
  }

  private String type;
  private String information;
  private String day;
  private String week;
  private String year;
  private String semster;


  public String getTakeId() {
    return takeId;
  }

  public void setTakeId(String takeId) {
    this.takeId = takeId;
  }


  public String getClassroomId() {
    return classroomId;
  }

  public void setClassroomId(String classroomId) {
    this.classroomId = classroomId;
  }


  public String getTakeDate() {
    return takeDate;
  }

  public void setTakeDate(String takeDate) {
    this.takeDate = takeDate;
  }


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }


  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getInformation() {
    return information;
  }

  public void setInformation(String information) {
    this.information = information;
  }


  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }


  public String getWeek() {
    return week;
  }

  public void setWeek(String week) {
    this.week = week;
  }


  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }


}
