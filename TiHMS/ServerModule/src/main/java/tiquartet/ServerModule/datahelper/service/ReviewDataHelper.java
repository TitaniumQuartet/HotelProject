package tiquartet.ServerModule.datahelper.service;

import java.util.List;
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
	public void insert (ReviewPO review);
	
}
