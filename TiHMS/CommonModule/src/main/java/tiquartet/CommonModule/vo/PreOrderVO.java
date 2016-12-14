package tiquartet.CommonModule.vo;

import java.io.Serializable;

public class PreOrderVO implements Serializable{
	//用户编号
     public int userID=-1;
     //酒店编号
     public int hotelID;
     //房间类型
     public int roomType;
     //房间数量
     public int numOfRoom;
     //房间价格
     public double price;
     //入住时间
     public String startTime;
     //离店时间
     public String leaveTime;
     //用户名
     public String userName;
     //订房者真实姓名
     public String clientRealName;
     //酒店名字
     public String hotelName;
     //客房类型名称
     public String roomTypeName;
}
