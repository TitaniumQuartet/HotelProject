package tiquartet.CommonModule.vo;

import java.io.Serializable;
import java.util.List;

public class HotelDetailsVO implements Serializable{
	    public long hotelID;
	    public String hotelName;
	    public int star;
	    public double averageg;
	    public String address;
	    public String  cityName;
	    public String  circleName;
	    //用户在此酒店的订单列表
	    public List<OrderVO> orderList;
	    //酒店的评论列表
	    public List<ReviewVO> reviewList;
	    public String introduction;
	    public double lowprice;
	    public String serviceintro;
}
