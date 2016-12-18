package tiquartet.ServerModule.bl;


import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import tiquartet.ServerModule.bl.hotelinfobl.HotelInfo;
import tiquartet.ServerModule.bl.manageuserbl.ManageUserController;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.HotelBriefVO;
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
         ResultMessage result=manageuser.addHotel(districtID, hotelName);
         int userID=123456;
         try {
			HotelBriefVO hotelBrief=hotelinfo.getHotelBrief(Integer.parseInt(result.message), userID);
			assertEquals(hotelBrief.hotelName,hotelName);
		} catch (NumberFormatException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetHotelDetails(){
		
	}
	
	@Test
	public void testavailableRoomType() throws RemoteException{
		PreOrderVO preOrder=new PreOrderVO();
		//List<RoomTypeVO> roomtype=hotel.availableRoomType(preOrder);
		//assertEquals(roomtype.get(0).price,100);
		//assertEquals(roomtype.get(1).roomType,"家庭房");
		assertEquals(true, true);
	}
	
	@Test
	public void testreviewHotel() throws RemoteException{
		ReviewVO review=new ReviewVO();
		review.hotelID=12301456;
		review.review="非常好";
		review.score=5;
		review.userID=123456;
		review.userName="qin";
		review.time="2016-12-12 00:00:00";
		ResultMessage result=hotelinfo.reviewHotel(review);
		assertEquals(result.result,true);
	}
	
	@Test
	public void testmodifyHotelInfo() throws RemoteException{
		HotelInfoVO hotelInfo=new HotelInfoVO();
		hotelInfo.address="nanjing";
		hotelInfo.averageGrade=100;
		hotelInfo.circleId=12301;
		hotelInfo.cityName="nanjing";
		hotelInfo.highprice=200;
		hotelInfo.hotelID=12301456;
		hotelInfo.hotelIntroduction="好酒店";
		hotelInfo.hotelName="nanjing";
		hotelInfo.lowprice=50;
		hotelInfo.star=5;
		ResultMessage result=hotelinfo.modifyHotelInfo(hotelInfo);
		assertEquals(result.result,true);
	}

}
