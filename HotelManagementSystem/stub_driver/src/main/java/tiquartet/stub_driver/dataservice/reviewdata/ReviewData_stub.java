package tiquartet.stub_driver.dataservice.reviewdata;

import tiquartet.common.dataservice.reviewdataservice.*;
import tiquartet.common.po.*;
import tiquartet.common.util.ResultMessage;

public class ReviewData_stub implements ReviewDataService{
	public ReviewPO search(long hotelID){
		return new ReviewPO();
	}
	public ResultMessage insert(ReviewPO review){
		return ResultMessage.SUCCEED;
	}

}
