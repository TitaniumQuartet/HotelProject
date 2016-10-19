package tiquartet.ClientModule.blservice.hotelinfoblservice;

import java.util.List;
import java.util.Calendar;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ClientModule.vo.HotelDetailsVO;
import tiquartet.ClientModule.vo.ReviewVO;
import tiquartet.ClientModule.vo.RoomTypeVO;

public interface HotelInfoBLService {
	public HotelDetailsVO getHotelDetails(long hotelID);
	public List<RoomTypeVO> availableRoomType(Calendar start, Calendar end,
			long hotelID);
	public ResultMessage reviewHotel(ReviewVO review);
}
