package tiquartet.CommonModule.vo;

import java.io.Serializable;

import tiquartet.CommonModule.util.CreditChange;

public class CreditVO implements Serializable{
	//订单改变类型
    public CreditChange changeType;
    //改变数额
    public double change;
    //剩余值
    public double balance;
    //订单编号
    public long orderID;
    //信用记录编号
    public long creditRecordID;
    //用户编号
    public int userID;
}
