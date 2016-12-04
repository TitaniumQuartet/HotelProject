package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;

import tiquartet.ServerModule.po.*;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.*;

public interface HotelInfoDataHelper {

	public HotelInfoPO getHotelInfo (int hotelID);
	public ResultMessage insert (HotelInfoPO hotelInfo);
	public ResultMessage update (HotelInfoPO hotelInfo);
	public List <RoomTypePO> getRoomTypes (int hotelID);
	public List <HotelInfoPO> getHotelList (int districtID);
	
}
