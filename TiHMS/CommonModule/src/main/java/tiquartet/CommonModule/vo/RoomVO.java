package tiquartet.CommonModule.vo;

import java.io.Serializable;

import tiquartet.CommonModule.util.RoomStatus;

public class RoomVO implements Serializable{
	    //房间编号
	    public int roomID;
	    //房间数量	    
	    public String roomNum;
	    //房间类型
	    public int roomType;
	    //房间状态
	    public RoomStatus roomStatus;
	    //酒店编号
	    public int hotelId;

}
