package tiquartet.client.blservice.hotelinfoblservice;

import java.util.List;
import java.util.Calendar;
import tiquartet.common.util.ResultMessage;
import tiquartet.client.vo.HotelDetailsVO;
import tiquartet.client.vo.ReviewVO;
import tiquartet.client.vo.RoomTypeVO;

public interface HotelInfoBLService {
	public HotelDetailsVO getHotelDetails(long hotelID);
	public List<RoomTypeVO> availableRoomType(Calendar start, Calendar end,
			long hotelID);
	public ResultMessage reviewHotel(ReviewVO review);
}
