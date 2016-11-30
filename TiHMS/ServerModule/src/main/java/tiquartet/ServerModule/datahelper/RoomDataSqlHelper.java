package tiquartet.ServerModule.datahelper;

import java.util.List;
import java.util.Map;

import tiquartet.ServerModule.datahelper.service.RoomDataHelper;
import tiquartet.ServerModule.po.OrderPO;
import tiquartet.ServerModule.po.RoomPO;
import tiquartet.ServerModule.po.RoomTypePO;

public class RoomDataSqlHelper implements RoomDataHelper{

	public void preOrder(OrderPO preOrder) {
		// TODO Auto-generated method stub
		
	}

	public void cancelPreOrder(OrderPO preOrder) {
		// TODO Auto-generated method stub
		
	}

	public List<RoomTypePO> availableRoomType(int hotelID, String startDate,
			String endDate, int numOfRoom) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Integer, RoomPO> getRoom(){
		return null;
	}
	
	public void update(RoomPO room) {
		// TODO Auto-generated method stub
		
	}

	public void insert(RoomPO room) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int roomID) {
		// TODO Auto-generated method stub
		
	}

	public void checkIn(int roomID) {
		// TODO Auto-generated method stub
		
	}

	public void checkOut(int roomID) {
		// TODO Auto-generated method stub
		
	}

	public void insertType(int hotelID, RoomTypePO room) {
		// TODO Auto-generated method stub
		
	}

	public void updateType(int hotelID, RoomTypePO room) {
		// TODO Auto-generated method stub
		
	}

}
