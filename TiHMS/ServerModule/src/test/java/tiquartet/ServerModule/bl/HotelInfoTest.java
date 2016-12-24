package tiquartet.ServerModule.bl;


import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.Test;

import tiquartet.ServerModule.bl.hotelinfobl.HotelInfo;
import tiquartet.ServerModule.bl.manageuserbl.ManageUserController;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelDetailsVO;
import tiquartet.CommonModule.vo.HotelInfoVO;
import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.CommonModule.vo.ReviewVO;
import tiquartet.CommonModule.vo.RoomTypeVO;

public class HotelInfoTest {
	
	private HotelInfo hotelinfo=new HotelInfo();	
	@Test
	public void testgetHotelBrief() throws RemoteException {
		 HotelBriefVO hotelBrief=hotelinfo.getHotelBrief(101001,1);
         assertEquals(hotelBrief.hotelName,"NumOne");
         assertEquals(hotelBrief.star,4);
         assertEquals(hotelBrief.introduction,"best");
         assertEquals(hotelBrief.cityName,"nj");
         assertEquals(hotelBrief.circleName,"xl");
	}
	
	@Test
	public void testgetHotelDetails() throws RemoteException{
		HotelDetailsVO hoteldetails=hotelinfo.getHotelDetails(101001,1);
		assertEquals(hoteldetails.address,"nju");
		if(hoteldetails.averagegrade==4.4){
			assertEquals(true,true);
		}else{
			assertEquals(true,false);
		}
		assertEquals(hoteldetails.circleName,"xl");
		assertEquals(hoteldetails.cityName,"nj");
		assertEquals(hoteldetails.hotelID,101001);
		assertEquals(hoteldetails.introduction,"best");
		if(hoteldetails.lowprice==100){
			assertEquals(true,true);
		}else{
			assertEquals(true,false);
		}
		assertEquals(hoteldetails.serviceintro,"wifi");
		assertEquals(hoteldetails.star,4);
	}
	
	@Test
	public void testavailableRoomType() throws RemoteException{
		PreOrderVO preorder=new PreOrderVO();
		preorder.userID = 1;
		preorder.hotelID = 101001;
		preorder.clientRealName="ccc";
		preorder.hotelName = "NumOne";
		preorder.startTime = "2017-01-05 12:00:00";
		preorder.leaveTime = "2017-01-06 12:00:00";
		preorder.roomType = 1;
		preorder.roomTypeName = "";
		preorder.userName = "Teki";
		preorder.price = 300;
		preorder.numOfRoom = 1;
		List<RoomTypeVO> roomtype=hotelinfo.availableRoomType(preorder);
		for(int i=0;i<roomtype.size();i++){
			if(roomtype.get(i).roomTypeId==1){
				assertEquals(roomtype.get(i).roomType,"home");
				assertEquals(roomtype.get(i).typeIntroduction,"forhome");
				if(roomtype.get(i).price==300){
					assertEquals(true,true);
				}else{
					assertEquals(true,false);
				}
			}else if(roomtype.get(i).roomTypeId==2){
				assertEquals(roomtype.get(i).roomType,"one");
				assertEquals(roomtype.get(i).typeIntroduction,"forone");
				if(roomtype.get(i).price==100){
					assertEquals(true,true);
				}else{
					assertEquals(true,false);
				}
			}else if(roomtype.get(i).roomTypeId==3){
				assertEquals(roomtype.get(i).roomType,"house");
				assertEquals(roomtype.get(i).typeIntroduction,"great");
				if(roomtype.get(i).price==500){
					assertEquals(true,true);
				}else{
					assertEquals(true,false);
				}
			}
		}
		assertEquals(true, true);
	}
	
	@Test
	public void testreviewHotel() throws RemoteException{
		ReviewVO review=new ReviewVO();
		review.hotelID=101001;
		review.review="非常好";
		review.score=5;
		review.userID=1;
		review.userName="Teki";
		review.time="2016-12-20 00:00:00";
		ResultMessage result=hotelinfo.reviewHotel(review);
		assertEquals(result.result,true);
	}
	
	@Test
	public void testmodifyHotelInfo() throws RemoteException{
		HotelDetailsVO hotelInfo=new HotelDetailsVO();
		hotelInfo.address="nju";
		hotelInfo.averagegrade=5;
		hotelInfo.circleID=201;
		hotelInfo.cityName="nj";
		hotelInfo.highprice=200;
		hotelInfo.hotelID=201001;
		hotelInfo.introduction="verygood";
		hotelInfo.serviceintro="wifi";
		hotelInfo.hotelName="Numthree";
		hotelInfo.lowprice=100;
		hotelInfo.star=4;
		ResultMessage result=hotelinfo.modifyHotelInfo(hotelInfo);
		assertEquals(result.result,true);
	}

}
