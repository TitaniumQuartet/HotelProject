package tiquartet.ServerModule.po;

import java.io.Serializable;

import tiquartet.CommonModule.vo.StrategyVO;

public class StrategyPO implements Serializable{
	//策略编号
	private long strategyId;
	//策略介绍
	private String strategyIntro;
	//酒店编号
	private int  hotelId;	
	//折扣比例
	private double discount;
	
	public StrategyPO(){
		
	}
	
	public StrategyPO(long strategyId,String strategyIntro,int hotelId,double discount){
		super();
		this.strategyId=strategyId;
		this.strategyIntro=strategyIntro;
		this.hotelId=hotelId;
		this.discount=discount;
	}
	public StrategyPO(StrategyVO vo){
		this.discount=vo.discount;
		this.hotelId=vo.hotelID;
		this.strategyId=vo.strategyID;
		this.strategyIntro=vo.strategyIntro;
	}
	public long getstrategyId(){
		return strategyId;
	}
	
	public void setrstategyId(long strategyId){
		this.strategyId=strategyId;
	}
	
	public String getstrategyIntro(){
		return strategyIntro;
	}
	
	public void setstrategyIntro(String strategyIntro){
		this.strategyIntro=strategyIntro;
	}
	
	public int gethotelId(){
		return hotelId;
	}
	
	public void sethotelId(int hotelId){
		this.hotelId=hotelId;
	}
	
	public double getdiscount(){
		return discount;
	}
	
	public void setdiscount(double discount){
		this.discount=discount;
	}
	
	public StrategyVO toStrategyvo(){
		StrategyVO vo=new StrategyVO();
		vo.discount=this.discount;
		vo.hotelID=this.hotelId;
		vo.strategyID=this.strategyId;
		vo.strategyIntro=this.strategyIntro;
		return vo;
	}
}
