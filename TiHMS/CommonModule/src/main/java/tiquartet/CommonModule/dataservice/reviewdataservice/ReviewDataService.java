package tiquartet.CommonModule.dataservice.reviewdataservice;

import tiquartet.CommonModule.po.ReviewPO;
import tiquartet.CommonModule.util.ResultMessage;

public interface ReviewDataService {
	public ReviewPO search(long hotelID);
	public ResultMessage insert(ReviewPO review);

}
