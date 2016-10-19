package tiquartet.CommonModule.po;

import java.io.Serializable;

public class RoomPO implements Serializable{
	//客房编号
	private int roomId;
	//实际房号
	private int roomNumber;
	//客房类型编号
	private int roomTypeId;
	//客房状态
	private String state;
	
	public RoomPO(){
		
	}
	
	public RoomPO(int roomId,int roomNumber,int roomTypeId,String state){
		super();
		this.roomId=roomId;
		this.roomNumber=roomNumber;
		this.roomTypeId=roomTypeId;
		this.state=state;
	}
	
	public int getroomId(){
		return roomId;
	}
	
	public void setroomId(int roomId){
		this.roomId=roomId;
	}
	
	public int getroomNumber(){
		return roomNumber;
	}
	
	public void setroomNumber(int roomNumber){
		this.roomNumber=roomNumber;
	}
	
	public int getroomTypeId(){
		return roomTypeId;
	}
	
	public void setroomTypeId(int roomTypeId){
		this.roomTypeId=roomTypeId;
	}
	
	public String getstate(){
		return state;
	}
	
	public void setstate(String state){
		this.state=state;
	}
}
