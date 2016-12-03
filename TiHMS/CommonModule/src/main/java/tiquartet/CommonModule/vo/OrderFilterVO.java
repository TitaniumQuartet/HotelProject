package tiquartet.CommonModule.vo;

import tiquartet.CommonModule.util.OrderStatus;

public class OrderFilterVO {
	    //初始值为-1;
	    //订单状态
	    public OrderStatus orderState=null;
	    //商圈编号	    
	    public int districtId=-1;
	    //酒店星级
	    public int star=-1;
	    //酒店编号
	    public int userId=-1;
	    //酒店名字
	    public String hotelName=null;
	    //酒店编号
	    public int hotelID=-1;
	    //最低价格
	    public double lowprice=-1;
	    //最高价格
	    public double highprice=-1;
	    //结束时间
	    public String endTime=null;
	    //入住时间
	    public String startTime=null;
	    //入住者姓名
	    public String  guestRealName=null;
	    //用户名
	    public String  userName=null;
	    //订房者姓名
	    public String clientRealName=null;
}
