package tiquartet.ClientModule.bl.hotelinfobl;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import tiquartet.ClientModule.vo.HotelBriefVO;
import tiquartet.ClientModule.vo.HotelInfoVO;
import tiquartet.ClientModule.vo.PreOrderVO;
import tiquartet.ClientModule.vo.ReviewVO;
import tiquartet.ClientModule.vo.RoomTypeVO;
import tiquartet.CommonModule.po.ReviewPO;
import tiquartet.CommonModule.util.ResultMessage;

public class HotelInfoTest {
	
	private HotelInfo hotel;	

	@Test
	public void testgetHotelBrief() {
       hotel=new HotelInfo();
       HotelBriefVO hotelbriefvo=hotel.getHotelBrief(0000000111);
       assertEquals(hotelbriefvo.hotelID,0000000111);
	}
	
	@Test
	public void testgetHotelDetails(){
		hotel=new HotelInfo();
	    HotelDetailsVO hoteldetailsvo=hotel.getHotelDetails(0000000111);
	    assertEquals(hoteldetailsvo.hotelID,0000000111);
	}
	
	@Test
	public void testavailableRoomType(){
		hotel=new HotelInfo();
		PreOrderVO preOrder=new PreOrderVO();
		List<RoomTypeVO> roomtype=hotel.availableRoomType(preOrder);
		assertEquals(roomtype.get(0).price,100);
		assertEquals(roomtype.get(1).name,"家庭房");
	}
	
	@Test
	public void testreviewHotel(){
		hotel=new HotelInfo();
		ReviewVO review=new ReviewVO();
		ResultMessage result=hotel.reviewHotel(review);
		assertEquals(result,ResultMessage.SUCCEED);
	}
	
	@Test
	public void testmodifyHotelInfo(){
		hotel=new HotelInfo();
		HotelInfoVO hotelInfo=new HotelInfoVO();
		ResultMessage result=hotel.modifyHotelInfo(hotelInfo);
		assertEquals(result,ResultMessage.SUCCEED);
	}

}
