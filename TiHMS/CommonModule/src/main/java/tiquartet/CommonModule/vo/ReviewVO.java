package tiquartet.CommonModule.vo;

import java.io.Serializable;

public class ReviewVO implements Serializable{
	    //酒店编号
	    public int hotelID = -1;
	    //用户评分
	    public int score = -1;
	    //用户ID
	    public int  userID = -1;
	    //用户名字
	    public String  userName = "";
	    //用户评价
	    public String  review = "";
	    //评价时间
	    public String time = "";
}
