package tiquartet.ServerModule.po;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.vo.OrderVO;


public class OrderPO implements Serializable{
	//订单编号
	private long orderId;
	//订单状态
	private OrderStatus orderStatus;
	//最晚订单执行时间
	private String latestTime;
	//房间数量
	private int numberOfRoom;
	//订单中的房间编号与实际房号的映射
	private HashMap<Integer, String> roomMap;
	//入住人数
	private int numberOfPeople;
	//有无儿童
	private int child;
	//入住人真实姓名
	private String guestRealName;
	//订房者真实姓名
	private String clientRealName;
	//酒店编号
	private int hotelId;
	//酒店名称
	public String hotelName;
	//用户编号
	private int userId;
	//用户名
	private String userName;
	//入住日期
	private String startTime;
	//离店日期
	private String leaveTime;
	//订单生成日期
	private String orderTime;
	//订单价格
	private double price;
	
	public OrderPO(OrderVO vo){
		this.child=vo.child;
		this.hotelId=vo.hotelId;
		this.latestTime=vo.latestTime;
		this.leaveTime=vo.leaveTime;
		this.numberOfPeople=vo.numberOfPeople;
		this.numberOfRoom=vo.numberOfRoom;
		this.roomMap=vo.roomMap;
		this.orderId=vo.orderId;
		this.orderStatus=vo.orderStatus;
		this.price=vo.price;
		this.clientRealName=vo.clientrealName;
		this.guestRealName=vo.guestrealName;
		this.startTime=vo.startTime;
		this.orderTime=vo.orderTime;
		this.userId=vo.userId;
		this.userName=vo.userName;
	}
	
	public OrderPO(){
		
	}
	
	public OrderPO(long orderId,OrderStatus orderStatus,String latestTime,HashMap<Integer, String> roomMap,int numberOfRoom,int numberOfPeople,int child,String guestRealName,String clientRealName,String hotelName,int userId,String userName,String startTime,String leaveTime,String orderTime,double price,int hotelId){
		super();
		this.orderId=orderId;
		this.orderStatus=orderStatus;
		this.latestTime=latestTime;
		this.numberOfRoom=numberOfRoom;
		this.numberOfPeople=numberOfPeople;
		this.child=child;
		this.roomMap=roomMap;
		this.clientRealName=clientRealName;
		this.guestRealName=guestRealName;
		this.hotelName=hotelName;
		this.startTime=startTime;
		this.leaveTime=leaveTime;
		this.orderTime=orderTime;
		this.price=price;

		this.userId=userId;
		this.userName=userName;
		this.hotelId=hotelId;
	}
	
	public long getorderId(){
		return orderId;
	}
	
	public void setorderId(int orderId){
		this.orderId=orderId;
	}
	
	public String getlatestTime(){
		return latestTime;
	}
	
	public void setlatestTime(String latestTime){
		this.latestTime=latestTime;
	}
	
	public int getnumberOfRoom(){
		return numberOfRoom;
	}
	
	public void setnumberOfRoom(int numberOfRoom){
		this.numberOfRoom=numberOfRoom;
	}
	
	public HashMap<Integer, String> getRoomMap() {
		return roomMap;
	}
	
	public void setRoomMap(HashMap<Integer, String> roomMap) {
		this.roomMap = roomMap;
	}
	
	/**
	 * 得到房间号和房间编号组成的字符串.
	 * @return
	 */
	public String getroom(){
		String roomn = "";
		String roomi = "";
		java.util.Iterator<Entry<Integer, String>> i = roomMap.entrySet().iterator();  
		while(i.hasNext()){ 
			Map.Entry entry = (Map.Entry) i.next();  
		    Object key = entry.getKey();  
		    Object val = entry.getValue();
		    roomn=roomn + key.toString();
		    roomi = roomi + val.toString();
		}  
		String result=roomn + ";" + roomi;
		return result;
	}
	
	public int getnumberOfPeople(){
		return numberOfPeople;
	}
	
	public void setnumberOfPeople(int numberOfPeople){
		this.numberOfPeople=numberOfPeople;
	}
	
	public int getchild(){
		return child;
	}
	
	public void setchild(int child){
		this.child=child;
	}
	
	public String getguestRealName(){
		return guestRealName;
	}
	
	public void setguestRealName(String guestRealName){
		this.guestRealName=guestRealName;
	}
	
	public void sethotelId(int hotelId){
		this.hotelId=hotelId;
	}
	
	public int gethotelId(){
		return this.hotelId;
	}
	
	public void setuserId(int userId){
		this.userId=userId;
	}
	
	public int getuserId(){
		return userId;
	}
	
	public void setuserName(String userName){
		this.userName=userName;
	}
	
	public String getuserName(){
		return this.userName;
	}
	
	public void setstartTime(String startTime){
		this.startTime=startTime;
	}
	
	public String getstartTime(){
		return this.startTime;
	}
	
	public void setleaveTime(String leaveTime){
		this.leaveTime=leaveTime;
	}

	public String getleaveTime(){
		return this.leaveTime;
	}
	
	public void setorderTime(String orderTime){
		this.orderTime=orderTime;
	}

	public String getorderTime(){
		return this.orderTime;
	}
	
	public void setprice(double price){
		this.price=price;
	}
	
	public double getprice(){
		return this.price;
	}
	
	public void setorderStatus(OrderStatus orderType){
		this.orderStatus=orderType;
	}
	
	public OrderStatus getorderStatus(){
		return this.orderStatus;
	}
	
	/**
	 * 得到枚举类型的序号以便在数据库中存储.
	 * @return
	 */
	public int getorderStatusAsInt(){
		return this.orderStatus.ordinal();
	}
	
	public void sethotellName(String hotelName){
		this.hotelName=hotelName;
	}
	
	public String gethotelName(){
		return this.hotelName;
	}
	
	public void setclientRealName(String clientRealName){
		this.clientRealName=clientRealName;
	}
	
	public String getclientRealName(){
		return this.clientRealName;
	}
	
	public OrderVO toOrderVO(){
		OrderVO vo=new OrderVO();
		vo.child=this.child;
		vo.hotelId=this.hotelId;
		vo.latestTime=this.latestTime;
		vo.leaveTime=this.leaveTime;
		vo.numberOfPeople=this.numberOfPeople;
		vo.numberOfRoom=this.numberOfRoom;
		vo.orderId=this.orderId;
		vo.clientrealName=this.clientRealName;
		vo.guestrealName=this.guestRealName;
		vo.startTime=this.startTime;
		vo.orderTime=this.orderTime;
		vo.userId=this.userId;
		vo.userName=this.userName;
		vo.orderStatus=this.orderStatus;
		return vo;
	}
	
}
