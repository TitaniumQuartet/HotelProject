package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.List;
import org.junit.Test;

import tiquartet.CommonModule.vo.*;
import tiquartet.ServerModule.bl.hotelinfobl.HotelInfo;
import tiquartet.CommonModule.util.ResultMessage;

public class HotelInfoTest {
	
	private HotelInfo hotel;	

	@Test
	public void testgetHotelBrief() {
   
	}
	
	@Test
	public void testgetHotelDetails(){
		
	}
	
	@Test
	public void testavailableRoomType() throws RemoteException{
		hotel=new HotelInfo();
		PreOrderVO preOrder=new PreOrderVO();
		List<RoomTypeVO> roomtype=hotel.availableRoomType(preOrder);
		assertEquals(roomtype.get(0).price,100);
		assertEquals(roomtype.get(1).roomType,"家庭房");
	}
	
	@Test
	public void testreviewHotel() throws RemoteException{
		hotel=new HotelInfo();
		ReviewVO review=new ReviewVO();
		ResultMessage result=hotel.reviewHotel(review);
		assertEquals(result.result,true);
	}
	
	@Test
	public void testmodifyHotelInfo() throws RemoteException{
		hotel=new HotelInfo();
		HotelInfoVO hotelInfo=new HotelInfoVO();
		ResultMessage result=hotel.modifyHotelInfo(hotelInfo);
		assertEquals(result.result,true);
	}

}
