package tiquartet.ServerModule.po;

import java.io.Serializable;
import java.util.List;

public class HotelDetailsPO implements Serializable{
	//酒店编号
	private long hotelId;
	//酒店名称
	private String hotelName;
	//星级
	private int star;
	//平均评分
	private int averagegrade;
	//城市名称
	private String cityName;
	//商圈名称
	private String businessDistrict;
	//酒店地址
	private String address;
	//酒店介绍
	private String hotelIntroduction;
	//服务设施介绍
	private String serviceIntroduction;
	//评价列表
	private List<ReviewPO> reviewList;
	
	public HotelDetailsPO(){
		
	}
	
	public HotelDetailsPO(long hotelId,String hotelName,int star,int average,String cityName,String businessDistrict,String address,String introduction,String service,List<ReviewPO> reviewList){
		super();
		this.hotelId=hotelId;
		this.hotelName=hotelName;
		this.star=star;
		this.averagegrade=average;
		this.cityName=cityName;
		this.businessDistrict=businessDistrict;
		this.address=address;
		this.hotelIntroduction=introduction;
		this.serviceIntroduction=service;
		this.reviewList=reviewList;
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
		return averagegrade;
	}
	
	public void setaverage(int average){
		this.averagegrade=average;
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
		return serviceIntroduction;
	}
	
	public void setserviceIntroduction(String serviceIntroduction){
		this.serviceIntroduction=serviceIntroduction;
	}
	
	public List<ReviewPO> getreviewList(){
		return reviewList;
	}
	
	public void setreviewList(List<ReviewPO> reviewList){
		this.reviewList=reviewList;
	}
}
