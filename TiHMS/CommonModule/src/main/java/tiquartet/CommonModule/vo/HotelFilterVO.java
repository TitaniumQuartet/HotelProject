package tiquartet.CommonModule.vo;

import java.io.Serializable;
import java.sql.Date;

public class HotelFilterVO implements Serializable{	
	//用户编号
	public int userID;    
	//城市编号
	public int cityID;
	//商圈编号
	public int districtID;
	//酒店名称
	public String hotelName;
	//星级下限
	public int lowestStar;
	//星级上限
	public int highestStar;
	//评分下限
	public double lowestGrade;
	//评分上限
	public double highestGrade;
	//均价下限
	public double lowestPrice;
	//均价上限
	public double highestPrice;
	//入住日期
	public Date checkInDate;
	//离店日期
	public Date checkOutDate;
}
