package tiquartet.common.po;

import java.io.Serializable;

public class HotelPO implements Serializable{
	//酒店编号
	private long hotelId;
	//酒店名称
	private String hotelName;
	//星级
	private int star;
	//平均评分
	private int average;
	//城市名称
	private String cityName;
	//商圈名称
	private String businessDistrict;
	//最低价格
	private int lowestPrice;
	
	public HotelPO(){
		
	}
	
	public HotelPO(long hotelId,String hotelName,int star,int average,String cityName,String businessDistrict,int lowestPrice){
		super();
		this.hotelId=hotelId;
		this.hotelName=hotelName;
		this.star=star;
		this.average=average;
		this.cityName=cityName;
		this.businessDistrict=businessDistrict;
		this.lowestPrice=lowestPrice;
	}
	
	public long gethotelId(){
		return hotelId;
	}
	
	public void sethotelId(long hotelId){
		this.hotelId=hotelId;
	}
	
	public String gethotelName(){
		return hotelName;
	}
	
	public void sethotelName(String hotelName){
		this.hotelName=hotelName;
	}
	
	public int getstar(){
		return star;
	}
	
	public void setstar(int star){
		this.star=star;
	}
	
	public int getaverage(){
		return average;
	}
	
	public void setaverage(int average){
		this.average=average;
	}
	
	public String getcityName(){
		return cityName;
	}
	
	public void setcityName(String cityName){
		this.cityName=cityName;
	}
	
	public String getbusinessDistrict(){
		return businessDistrict;
	}
	
	public void setbusinessDistrict(String businessDistrict){
		this.businessDistrict=businessDistrict;
	}
	
	public int getlowestPrice(){
		return lowestPrice;
	}
	
	public void setlowestPrice(int lowestPrice){
		this.lowestPrice=lowestPrice;
	}
}
