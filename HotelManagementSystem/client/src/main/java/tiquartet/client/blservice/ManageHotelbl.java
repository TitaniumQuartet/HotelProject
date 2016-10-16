package tiquartet.client.blservice;

import tiquartet.client.vo.*;

public interface ManageHotelbl {

	public HotelInfoVO getHotelInfo(long userID);
	
	public ResultMessage modifyHotelInfo(HotelInfoVO hotelInfo);
	
}
