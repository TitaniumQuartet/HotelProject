package tiquartet.common.po.roomtypepo;

import java.io.Serializable;

public class RoomTypePO implements Serializable{
	//客房类型编号
	private int roomTypeId;
	//客房类型名称
	private String roomType;
	//客房类型介绍
	private String typeIntroduction;	
	//价格
	private int price;
	
	public RoomTypePO(){
		
	}
	
	public RoomTypePO(int roomTypeId,String roomType,String typeIntroduction,int price){
		super();
		this.roomTypeId=roomTypeId;
		this.roomType=roomType;
		this.typeIntroduction=typeIntroduction;
		this.price=price;
	}
	
	public int getroomTypeId(){
		return roomTypeId;
	}
	
	public void setroomTypeId(int roomTypeId){
		this.roomTypeId=roomTypeId;
	}
	
	public String getroomType(){
		return roomType;
	}
	
	public void setroomType(String roomType){
		this.roomType=roomType;
	}
	
	public String gettypeIntroduction(){
		return typeIntroduction;
	}
	
	public void settypeIntroduction(String typeIntroduction){
		this.typeIntroduction=typeIntroduction;
	}
	
	public int getprice(){
		return price;
	}
	
	public void setprice(int price){
		this.price=price;
	}
	
}
