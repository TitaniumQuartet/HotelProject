package tiquartet.ServerModule.bl.hotelinfobl;

import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelDetailsVO;
import tiquartet.CommonModule.vo.HotelInfoVO;
import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.CommonModule.vo.ReviewVO;
import tiquartet.CommonModule.vo.RoomTypeVO;


public class HotelInfoController {
	static HotelInfo HotelInfo=new HotelInfo();
	public static HotelBriefVO getHotelBrief (int hotelID){
		//传入酒店ID返回酒店的简略信息
	      return HotelInfo.getHotelBrief(hotelID);
	}
	
	public static HotelDetailsVO getHotelDetails (int hotelID){	
		//传入酒店编号，返回酒店详细信息
         return HotelInfo.getHotelDetails(hotelID);
	}
	
	public static List<RoomTypeVO> availableRoomType (PreOrderVO preOrder){
		//传入入住日期，酒店，返回可用客房类型及该类型数量
		return HotelInfo.availableRoomType(preOrder);
	}
	
	public static ResultMessage reviewHotel(ReviewVO review){
		//传入一个评价，把他存入数据库中
		return HotelInfo.reviewHotel(review);
	}
	
	public static ResultMessage modifyHotelInfo (HotelInfoVO hotelInfo){
		//修改酒店信息		
		return HotelInfo.modifyHotelInfo(hotelInfo);
	}
   public static List<HotelInfoVO> clientHotelList(int userId){
	   //返回用户订过的酒店列表
	   return HotelInfo.clientHotelList(userId);
   }
}