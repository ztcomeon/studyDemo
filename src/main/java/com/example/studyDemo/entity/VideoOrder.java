package com.example.studyDemo.entity;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-06-02
 * @since 1.0.0
 */
public class VideoOrder {

  private String tradeNo;
  private int money;
  private String title;

  public VideoOrder(String tradeNo, String title, int money) {
    this.tradeNo = tradeNo;
    this.title = title;
    this.money = money;
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof VideoOrder) {
      VideoOrder o1 = (VideoOrder) obj;
      return title.equals(o1.getTitle());
    }
    return super.equals(obj);
  }

  @Override
  public String toString() {
    return "VideoOrder{" + "money=" + money + ", title='" + title + '\'' + '}';
  }

  @Override
  public int hashCode() {
    return title.hashCode();
  }
}
