package tiquartet.stub_driver.blservice.managehotel;

import tiquartet.client.vo.*;
import tiquartet.client.blservice.managehotelblservice.*;
import tiquartet.common.util.ResultMessage;

public class ManageHotelBL_stub implements ManageHotelBLService{

	public HotelInfoVO getHotelInfo(long userID)
	{
		return new HotelInfoVO();
	}
	
	public ResultMessage modifyHotelInfo(HotelInfoVO hotelInfo){
		return ResultMessage.SUCCEED;
	}
}
