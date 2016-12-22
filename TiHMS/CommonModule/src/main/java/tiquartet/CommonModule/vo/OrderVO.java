package tiquartet.CommonModule.vo;

import java.io.Serializable;
import java.util.HashMap;

import tiquartet.CommonModule.util.OrderStatus;

public class OrderVO implements Serializable{
	    //订单编号
		public long orderId = -1;
		//最晚订单执行时间
		public String latestTime = "";
		//房间数量
		public int numberOfRoom = -1;
		//订单中的房间编号与实际房号的映射
		public HashMap<Integer, String> roomMap;
		//入住人数
		public int numberOfPeople = -1;
		//有无儿童
		public int child = -1;
		//入住人真实姓名
		public String guestrealName = "";
		//预订者真实姓名
		public String clientrealName = "";
		//酒店编号
		public int hotelId = -1;
		//用户编号
		public int userId = -1;
		//用户名
		public String userName = "";
		//入住日期
		public String startTime = "";
		//离店日期
		public String leaveTime = "";
		//订单生成日期
		public String orderTime = "";
		//订单价格
		public double price = -1;
		//订单状态
		public OrderStatus orderStatus;
		//酒店名称
		public String hotelName = "";
		//房间类型名称
		public String roomTypeName = "";
}
