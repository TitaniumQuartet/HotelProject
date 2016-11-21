package tiquartet.ServerModule.bl.hotelinfobl;

import java.util.*;
import tiquartet.CommonModule.vo.*;
import tiquartet.CommonModule.blservice.hotelinfoblservice.*;
import tiquartet.CommonModule.util.ResultMessage;

public class HotelInfoBL_stub implements HotelInfoBLService{
	
	public HotelBriefVO getHotelBrief (int hotelID){
		return new HotelBriefVO();
	}
	public HotelDetailsVO getHotelDetails (int hotelID){
		return new HotelDetailsVO();
	}
	public ResultMessage reviewHotel(ReviewVO review){
		return new ResultMessage(true);
	}
	public ResultMessage modifyHotelInfo (HotelInfoVO hotelInfo){
		return new ResultMessage(true);
	}
	public List<RoomTypeVO> availableRoomType(PreOrderVO preOrder) {
		return new ArrayList<RoomTypeVO>();
	}
}
