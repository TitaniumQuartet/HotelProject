package manageHotel.stub_driver;

import tiquartet.client.blservice.HotelInfoVO;
import tiquartet.client.blservice.ResultMessage;

public class ManageHotelbl_stub {

	public HotelInfoVO getHotelInfo(long userID);
	{
		return new HotelInfoVO();
	}
	
	public ResultMessage modifyHotelInfo(HotelInfoVO hotelInfo){
		return ResultMessage.succeed;
	}
}
