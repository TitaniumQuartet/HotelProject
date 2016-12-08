package tiquartet.CommonModule.vo;

import java.io.Serializable;

public class OrderNumVO implements Serializable{
    //酒店编号
	public int hotelID;
	//用户编号
	public int uesrID;
	//总订单数目
	public int allOrder;
	//未执行订单数目
	public int unexecutedOrder;
	//已执行订单数目
	public int executedOrder;
	//取消的订单数目
	public int canceledOrder;
	//异常订单数目
	public int abnormalOrder;
}
