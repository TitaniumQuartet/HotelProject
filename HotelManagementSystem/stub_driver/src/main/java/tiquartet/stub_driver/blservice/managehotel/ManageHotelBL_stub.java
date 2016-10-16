package tiquartet.stub_driver.blservice.managehotel;

import tiquartet.client.blservice.HotelInfoVO;
import tiquartet.client.blservice.ResultMessage;

public class ManageHotelBL_stub {

	public HotelInfoVO getHotelInfo(long userID);
	{
		return new HotelInfoVO();
	}
	
	public ResultMessage modifyHotelInfo(HotelInfoVO hotelInfo){
		return ResultMessage.succeed;
	}
}
