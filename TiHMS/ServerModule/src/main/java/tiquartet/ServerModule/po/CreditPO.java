package tiquartet.ServerModule.po;

import java.io.Serializable;

public class CreditPO implements Serializable{
	//信用变化类型
	private String changeType;
	//信用变化值
	private int change;
	//信用值余额
	private int balance;
	//相关订单号
	private int orderId;
	//信用记录编号
	private long creditRecordId;
	
	public CreditPO(){
		
	}
	
	public CreditPO(String changeType,int change,int balance,int orderId,long creditRecordId){
		super();
		this.changeType=changeType;
		this.change=change;
		this.balance=balance;
		this.orderId=orderId;
		this.creditRecordId=creditRecordId;
	}
	
	public String getchangeType(){
		return changeType;
	}
	
	public void setchangeType(String changeType){
		this.changeType=changeType;
	}
	
	public int getchange(){
		return change;
	}
	
	public void setchange(int change){
		this.change=change;
	}
	
	public int getbalance(){
		return balance;
	}
	
	public void setbalance(int balance){
		this.balance=balance;
	}
	
	public int getorderTd(){
		return orderId;
	}
	
	public void setorderTd(int orderId){
		this.orderId=orderId;
	}
	
	public void setcreditRecordId(long creditRecordId){
		this.creditRecordId=creditRecordId;
	}
	
	public long getcreditRecordId(){
		return this.creditRecordId;
	}
}
