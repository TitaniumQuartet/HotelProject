package tiquartet.common.po.orderfilterpo;

import java.io.Serializable;
import java.util.Calendar;


public class OrderFilterPO implements Serializable{
	//订单编号
	private long orderId;
	//订单状态
	private String state;
	//订单生成时间
	private Calendar orderTime;
	//入住时间
	private Calendar checkInTime;
	//客户姓名
	private String realName;
	//用户名
	private String userName;
	
	public OrderFilterPO(){
		
	}
	
	public OrderFilterPO(long orderId,String state,Calendar orderTime,Calendar checkInTime,String realName,String userName){
		super();
		this.orderId=orderId;
		this.state=state;
		this.orderTime=orderTime;
		this.checkInTime=checkInTime;
		this.realName=realName;
		this.userName=userName;
	}
	
	public long getorderId(){
		return orderId;
	}
	
	public void setorderId(int orderId){
		this.orderId=orderId;
	}
	
	public String state(){
		return state;
	}
	
	public void setstate(String state){
		this.state=state;
	}
	
	public Calendar getorderTime(){
		return orderTime;
	}
	
	public void setorderTime(Calendar orderTime){
		this.orderTime=orderTime;
	}

	public Calendar getcheckInTime(){
		return checkInTime;
	}
	
	public void setcheckInTime(Calendar checkInTime){
		this.checkInTime=checkInTime;
	}
	
	public String getrealName(){
		return realName;
	}
	
	public void setrealName(String realName){
		this.realName=realName;
	}
	
	public String userName(){
		return userName;
	}
	
	public void setuserName(String userName){
		this.userName=userName;
	}
	
}
