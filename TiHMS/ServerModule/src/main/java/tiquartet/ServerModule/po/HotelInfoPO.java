package tiquartet.ServerModule.po;

import java.io.Serializable;

public class HotelInfoPO implements Serializable{
	//酒店编号
	private int hotelId;
	//酒店名称
	private String hotelName;
	//星级
	private int star;
	//酒店地址
	private String address;
	//酒店介绍
	private String hotelIntroduction;
	//服务设施介绍
	private String serviceIntroduction;
	//商圈编号
	private int circleId;
	//商圈名字
	private String circleName;
	//最低价格
	private int lowprice;
	//平均评分
	private double avgragegrade;
	//城市名称
	private String cityName;
	
	public HotelInfoPO(){
		
	}
	
	public HotelInfoPO(int hotelId,String hotelName,int star,String address,String introduction,String service){
		super();
		this.hotelId=hotelId;
		this.hotelName=hotelName;
		this.star=star;
		this.address=address;
		this.hotelIntroduction=introduction;
		this.serviceIntroduction=service;
	}
	
	public int gethotelId(){
		return this.hotelId;
	}
	
	public void sethotelId(int hotelId){
		this.hotelId=hotelId;
	}
	
	public String gethotelName(){
		return this.hotelName;
	}
	
	public void sethotelName(String hotelName){
		this.hotelName=hotelName;
	}
	
	public int getstar(){
		return this.star;
	}
	
	public void setstar(int star){
		this.star=star;
	}
	
	public String getaddress(){
		return address;
	}
	
	public void setaddress(String address){
		this.address=address;
	}
	
	public String gethotelIntroduction(){
		return this.hotelIntroduction;
	}
	
	public void sethotelIntroduction(String hotelIntroduction){
		this.hotelIntroduction=hotelIntroduction;
	}
	
	public String getserviceIntroduction(){
		return this.serviceIntroduction;
	}
	
	public void setserviceIntroduction(String serviceIntroduction){
		this.serviceIntroduction=serviceIntroduction;
	}
	
	public void setcircleId(int circleId){
		this.circleId=circleId;
	}
	
	public int getcircleId(){
		return this.circleId;
	}
	
	public void setcircleName(String circleName){
		this.circleName=circleName;
	}
	
	public String getcircleName(){
		return this.circleName;
	}
	
	public void setlowprice(int lowprice){
		this.lowprice=lowprice;
	}
	
	public int getlowprice(){
		return this.lowprice;
	}
	
	public void setavgragegrade(double avgragegrade){
		this.avgragegrade=avgragegrade;
	}
	
	public double getavgreagegrade(){
		return this.avgragegrade;
	}
	
	public void setcityName(String cityName){
		this.cityName=cityName;
	}
	
	public String getcityName(){
		return this.cityName;
	}
}