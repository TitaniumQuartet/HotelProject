package tiquartet.CommonModule.vo;

import java.io.Serializable;

import tiquartet.CommonModule.util.StrategyType;

public class StrategyVO implements Serializable {
	// 酒店编号
	public int hotelID = -1;
	// 商圈编号
	public int circleID = -1;
	// 策略介绍
	public String strategyIntro = "";
	// 折扣
	public double discount = -1;
	// 策略编号
	public int strategyID = -1;
	// 门槛上线
	public double[] memberThreShold = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	// 会员等级对应折扣
	public double[] memberDiscount = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	// 折扣策略开始时间
	public String startTime = "";
	// 折扣策略结束时间
	public String endTime = "";
	// 折扣类型
	public StrategyType strategyType;
	// 所需房间数量
	public int numOfRoom = -1;
	// 合作企业
	public String company = "";
}
