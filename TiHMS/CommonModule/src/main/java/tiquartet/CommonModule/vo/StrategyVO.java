package tiquartet.CommonModule.vo;

import java.util.Map;

import tiquartet.CommonModule.util.StrategyType;

public class StrategyVO {
	 //酒店编号
	 public int hotelID;
	 //策略介绍
     public String strategyIntro;
     //折扣
     public double discount;
     //策略编号
     public int strategyID;
     //商圈特殊相应会员等级相应折扣
     public Map<Integer,Double> circelDiscount;
     //会员等级相应折扣
     public Map<Integer,Double> memberDiscount;
     //折扣策略开始时间
     public String startTime;
     //折扣策略结束时间
     public String endTime;
     //折扣类型
     public StrategyType strategyType;
}
