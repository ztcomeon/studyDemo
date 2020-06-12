package com.example.studyDemo.entity;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-06-02
 * @since 1.0.0
 */
public class Student {

  private String province;
  private int number;

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public Student(String province, int number) {
    this.province = province;
    this.number = number;
  }

  @Override
  public String toString() {
    return "Student{" + "province='" + province + '\'' + ", number=" + number + '}';
  }
}
