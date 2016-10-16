package tiquartet.client.blservice.managehotelblservice;

import tiquartet.client.vo.*;

public interface ManageHotelBLService {

	public HotelInfoVO getHotelInfo(long userID);
	
	public ResultMessage modifyHotelInfo(HotelInfoVO hotelInfo);
	
}
