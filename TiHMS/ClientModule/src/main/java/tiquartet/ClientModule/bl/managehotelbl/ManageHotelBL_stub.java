package tiquartet.ClientModule.bl.managehotelbl;

import tiquartet.ClientModule.vo.*;
import tiquartet.ClientModule.blservice.managehotelblservice.*;
import tiquartet.CommonModule.util.ResultMessage;

public class ManageHotelBL_stub implements ManageHotelBLService{

	public HotelInfoVO getHotelInfo(long userID)
	{
		return new HotelInfoVO();
	}
	
	public ResultMessage modifyHotelInfo(HotelInfoVO hotelInfo){
		return ResultMessage.SUCCEED;
	}
}
