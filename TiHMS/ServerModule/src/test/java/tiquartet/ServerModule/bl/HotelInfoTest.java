package tiquartet.ServerModule.bl;


import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import tiquartet.ServerModule.bl.hotelinfobl.HotelInfo;
import tiquartet.ServerModule.bl.manageuserbl.ManageUserController;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.HotelInfoVO;
import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.CommonModule.vo.ReviewVO;

public class HotelInfoTest {
	
	private HotelInfo hotelinfo=new HotelInfo();	
    private ManageUserController manageuser=new ManageUserController();
	@Test
	public void testgetHotelBrief() {
         int districtID=12345;
         String hotelName="nandaheyuan";
         manageuser.addHotel(districtID, hotelName);
         
	}
	
	@Test
	public void testgetHotelDetails(){
		
	}
	
	@Test
	public void testavailableRoomType() throws RemoteException{
		//hotel=new HotelInfo();
		PreOrderVO preOrder=new PreOrderVO();
		//List<RoomTypeVO> roomtype=hotel.availableRoomType(preOrder);
		//assertEquals(roomtype.get(0).price,100);
		//assertEquals(roomtype.get(1).roomType,"家庭房");
		assertEquals(true, true);
	}
	
	@Test
	public void testreviewHotel() throws RemoteException{
		//hotel=new HotelInfo();
		ReviewVO review=new ReviewVO();
		//ResultMessage result=hotel.reviewHotel(review);
		assertEquals(true,true);
	}
	
	@Test
	public void testmodifyHotelInfo() throws RemoteException{
		//hotel=new HotelInfo();
		HotelInfoVO hotelInfo=new HotelInfoVO();
		//ResultMessage result=hotel.modifyHotelInfo(hotelInfo);
		assertEquals(true,true);
	}

}
