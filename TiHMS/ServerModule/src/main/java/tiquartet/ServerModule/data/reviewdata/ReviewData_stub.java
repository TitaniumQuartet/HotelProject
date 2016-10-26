package tiquartet.ServerModule.data.reviewdata;

import tiquartet.CommonModule.dataservice.reviewdataservice.*;
import tiquartet.CommonModule.po.*;
import tiquartet.CommonModule.util.ResultMessage;

public class ReviewData_stub implements ReviewDataService{
	public ReviewPO search(long hotelID){
		return new ReviewPO();
	}
	public ResultMessage insert(ReviewPO review){
		return ResultMessage.SUCCEED;
	}

}
