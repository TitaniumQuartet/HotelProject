package tiquartet.CommonModule.vo;

import java.io.Serializable;

public class OrderStrategyVO implements Serializable{
	 //订单编号
     public long orderID = -1;
     //策略介绍
     public String strategyIntroduce = "";
     //订单价格
     public double orderPrice = -1;
     //策略编号
     public int strategyID = -1;
}
