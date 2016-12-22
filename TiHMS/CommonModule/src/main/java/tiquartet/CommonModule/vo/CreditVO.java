package tiquartet.CommonModule.vo;

import java.io.Serializable;

import tiquartet.CommonModule.util.CreditChange;

public class CreditVO implements Serializable {
	// 订单改变类型
	public CreditChange changeType;
	// 改变数额
	public double change = -1;
	// 剩余值
	public double balance = -1;
	// 订单编号
	public long orderID = -1;
	// 信用记录编号
	public long creditRecordID = -1;
	// 用户编号
	public int userID = -1;
}
