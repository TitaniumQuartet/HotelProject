package tiquartet.ServerModule.data.hotelinfodata;


import java.util.*;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.ServerModule.dataservice.hotelinfodataservice.*;
import tiquartet.ServerModule.po.*;


public class HotelInfoData_stub implements HotelInfoDataService{
	public HotelPO getHotelInfo(long hotelID){
		System.out.println("yes");
		return new HotelPO();
	}
	public HotelDetailsPO search(long hotelID){
		System.out.println("yes");
		return new HotelDetailsPO();
	}
	public HotelInfoPO updateRate(long HotelID, int newRate){
		System.out.println("yes");
		return new HotelInfoPO();
	}
	public ResultMessage insert(HotelDetailsPO hotel){
		System.out.println("yes");
		return new ResultMessage(true);
	}
	public ResultMessage update(HotelInfoPO hotelInfo){
		System.out.println("yes");
		return new ResultMessage(true);
	}
	public RoomTypePO getRoomType(long hotelID, int roomTypeID){
		System.out.println("yes");
		return new RoomTypePO();
	}
	public List<RoomTypePO> getRoomTypes(long HotelID){
		System.out.println("yes");
		return new ArrayList<RoomTypePO>();
	}
	public HotelPO getHotelList(int cityID, int districtID){
		System.out.println("yes");
		return new HotelPO();
	}
	public HotelPO briefHotelInfo(long hotelID){
		System.out.println("Getting brief info...");
		return new HotelPO();
	}
	public HotelDetailsPO searchHotelDetails(long hotelID){
		System.out.println("Getting detailed info...");
		return new HotelDetailsPO();
	}
	public HotelInfoPO getHotelInfo(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}
	public ResultMessage updateRate(int HotelID, double newRate) {
		// TODO Auto-generated method stub
		return null;
	}
	public ResultMessage insert(HotelInfoPO hotelInfo) {
		// TODO Auto-generated method stub
		return null;
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
