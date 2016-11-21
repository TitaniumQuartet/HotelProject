package tiquartet.ServerModule.data.reviewdata;

import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.dataservice.reviewdataservice.*;
import tiquartet.ServerModule.po.*;

public class ReviewData_stub implements ReviewDataService{
	
	public ResultMessage insert(ReviewPO review){
		return new ResultMessage(true);
	}
	public List<ReviewPO> searchByHotel(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}

}
