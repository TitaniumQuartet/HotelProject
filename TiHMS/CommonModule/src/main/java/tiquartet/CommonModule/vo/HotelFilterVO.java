package tiquartet.CommonModule.vo;

import java.io.Serializable;

public class HotelFilterVO implements Serializable {
	// 用户编号
	public int userID = -1;
	// 城市编号
	public int cityID = -1;
	// 商圈编号
	public int districtID = -1;
	// 酒店名称
	public String hotelName = "";
	// 星级下限
	public int lowestStar = -1;
	// 星级上限
	public int highestStar = -1;
	// 评分下限
	public double lowestGrade = -1;
	// 评分上限
	public double highestGrade = -1;
	// 均价下限
	public double lowestPrice = -1;
	// 均价上限
	public double highestPrice = -1;
	// 入住日期
	public String checkInDate = "";
	// 离店日期
	public String checkOutDate = "";
}
