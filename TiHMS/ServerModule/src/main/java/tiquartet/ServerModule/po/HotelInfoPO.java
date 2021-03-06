package tiquartet.ServerModule.po;

import java.io.Serializable;

import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelDetailsVO;

public class HotelInfoPO implements Serializable{
	//酒店编号
	private int hotelId=-1;
	//酒店名称
	private String hotelName="";
	//星级
	private int star=-1;
	//酒店地址
	private String address="";
	//酒店介绍
	private String hotelIntroduction="";
	//服务设施介绍
	private String serviceIntroduction="";
	//商圈编号
	private int circleId=-1;
	//商圈名字
	private String circleName="";
	//最低价格
	private double lowprice=-1;
	//最高价格
	private double highprice=-1;
	//平均评分
	private double averageGrade=-1;
	//城市名称
	private String cityName="";
	
	public HotelInfoPO(){
		
	}
	
	public HotelInfoPO(HotelDetailsVO hotelInfo){
		this.hotelId=hotelInfo.hotelID;
		this.address=hotelInfo.address;
		this.averageGrade=hotelInfo.averagegrade;
		this.circleName=hotelInfo.circleName;
		this.cityName=hotelInfo.cityName;
		this.hotelId=hotelInfo.hotelID;
		this.hotelIntroduction=hotelInfo.introduction;
		this.serviceIntroduction=hotelInfo.serviceintro;
		this.star=hotelInfo.star;
		this.lowprice=hotelInfo.lowprice;
		this.highprice=hotelInfo.highprice;
		this.circleId=hotelInfo.circleID;
		this.hotelName=hotelInfo.hotelName;
	}
	
	public HotelInfoPO(int hotelId,String hotelName,int star,String address,String introduction,String service,int circleId,String circleName,double lowprice,double highprice,double averageGrade,String cityName){
		super();
		this.hotelId=hotelId;
		this.hotelName=hotelName;
		this.star=star;
		this.address=address;
		this.hotelIntroduction=introduction;
		this.serviceIntroduction=service;
		this.circleId=circleId;
		this.circleName=circleName;
		this.lowprice=lowprice;
		this.highprice=highprice;
		this.averageGrade=averageGrade;
		this.cityName=cityName;
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
	
	public void setlowprice(double lowprice){
		this.lowprice=lowprice;
	}
	
	public double getlowprice(){
		return this.lowprice;
	}
	
	
	public void sethighprice(double highprice){
		this.highprice=highprice;
	}
	
	public double gethighprice(){
		return this.highprice;
	}
	public void setaverageGrade(double avgrageGrade){
		this.averageGrade=avgrageGrade;
	}
	
	public double getaverageGrade(){
		return this.averageGrade;
	}
	
	public void setcityName(String cityName){
		this.cityName=cityName;
	}
	
	public String getcityName(){
		return this.cityName;
	}
	
	public HotelBriefVO getBriefVO(){
		HotelBriefVO hotelBriefVO = new HotelBriefVO();
		hotelBriefVO.averageGrade = this.averageGrade;
		hotelBriefVO.circleName = this.circleName;
		hotelBriefVO.cityName = this.cityName;
		hotelBriefVO.hotelID = this.hotelId;
		hotelBriefVO.hotelName = this.hotelName;
		hotelBriefVO.star = this.star;
		hotelBriefVO.introduction = this.hotelIntroduction;
		return hotelBriefVO;
	}
	
}