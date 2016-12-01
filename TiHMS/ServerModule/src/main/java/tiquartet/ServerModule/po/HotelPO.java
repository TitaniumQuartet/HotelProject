package tiquartet.ServerModule.po;

import java.io.Serializable;

import tiquartet.CommonModule.vo.HotelBriefVO;

public class HotelPO implements Serializable{
	//酒店编号
	private long hotelId;
	//酒店名称
	private String hotelName;
	//酒店星级
	private int star;
	//平均分
	private double average;
	//城市名
	private String cityName;
	//商圈名
	private String businessDistrict;
	//最低价格
	private int lowestPrice;
	//最高价格
	private int highestPrice;
	
	private int numOfAllOrder;
	
	private int numOfEndOrder;
	
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
	
	public int gethighestPrice(){
		return highestPrice;
	}
	
	public void sethighestPrice(int highestPrice){
		this.highestPrice = highestPrice;
	}
	
	public int getnumOfAllOrder(){
		return numOfAllOrder;
	}
	
	public void setnumOfAllOrder(int numOfAllOrder){
		this.numOfAllOrder = numOfAllOrder;
	}
	
	public int getnumOfEndOrder(){
		return numOfEndOrder;
	}
	
	public void setnumOfEndOrder(int numOfEndOrder){
		this.numOfEndOrder = numOfEndOrder;
	}
	
	public HotelBriefVO getBriefVO(){
		HotelBriefVO hotelBriefVO = new HotelBriefVO();
		hotelBriefVO.averageGrade = this.average;
		hotelBriefVO.circleName = this.businessDistrict;
		hotelBriefVO.cityName = this.cityName;
		hotelBriefVO.hotelID = this.hotelId;
		hotelBriefVO.hotelName = this.hotelName;
		hotelBriefVO.star = this.star;
		hotelBriefVO.numOfAllOrder = this.numOfAllOrder;
		hotelBriefVO.numOfEndOrder = this.numOfEndOrder;
		
		return hotelBriefVO;
	}
}
