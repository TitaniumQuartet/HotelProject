package tiquartet.ClientModule.blservice.managehotelblservice;

import tiquartet.ClientModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;

public interface ManageHotelBLService {

	public HotelInfoVO getHotelInfo(long userID);
	
	public ResultMessage modifyHotelInfo(HotelInfoVO hotelInfo);
	
}
