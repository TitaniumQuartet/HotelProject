package tiquartet.ServerModule.po;

import java.io.Serializable;

import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.vo.RoomVO;

public class RoomTypePO implements Serializable{
	//房间类型编号
	private int roomTypeId;
	//房间类型
	private String roomType;
	//类型介绍
	private String typeIntroduction;	
	//房间价格
	private double price;
	
	public RoomTypePO(){
		
	}
	
	public RoomTypePO(int roomTypeId,String roomType,String typeIntroduction,int price){
		super();
		this.roomTypeId=roomTypeId;
		this.roomType=roomType;
		this.typeIntroduction=typeIntroduction;
		this.price=price;
	}
	
	public RoomTypePO(RoomTypeVO roomTypeVO){
		super();
		this.roomTypeId = roomTypeVO.roomTypeId;
		this.roomType = roomTypeVO.roomType;
		this.typeIntroduction = roomTypeVO.typeIntroduction;
		this.price = roomTypeVO.price;
		
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
	
	public double getprice(){
		return price;
	}
	
	public void setprice(double price){
		this.price=price;
	}
	public RoomTypeVO toRoomTypevo(){
		RoomTypeVO vo=new RoomTypeVO();
		vo.price=this.price;
		vo.roomType=this.roomType;
		vo.roomTypeId=this.roomTypeId;
		vo.typeIntroduction=this.typeIntroduction;
		return vo;
	}
}
