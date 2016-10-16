package tiquartet.common.dataservice.reviewdataservice;

import tiquartet.common.po.reviewpo.ReviewPO;
import tiquartet.common.util.ResultMessage;

public interface ReviewDataService {
	public ReviewPO search(long hotelID);
	public ResultMessage insert(ReviewPO review);

}