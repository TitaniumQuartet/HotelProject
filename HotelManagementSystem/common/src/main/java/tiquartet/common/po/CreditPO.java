package tiquartet.common.po;

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
	
	public CreditPO(){
		
	}
	
	public CreditPO(String changeType,int change,int balance,int orderId){
		super();
		this.changeType=changeType;
		this.change=change;
		this.balance=balance;
		this.orderId=orderId;
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
}
