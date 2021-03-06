package tiquartet.CommonModule.vo;

import java.io.Serializable;

public class HotelBriefVO implements Serializable {
	// 酒店编号
	public int hotelID = -1;
	// 酒店星级
	public int star = -1;
	// 用户在该酒店全部订单数量
	public int numOfAllOrder = -1;
	// 用户在该酒店已执行订单数量
	public int numOfExecutedOrder = -1;
	// 平均评分
	public double averageGrade = -1;
	// 所在城市名称
	public String cityName = "";
	// 所在商圈名称
	public String circleName = "";
	// 酒店名称
	public String hotelName = "";
	// 酒店介绍
	public String introduction = "";
}
