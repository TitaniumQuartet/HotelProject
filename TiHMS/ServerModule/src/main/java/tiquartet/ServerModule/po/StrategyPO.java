package tiquartet.ServerModule.po;

import java.io.Serializable;

import tiquartet.CommonModule.util.StrategyType;
import tiquartet.CommonModule.vo.StrategyVO;

public class StrategyPO implements Serializable{
	//策略编号
	private int strategyId=-1;
	//策略介绍
	private String strategyIntro="";
	//酒店编号
	private int  hotelId=-1;	
	//商圈编号
	private int circleID=-1;
	//折扣比例
	private double discount=-1;
	//门槛上线
	private double[] memberThreShold={0,0,0,0,0,0,0,0,0};
	//会员等级对应折扣
	private double[] memberDiscount={0,0,0,0,0,0,0,0,0,0};
	//折扣策略开始时间
	private String startTime="";
	//折扣策略结束时间
	private String endTime="";
	//策略类型
	private StrategyType strategyType=StrategyType.TIME;
	//所需房间数目
	private int numOfRoom=-1;
	public StrategyPO(){
		
	}
	
	public StrategyPO(int strategyId,String strategyIntro,int hotelId,double discount,int circleID,double[] memberThreShold,double[] memberDiscount,String startTime,String endTime,StrategyType strategyType,int numOfRoom){
		super();
		this.strategyId=strategyId;
		this.strategyIntro=strategyIntro;
		this.hotelId=hotelId;
		this.discount=discount;
		this.circleID=circleID;
		this.memberThreShold=memberThreShold;
		this.memberDiscount=memberDiscount;
		this.startTime=startTime;
		this.endTime=endTime;
		this.strategyType=strategyType;
		this.numOfRoom=numOfRoom;
	}
	
	public StrategyPO(StrategyVO vo){
		this.discount=vo.discount;
		this.hotelId=vo.hotelID;
		this.strategyId=vo.strategyID;
		this.strategyIntro=vo.strategyIntro;
		this.memberDiscount=vo.memberDiscount;
		this.memberThreShold=vo.memberThreShold;
		this.startTime=vo.startTime;
		this.endTime=vo.endTime;
		this.strategyType=vo.strategyType;
		this.circleID=vo.circleID;
		this.numOfRoom=vo.numOfRoom;
	}
	public int getstrategyId(){
		return strategyId;
	}
	
	public void setrstategyId(int strategyId){
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
		vo.memberDiscount=this.memberDiscount;
		vo.memberThreShold=this.memberThreShold;
		vo.endTime=this.endTime;
		vo.startTime=this.startTime;
		vo.strategyType=this.strategyType;
		vo.circleID=this.circleID;
		vo.numOfRoom=this.numOfRoom;
		return vo;
	}

	
	public void setMemberDiscount(double[] memberDiscount){
		this.memberDiscount=memberDiscount;
	}
	
	public double[] getMemberDiscount(){
		return this.memberDiscount;
	}
	
	public String getMemberDiscountAsString(){
		String result="";
		if(memberDiscount[0]==0)
			return "";
		for(int i=0;i<memberDiscount.length;i++){
			result=result+String.valueOf(memberDiscount[i])+",";
		}
		result=result+String.valueOf(memberDiscount[9]);
		return result;
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
	
	public double[] getMemberThreshold(){
		return this.memberThreShold;
	}
	
	public String getMemberThresholdAsString(){
		String result="";
		if(memberThreShold[0]==0)
			return "";
		for(int i=0;i<memberThreShold.length-1;i++){
			result=result+String.valueOf(memberThreShold[i])+",";
		}
		result=result+String.valueOf(memberThreShold[8]);
		return result;
	}
    
	public void setCircleID(int circleID){
		this.circleID=circleID;
	}
	
	public int getCircelID(){
		return this.circleID;
	}
	
	public void setnumOfRoom(int numOfRoom){
		this.numOfRoom=numOfRoom;		
	}
	
	public int getnumOfRoom(){
		return this.numOfRoom;
	}
}
