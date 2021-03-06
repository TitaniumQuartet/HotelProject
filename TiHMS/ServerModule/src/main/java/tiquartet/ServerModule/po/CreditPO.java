package tiquartet.ServerModule.po;

import java.io.Serializable;

import tiquartet.CommonModule.util.CreditChange;
import tiquartet.CommonModule.vo.CreditVO;

public class CreditPO implements Serializable {
	// 变更类型
	private CreditChange changeType=CreditChange.订单执行时自动增加信用值;
	// 变更数额
	private double change = -1;
	// 变化后的余额
	private double balance = -1;
	// 订单编号
	private long orderId = -1;
	// 信用记录编号
	private  long creditRecordId = -1;
	// 用户编号
	private int userID = -1;
	//时间
	private String time="";

	public CreditPO() {

	}

	public CreditPO(CreditChange changeType, double change, double balance, long orderId, long creditRecordId,
			int userID,String time) {
		super();
		this.changeType = changeType;
		this.change = change;
		this.balance = balance;
		this.orderId = orderId;
		this.creditRecordId = creditRecordId;
		this.userID = userID;
		this.time=time;
	}

	public CreditPO(CreditVO creditVO) {
		super();
		this.changeType = creditVO.changeType;
		this.change = creditVO.change;
		this.balance = creditVO.balance;
		this.orderId = creditVO.orderID;
		this.creditRecordId = creditVO.creditRecordID;
		this.userID = creditVO.userID;
		this.time= creditVO.time;
	}

	public CreditChange getchangeType() {
		return changeType;
	}

	public void setchangeType(CreditChange changeType) {
		this.changeType = changeType;
	}

	public double getchange() {
		return change;
	}

	public void setchange(double change) {
		this.change = change;
	}

	public double getbalance() {
		return balance;
	}

	public void setbalance(double balance) {
		this.balance = balance;
	}

	public long getorderId() {
		return orderId;
	}

	public void setorderId(long orderId) {
		this.orderId = orderId;
	}

	public void setcreditRecordId(long creditRecordId) {
		this.creditRecordId = creditRecordId;
	}

	public long getcreditRecordId() {
		return this.creditRecordId;
	}

	public void setuserID(int userID) {
		this.userID = userID;
	}

	public int getuserID() {
		return this.userID;
	}
	
	public void settime(String time){
		this.time=time;
	}
	
	public String gettime(){
		return this.time;
	}

	public CreditVO getVO() {
		CreditVO creditVO = new CreditVO();
		creditVO.change = this.change;
		creditVO.changeType = this.changeType;
		creditVO.balance = this.balance;
		creditVO.creditRecordID = this.creditRecordId;
		creditVO.orderID = this.orderId;
		creditVO.userID = this.userID;
		creditVO.time=this.time;
		return creditVO;

	}
}
