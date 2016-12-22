package tiquartet.CommonModule.vo;

import java.io.Serializable;

public class OrderNumVO implements Serializable{
    //酒店编号
	public int hotelID = -1;
	//用户编号
	public int uesrID = -1;
	//总订单数目
	public int allOrder = -1;
	//未执行订单数目
	public int unexecutedOrder = -1;
	//已执行订单数目
	public int executedOrder = -1;
	//取消的订单数目
	public int canceledOrder = -1;
	//异常订单数目
	public int abnormalOrder = -1;
}
