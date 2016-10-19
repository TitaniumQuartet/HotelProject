package tiquartet.StubDriver.blservice.hotelinfo;

import java.util.*;
import tiquartet.ClientModule.vo.*;
import tiquartet.ClientModule.blservice.hotelinfoblservice.*;
import tiquartet.CommonModule.util.ResultMessage;

public class HotelInfoBL_stub implements HotelInfoBLService{
	public HotelDetailsVO getHotelDetails(long hotelID){
		return new HotelDetailsVO();
	}
	public List<RoomTypeVO> availableRoomType(Calendar start, Calendar end, long hotelID){
		return new ArrayList<RoomTypeVO>();
	}
	public ResultMessage reviewHotel(ReviewVO review){
		return ResultMessage.SUCCEED;
	}
}
