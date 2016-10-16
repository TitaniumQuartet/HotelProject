package tiquartet.server;

public interface ManageHotelbl {

	public HotelInfoVO getHotelInfo(long userID);
	
	public ResultMessage modifyHotelInfo(HotelInfoVO hotelInfo);
	
}
