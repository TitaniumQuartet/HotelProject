package tiquartet.CommonModule.vo;


import java.io.Serializable;

import tiquartet.CommonModule.util.StrategyType;

public class StrategyVO implements Serializable{
	 //酒店编号
	 public int hotelID;
	 //商圈编号
	 public int circleID;
	 //策略介绍
     public String strategyIntro;
     //折扣
     public double discount;
     //策略编号
     public int strategyID;
 	 //门槛上线
 	 public double[] memberThreShold;
 	 //会员等级对应折扣
 	 public double[] memberDiscount;
     //折扣策略开始时间
     public String startTime;
     //折扣策略结束时间
     public String endTime;
     //折扣类型
     public StrategyType strategyType;
     //所需房间数量
     public int numOfRoom;
}
