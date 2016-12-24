package tiquartet.CommonModule.vo;

import java.io.Serializable;

public class OrderInfoVO implements Serializable{
   //客户编号
   public int userID = -1;
   //儿童数量
   public int kids = -1;
   //客户名字
   public String guestRealName = "";
   //最晚订单执行时间
   public String latestTime = "";
   //住房人数
   public int numOfGuest = -1;
   //策略编号
   public int strategyID = -1;
   //价格
   public double price = -1;
   //订单编号
   public long orderID = -1;
}
