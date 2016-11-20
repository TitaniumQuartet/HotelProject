package tiquartet.ServerModule.dataservice.reviewdataservice;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.ReviewPO;
import java.util.List;

public interface ReviewDataService {
	
	public List<ReviewPO> searchByHotel (int hotelID);
	public ResultMessage insert (ReviewPO review);
}
