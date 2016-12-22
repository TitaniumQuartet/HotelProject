package tiquartet.CommonModule.vo;

import java.io.Serializable;

public class RoomTypeVO implements Serializable{
        //客房类型编号 	
	    public int roomTypeId = -1;
	    //客房类型名称
	    public String roomType = "";
	    //客房类型介绍
	    public String  typeIntroduction = "";
	    //客房价格
	    public double price = -1;
	    //酒店编号
	    public int hotelID = -1;
	    //可预订房间数量
	    public int number = -1;
}
