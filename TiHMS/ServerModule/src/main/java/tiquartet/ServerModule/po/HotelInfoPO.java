package tiquartet.ServerModule.po;

import java.io.Serializable;

import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelInfoVO;

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
	private double lowprice;
	//最高价格
	private double highprice;
	//平均评分
	private double averageGrade;
	//城市名称
	private String cityName;
	
	public HotelInfoPO(){
		
	}
	public HotelInfoPO(HotelInfoVO hotelInfovo){
		this.address=hotelInfovo.address;
		this.highprice=hotelInfovo.highprice;
		this.circleId=hotelInfovo.circleId;
		this.circleName=hotelInfovo.circleName;
		this.circleName=hotelInfovo.cityName;
		this.averageGrade=hotelInfovo.averageGrade;
		this.hotelId=hotelInfovo.hotelID;
		this.hotelIntroduction=hotelInfovo.hotelIntroduction;
		this.hotelName=hotelInfovo.hotelName;
		this.lowprice=hotelInfovo.lowprice;
		this.serviceIntroduction=hotelInfovo.serviceIntroduction;
		this.star=hotelInfovo.star;
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
	public HotelInfoVO toHotelInfoVO(){
		HotelInfoVO vo=new HotelInfoVO();
		vo.address=this.address;
		vo.averageGrade=this.averageGrade;
		vo.circleId=this.circleId;
		vo.circleName=this.circleName;
		vo.cityName=this.cityName;
		vo.hotelID=this.hotelId;
		vo.hotelIntroduction=this.hotelIntroduction;
		vo.hotelName=this.hotelName;
		vo.lowprice=this.lowprice;
		vo.serviceIntroduction=this.serviceIntroduction;
		vo.star=this.star;
		vo.highprice=this.highprice;
		return vo;
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