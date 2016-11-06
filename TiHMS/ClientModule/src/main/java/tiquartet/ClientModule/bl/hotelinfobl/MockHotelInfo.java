package tiquartet.ClientModule.bl.hotelinfobl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tiquartet.ClientModule.vo.PreOrderVO;
import tiquartet.ClientModule.vo.RoomTypeVO;

public class MockHotelInfo extends HotelInfo{
	public List<RoomTypeVO> availableRoomType(PreOrderVO preOrder){
		RoomTypeVO room1=new RoomTypeVO();
		room1.roomType=001;
		room1.name="单人房";
		room1.price=100;
		RoomTypeVO room2=new RoomTypeVO();
		room2.roomType=002;
		room2.name="家庭房";
		room2.price=200;
		ArrayList<RoomTypeVO> roomlist=new ArrayList<RoomTypeVO>();
		roomlist.add(room1);
		roomlist.add(room2);
		return roomlist;
	}

}
