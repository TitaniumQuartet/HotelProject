package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.ReviewPO;

public interface ReviewDataHelper {

	/**
	 * 获得酒店评价
	 * @param 
	 */
	public List<ReviewPO> searchByHotel (int hotelID);
	
	/**
	 * 新增评价
	 * @param 
	 */
	public ResultMessage insert (ReviewPO review);
	
}
