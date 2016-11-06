package tiquartet.ServerModule.dataservice.reviewdataservice;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.ReviewPO;

public interface ReviewDataService {
	public ReviewPO search(long hotelID);
	public ResultMessage insert(ReviewPO review);

}
