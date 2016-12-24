package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;

import tiquartet.ServerModule.po.*;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.*;

/**
 * 对hotelInfo数据库的操作的接口.
 * @author Teki
 */
public interface HotelInfoDataHelper {

	/**
	 * 根据hotelID得到酒店的信息.
	 * @return
	 */
	public HotelInfoPO getHotelInfo (int hotelID);
	
	/**
	 * 向hotelInfo数据库中插入一条记录.
	 * @return
	 */
	public ResultMessage insert (HotelInfoPO hotelInfo);
	
	/**
	 * 更新相关的酒店信息.
	 * @return
	 */
	public ResultMessage update (HotelInfoPO hotelInfo);
	
	/**
	 * 通过hotelID得到该酒店的所有房间类型.
	 * @return
	 */
	public List <RoomTypePO> getRoomTypes (int hotelID);
	
	/**
	 * 得到该商圈的所有酒店列表.
	 * @return
	 */
	public List <HotelInfoPO> getHotelList (int districtID);
	
}
