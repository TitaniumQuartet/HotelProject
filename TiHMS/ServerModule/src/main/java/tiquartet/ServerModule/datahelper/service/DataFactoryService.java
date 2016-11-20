package tiquartet.ServerModule.datahelper.service;

public interface DataFactoryService {
	

	public OrderDataHelper getOrderDataHelper();
	
	public UserDataHelper getUserDataHelper();

	public CreditDataHelper getCreditDataHelper();
	
	public HotelInfoDataHelper getHotelInfoDataHelper();

	public ReviewDataHelper getReviewDataHelper();
	
	public RoomDataHelper getRoomDataHelper();

	public StrategyDataHelper getStrategyDataHelper();
	
	public LocationDataHelper getLocationDataHelper();


}
