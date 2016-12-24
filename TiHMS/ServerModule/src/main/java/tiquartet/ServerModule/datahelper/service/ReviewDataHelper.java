package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.ReviewPO;

public interface ReviewDataHelper {

	/**
	 * 根据hotelID搜索酒店评论.
	 * @return
	 */
	public List<ReviewPO> searchByHotel (int hotelID);
	
	/**
	 * 新增评价
	 * @param 
	 */
	public ResultMessage insert (ReviewPO review);
	
}
