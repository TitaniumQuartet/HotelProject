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
		if(dataFactory == null){
			dataFactory = new DataFactory();
			reviewDataHelper = dataFactory.getReviewDataHelper();
		}
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	/**
	 * 根据hotelID搜索酒店评论.
	 * @return
	 */
	@Override
	public List<ReviewPO> searchByHotel(int hotelID) {
		return reviewDataHelper.searchByHotel(hotelID);
	}

	/**
	 * 向review数据库中添加一条记录.
	 * @return
	 */
	@Override
	public ResultMessage insert(ReviewPO review) {
		return reviewDataHelper.insert(review);
	}

}
