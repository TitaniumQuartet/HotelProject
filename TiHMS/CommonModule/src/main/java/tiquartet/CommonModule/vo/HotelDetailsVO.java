package tiquartet.CommonModule.vo;

import java.io.Serializable;
import java.util.List;

public class HotelDetailsVO implements Serializable {
	// 酒店编号
	public int hotelID = -1;
	// 酒店名字
	public String hotelName = "";
	//商圈编号
	public int circleID = -1;
	// 酒店星级
	public int star = -1;
	// 酒店评价评分
	public double averagegrade = -1;
	// 地址
	public String address = "";
	// 城市名
	public String cityName = "";
	// 商圈名
	public String circleName = "";
	// 用户在此酒店的订单列表
	public List<OrderVO> orderList;
	// 酒店的评论列表
	public List<ReviewVO> reviewList;
	// 酒店介绍
	public String introduction = "";
	// 最低价格
	public double lowprice = -1;
	//最高价格
	public double highprice = -1;
	// 酒店服务设施介绍
	public String serviceintro = "";
}
