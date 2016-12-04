package tiquartet.ServerModule.po;

import java.io.Serializable;

import tiquartet.CommonModule.util.StrategyType;
import tiquartet.CommonModule.vo.StrategyVO;

public class StrategyPO implements Serializable{
	//策略编号
	private long strategyId;
	//策略介绍
	private String strategyIntro;
	//酒店编号
	private int  hotelId=-1;	
	//折扣比例
	private double discount;
	//升级门槛
	private double[] memberThreShold=new double[10];
	//会员等级对应折扣
	private double[] memberDiscount=new double[10];
	//折扣策略开始时间
	private String startTime;
	//折扣策略结束时间
	private String endTime;
	//策略类型
	private StrategyType strategyType;
	
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

	
	public void setMemberDiscount(double[] memberDiscount){
		this.memberDiscount=memberDiscount;
	}
	
	public double[] getMemberDiscount(){
		return this.memberDiscount;
	}
	
	public void setStartTime(String startTime){
		this.startTime=startTime;
	}
	
	public String getStartTime(){
		return this.startTime;
	}
	
	public void setEndTIme(String endTime){
		this.endTime=endTime;
	}
	
	public String getEndTime(){
		return this.endTime;
	}
	
	public void setStrategyType(StrategyType strategyType){
		this.strategyType=strategyType;
	}
	
	public StrategyType getStrategyType(){
		return this.strategyType;
	}
	
	public void setMemberThreshold(double[] memberthreshold){
		this.memberThreShold=memberthreshold;
	}
	
	public double[] getMemberThreshole(){
		return this.memberThreShold;
	}
}
