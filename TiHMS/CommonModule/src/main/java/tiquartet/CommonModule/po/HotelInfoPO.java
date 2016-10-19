package tiquartet.CommonModule.po;

import java.io.Serializable;

public class HotelInfoPO implements Serializable{
	//酒店编号
	private long hotelId;
	//酒店名称
	private String hotelName;
	//星级
	private int star;
	//酒店地址
	private String address;
	//酒店介绍
	private String introduction;
	//服务设施介绍
	private String service;
	
	public HotelInfoPO(){
		
	}
	
	public HotelInfoPO(long hotelId,String hotelName,int star,String address,String introduction,String service){
		super();
		this.hotelId=hotelId;
		this.hotelName=hotelName;
		this.star=star;
		this.address=address;
		this.introduction=introduction;
		this.service=service;
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
	
}