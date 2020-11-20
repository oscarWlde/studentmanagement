package com.example.demo.bean;


public class Course {

  private String courseId;
  private String courseOrder;
  private String courseNum;
  private String courseName;
  private int courseMemberNum;
  private String capacity;
  private int credit;
  private int courseTotalTimes;
  private String semster;
  private String type;

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  private String limitGrade;
  private String time;
  private String language;
  private String year;
  private String weekdata;
  private String startTime;

  public String getClassroomId() {
    return classroomId;
  }

  public void setClassroomId(String classroomId) {
    this.classroomId = classroomId;
  }

  private String classroomId;


  public String getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(String teacherId) {
    this.teacherId = teacherId;
  }

  private String teacherName;
  private String information;
  private String teacherId;

  @Override
  public String toString() {
    return "Course{" +
            "courseId='" + courseId + '\'' +
            ", courseOrder='" + courseOrder + '\'' +
            ", courseNum='" + courseNum + '\'' +
            ", courseName='" + courseName + '\'' +
            ", courseMemberNum=" + courseMemberNum +
            ", capacity='" + capacity + '\'' +
            ", credit=" + credit +
            ", courseTotalTimes=" + courseTotalTimes +
            ", semster='" + semster + '\'' +
            ", type='" + type + '\'' +
            ", limitGrade='" + limitGrade + '\'' +
            ", time='" + time + '\'' +
            ", language='" + language + '\'' +
            ", year='" + year + '\'' +
            ", weekdata='" + weekdata + '\'' +
            ", teacherName='" + teacherName + '\'' +
            ", information='" + information + '\'' +
            '}';
  }

  public Course(String courseId, String courseOrder, String courseNum, String courseName, int courseMemberNum, String capacity, int credit, int courseTotalTimes, String semster, String type, String limitGrade, String time, String language, String year, String weekdata, String teacherName, String information, String teacherId) {
    this.courseId = courseId;
    this.courseOrder = courseOrder;
    this.courseNum = courseNum;
    this.courseName = courseName;
    this.courseMemberNum = courseMemberNum;
    this.capacity = capacity;
    this.credit = credit;
    this.courseTotalTimes = courseTotalTimes;
    this.semster = semster;
    this.type = type;
    this.limitGrade = limitGrade;
    this.time = time;
    this.language = language;
    this.year = year;
    this.weekdata = weekdata;
    this.teacherName = teacherName;
    this.information = information;
    this.teacherId = teacherId;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }


  public String getCourseOrder() {
    return courseOrder;
  }

  public void setCourseOrder(String courseOrder) {
    this.courseOrder = courseOrder;
  }


  public String getCourseNum() {
    return courseNum;
  }

  public void setCourseNum(String courseNum) {
    this.courseNum = courseNum;
  }


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }





  public String getCapacity() {
    return capacity;
  }

  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }


  public int getCourseMemberNum() {
    return courseMemberNum;
  }

  public void setCourseMemberNum(int courseMemberNum) {
    this.courseMemberNum = courseMemberNum;
  }

  public int getCredit() {
    return credit;
  }

  public void setCredit(int credit) {
    this.credit = credit;
  }

  public int getCourseTotalTimes() {
    return courseTotalTimes;
  }

  public void setCourseTotalTimes(int courseTotalTimes) {
    this.courseTotalTimes = courseTotalTimes;
  }

  public String getSemster() {
    return semster;
  }

  public void setSemster(String semster) {
    this.semster = semster;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getLimitGrade() {
    return limitGrade;
  }

  public void setLimitGrade(String limitGrade) {
    this.limitGrade = limitGrade;
  }


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }


  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }


  public String getWeekdata() {
    return weekdata;
  }

  public void setWeekdata(String weekdata) {
    this.weekdata = weekdata;
  }


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }


  public String getInformation() {
    return information;
  }

  public void setInformation(String information) {
    this.information = information;
  }

}
