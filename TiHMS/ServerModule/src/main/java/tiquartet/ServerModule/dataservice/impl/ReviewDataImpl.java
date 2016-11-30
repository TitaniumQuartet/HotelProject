package tiquartet.ServerModule.dataservice.impl;

import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.ReviewDataHelper;
import tiquartet.ServerModule.dataservice.reviewdataservice.ReviewDataService;
import tiquartet.ServerModule.po.ReviewPO;

public class ReviewDataImpl implements ReviewDataService{

	private Map<Integer, ReviewPO> map;
	
	private ReviewDataHelper reviewDataHelper;
	
	private DataFactoryService dataFactory;

	private static ReviewDataImpl reviewDataServiceImpl;
	
	public static ReviewDataImpl getInstance(){
		if(reviewDataServiceImpl == null){
			reviewDataServiceImpl = new ReviewDataImpl();
		}
		return reviewDataServiceImpl;
	}
	
	public ReviewDataImpl(){
		if(map == null){
			dataFactory = new DataFactory();
			reviewDataHelper = dataFactory.getReviewDataHelper();
			map = reviewDataHelper.searchByHotel();
		}
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	@Override
	public List<ReviewPO> searchByHotel(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insert(ReviewPO review) {
		// TODO Auto-generated method stub
		return null;
	}

}
