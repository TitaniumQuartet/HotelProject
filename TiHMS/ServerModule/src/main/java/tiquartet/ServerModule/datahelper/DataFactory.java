package tiquartet.ServerModule.datahelper;

import tiquartet.ServerModule.datahelper.service.*;
public class DataFactory implements DataFactoryService{
	
	public OrderDataHelper getOrderDataHelper() {
		OrderDataHelper orderDao = new OrderDataSqlHelper();
		return orderDao;
	}

	public UserDataHelper getUserDataHelper() {
		UserDataHelper userDao = new UserDataSqlHelper();
		return userDao;
	}

	public CreditDataHelper getCreditDataHelper(){
		CreditDataHelper creditDao = new CreditDataSqlHelper();
		return creditDao;
	}
	
	public HotelInfoDataHelper getHotelInfoDataHelper(){
		HotelInfoDataHelper hotelDao = new HotelInfoDataSqlHelper();
		return hotelDao;
	}

	public ReviewDataHelper getReviewDataHelper(){
		ReviewDataHelper reviewDao = new ReviewDataSqlHelper();
		return reviewDao;
	}
	
	public RoomDataHelper getRoomDataHelper(){
		RoomDataHelper roomDao = new RoomDataSqlHelper();
		return roomDao;
	}

	public StrategyDataHelper getStrategyDataHelper(){
		StrategyDataHelper strategyDao = new StrategyDataSqlHelper();
		return strategyDao;
	}
	
	public LocationDataHelper getLocationDataHelper(){
		LocationDataHelper locationDao = new LocationDataSqlHelper();
		return locationDao;
	}


}
