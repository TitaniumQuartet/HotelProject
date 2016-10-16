package tiquartet.stub_driver.reviewdata;

import tiquartet.common.dataservice.reviewdataservice;

public class ReviewData_stub implements ReviewDtaService{
	public ReviewPO search(long hotelID){
		return new ReviewPO();
	}
	public ResultMessage insert(ReviewPO review){
		return ResultMessage.SUCCEED;
	}

}
