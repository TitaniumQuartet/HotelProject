package tiquartet.ServerModule.dataservice.reviewdataservice;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.ReviewPO;
import java.util.List;

public interface ReviewDataService {
	
	/**
	 * 根据hotelID搜索酒店评论.
	 * @return
	 */
	public List<ReviewPO> searchByHotel (int hotelID);
	
	/**
	 * 向review数据库中添加一条记录.
	 * @return
	 */
	public ResultMessage insert (ReviewPO review);
}
