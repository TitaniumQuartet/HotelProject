package tiquartet.CommonModule.blservice.hotelinfoblservice;

import java.util.List;
import java.util.Calendar;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.*;

public interface HotelInfoBLService {
	public HotelBriefVO getHotelBrief (int hotelID);
	public HotelDetailsVO getHotelDetails (int hotelID);
	public List<RoomTypeVO> availableRoomType(PreOrderVO preOrder);
	public ResultMessage reviewHotel(ReviewVO review);
	public ResultMessage modifyHotelInfo (HotelInfoVO hotelInfo);
}
