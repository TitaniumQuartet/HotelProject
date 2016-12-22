package tiquartet.CommonModule.vo;

import java.io.Serializable;

public class HotelInfoVO implements Serializable{
	    //酒店编号 
	    public int hotelID = -1;
	    //酒店名字
	    public String hotelName = "";
	    //酒店星级
	    public int star = -1;
	    //酒店平均评分
	    public double averageGrade = -1;
	    //酒店地址
	    public String  address = "";
	    //酒店介绍
	    public String  hotelIntroduction = "";
	    //酒店服务设施介绍
	    public String  serviceIntroduction = "";
	    //商圈编号
	    public int circleId = -1;
	    //商圈名字
	    public String circleName = "";
	    //城市名称
	    public String cityName = "";
	    //最低价格
	    public double lowprice = -1;
	    //
	    public double highprice = -1;
}
