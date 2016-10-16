package tiquartet.common.dataservice.reviewdataservice;

import tiquartet.common.po.ReviewPO;
import tiquartet.common.util.ResultMessage;

public interface ReviewDataService {
	public ReviewPO search(long hotelID);
	public ResultMessage insert(ReviewPO review);

}
