package tiquartet.ServerModule.po;

import java.io.Serializable;

import tiquartet.CommonModule.util.RoomStatus;
import tiquartet.CommonModule.vo.RoomVO;

public class RoomPO implements Serializable{
	//房间编号
	private int roomId;
	//房间号
	private String roomNumber;
	//房间类型编号
	private int roomTypeId;
	//房间状态
	private RoomStatus state;
	//酒店编号
	private int hotelId;
	public RoomPO(){
		
	}
	
	public RoomPO(int roomId,String roomNumber,int roomTypeId,RoomStatus state,int hotelId){
		super();
		this.roomId=roomId;
		this.roomNumber=roomNumber;
		this.roomTypeId=roomTypeId;
		this.state=state;
		this.hotelId=hotelId;
	}
	
	public RoomPO(RoomVO roomVO){
		super();
		this.roomId = roomVO.roomID;
		this.roomNumber = roomVO.roomNum;
		this.roomTypeId = roomVO.roomType;
		this.state = roomVO.roomStatus;
	}
	
	public int getroomId(){
		return roomId;
	}
	
	public void setroomId(int roomId){
		this.roomId=roomId;
	}
	
	public String getroomNumber(){
		return roomNumber;
	}
	
	public void setroomNumber(String roomNumber){
		this.roomNumber=roomNumber;
	}
	
	public int getroomTypeId(){
		return roomTypeId;
	}
	
	public void setroomTypeId(int roomTypeId){
		this.roomTypeId=roomTypeId;
	}
	
	public RoomStatus getstate(){
		return state;
	}
	
	public int getstateAsInt(){
		return state.ordinal();
	}
	
	public void setstate(RoomStatus state){
		this.state=state;
	}
	
	public int gethotelId(){
		return hotelId;
	}
	
	public void sethotelId(int hotelId){
		this.hotelId=hotelId;
	}
	
	public RoomVO getRoomVO(){
		RoomVO roomVO = new RoomVO();
		roomVO.roomID = this.roomId;
		roomVO.roomNum = this.roomNumber;
		roomVO.roomStatus = this.state;
		roomVO.roomType = this.roomTypeId;
		
		return roomVO;
		
	}
}
