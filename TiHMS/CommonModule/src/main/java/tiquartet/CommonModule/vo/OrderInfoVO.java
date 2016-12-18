package tiquartet.CommonModule.vo;

import java.io.Serializable;

public class OrderInfoVO implements Serializable{
   //客户编号
   public int userID;
   //酒店编号
   public int hotelID;
   //儿童数量
   public int kids;
   //客户名字
   public String guestRealName;
   //最晚订单执行时间
   public String latestTime;
   //住房人数
   public int numOfGuest;
   //策略编号
   public int strategyID;
   //价格
   public double price;
   //订单编号
   public long orderID;
}
