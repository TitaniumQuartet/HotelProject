package tiquartet.ServerModule.data.reviewdata;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.dataservice.reviewdataservice.*;
import tiquartet.ServerModule.po.*;

public class ReviewData_stub implements ReviewDataService{
	public ReviewPO search(long hotelID){
		return new ReviewPO();
	}
	public ResultMessage insert(ReviewPO review){
		return ResultMessage.SUCCEED;
	}

}
