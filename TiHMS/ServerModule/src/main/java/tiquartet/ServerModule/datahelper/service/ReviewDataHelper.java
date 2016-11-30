package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;

import tiquartet.ServerModule.po.ReviewPO;

public interface ReviewDataHelper {

	/**
	 * 获得酒店评价
	 * @param 
	 */
	public Map<Integer, ReviewPO> searchByHotel ();
	
	/**
	 * 新增评价
	 * @param 
	 */
	public void insert (ReviewPO review);
	
}
