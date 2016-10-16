package tiquartet.common.po.strategypo;

import java.io.Serializable;

public class StrategyPO implements Serializable{
	//策略编号
	private long strategyId;
	//策略介绍
	private String strategyIntro;
	//酒店编号
	private long hotelId;	
	//折扣比例
	private double discount;
	
	public StrategyPO(){
		
	}
	
	public StrategyPO(long strategyId,String strategyIntro,long hotelId,double discount){
		super();
		this.strategyId=strategyId;
		this.strategyIntro=strategyIntro;
		this.hotelId=hotelId;
		this.discount=discount;
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
	
	public long gethotelId(){
		return hotelId;
	}
	
	public void sethotelId(long hotelId){
		this.hotelId=hotelId;
	}
	
	public double getdiscount(){
		return discount;
	}
	
	public void setdiscount(long discount){
		this.discount=discount;
	}
}
