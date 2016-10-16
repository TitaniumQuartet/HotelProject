package tiquartet.common.po.hoteldetailspo;

import java.io.Serializable;
import java.util.List;
import tiquartet.common.po.reviewpo.ReviewPO;

public class HotelDetailsPO implements Serializable{
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
	//酒店地址
	private String address;
	//酒店介绍
	private String introduction;
	//服务设施介绍
	private String service;
	//评价列表
	private List<ReviewPO> reviewList;
	
	public HotelDetailsPO(){
		
	}
	
	public HotelDetailsPO(long hotelId,String hotelName,int star,int average,String cityName,String businessDistrict,String address,String introduction,String service,List<ReviewPO> reviewList){
		super();
		this.hotelId=hotelId;
		this.hotelName=hotelName;
		this.star=star;
		this.average=average;
		this.cityName=cityName;
		this.businessDistrict=businessDistrict;
		this.address=address;
		this.introduction=introduction;
		this.service=service;
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
	
	public String getaddress(){
		return address;
	}
	
	public void setaddress(String address){
		this.address=address;
	}
	
	public String getintroduction(){
		return introduction;
	}
	
	public void setintroduction(String introduction){
		this.introduction=introduction;
	}
	
	public String getservice(){
		return service;
	}
	
	public void setservice(String service){
		this.service=service;
	}
	
	public List<ReviewPO> getreviewList(){
		return reviewList;
	}
	
	public void setreviewList(List<ReviewPO> reviewList){
		this.reviewList=reviewList;
	}
}
