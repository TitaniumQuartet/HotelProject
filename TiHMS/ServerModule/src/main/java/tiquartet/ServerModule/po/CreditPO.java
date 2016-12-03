package tiquartet.ServerModule.po;

import java.io.Serializable;

import tiquartet.CommonModule.vo.CreditVO;

public class CreditPO implements Serializable{
	//变更类型
	private int changeType=-1;
	//变更数额
	private double change=-1;
	//
	private double balance;
	//订单编号
	private long orderId;
	//信用记录编号
	private long creditRecordId;
	
	public CreditPO(){
		
	}
	
	public CreditPO(int changeType,double change,double balance,long orderId,long creditRecordId){
		super();
		this.changeType=changeType;
		this.change=change;
		this.balance=balance;
		this.orderId=orderId;
		this.creditRecordId=creditRecordId;
	}
	
	public CreditPO(CreditVO creditVO){
		super();
		this.changeType = creditVO.changeType;
		this.change = creditVO.change;
		this.balance = creditVO.balance;
		this.orderId = creditVO.orderID;
		this.creditRecordId = creditVO.creditRecordID;
	}
	
	public int getchangeType(){
		return changeType;
	}
	
	public void setchangeType(int changeType){
		this.changeType=changeType;
	}
	
	public double getchange(){
		return change;
	}
	
	public void setchange(int change){
		this.change=change;
	}
	
	public double getbalance(){
		return balance;
	}
	
	public void setbalance(int balance){
		this.balance=balance;
	}
	
	public long getorderId(){
		return orderId;
	}
	
	public void setorderId(int orderId){
		this.orderId=orderId;
	}
	
	public void setcreditRecordId(long creditRecordId){
		this.creditRecordId=creditRecordId;
	}
	
	public long getcreditRecordId(){
		return this.creditRecordId;
	}
	
	public CreditVO getVO(){
		CreditVO creditVO = new CreditVO();
		creditVO.change = this.change;
		creditVO.changeType = this.changeType;
		creditVO.balance = this.balance;
		creditVO.creditRecordID = this.creditRecordId;
		creditVO.orderID = this.orderId;
		
		return creditVO;
		
	}
}
