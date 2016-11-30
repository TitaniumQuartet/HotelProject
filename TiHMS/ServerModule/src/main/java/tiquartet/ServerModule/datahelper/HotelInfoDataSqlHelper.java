package tiquartet.ServerModule.datahelper;

import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.ServerModule.datahelper.service.HotelInfoDataHelper;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.RoomTypePO;

public class HotelInfoDataSqlHelper implements HotelInfoDataHelper{

	public Map<Integer,HotelInfoPO> getHotelInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateRate(int HotelID, double newRate) {
		// TODO Auto-generated method stub
		
	}

	public void insert(HotelInfoPO hotelInfo) {
		// TODO Auto-generated method stub
		
	}

	public void update(HotelInfoPO hotelInfo) {
		// TODO Auto-generated method stub
		
	}

	public List<RoomTypePO> getRoomTypes(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RoomTypePO> availableRoomType(PreOrderVO preOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<HotelInfoPO> getHotelList(int districtID) {
		// TODO Auto-generated method stub
		return null;
	}

}
