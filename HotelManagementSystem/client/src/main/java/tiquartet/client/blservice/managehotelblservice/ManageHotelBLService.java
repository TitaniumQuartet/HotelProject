package tiquartet.client.blservice.managehotelblservice;

import tiquartet.client.vo.*;
import tiquartet.common.util.ResultMessage;

public interface ManageHotelBLService {

	public HotelInfoVO getHotelInfo(long userID);
	
	public ResultMessage modifyHotelInfo(HotelInfoVO hotelInfo);
	
}
