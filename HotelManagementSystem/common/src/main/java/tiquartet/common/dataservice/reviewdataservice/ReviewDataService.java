package tiquartet.common.dataservice.reviewdataservice;

public interface ReviewDataService {
	public ReviewPO search(long hotelID);
	public ResultMessage insert(ReviewPO review);

}
