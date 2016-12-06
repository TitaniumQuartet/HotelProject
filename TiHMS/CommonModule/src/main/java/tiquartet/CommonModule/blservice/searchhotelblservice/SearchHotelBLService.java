package tiquartet.CommonModule.blservice.searchhotelblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import tiquartet.CommonModule.util.HotelSort;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.*;

public interface SearchHotelBLService extends Remote{

	/**
	 * 为用户推荐酒店
	 * @param userID
	 * @return
	 * @throws RemoteException
	 */
	public List<HotelBriefVO> recommend (int userID) throws RemoteException;
	/**
	 * 获得排序好的酒店列表
	 * @param filter
	 * @param sort
	 * @param rank1
	 * @param rank2
	 * @return
	 * @throws RemoteException
	 */
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, HotelSort sort, int rank1, int rank2) throws RemoteException;
	/**
	 * 获取商圈信息
	 * @param
	 * @return
	 * @throws RemoteException
	 */
	public DistrictVO getDistricts() throws RemoteException;
	/**
	 * 更新商圈信息
	 * @param district
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage updateDistricts(DistrictVO district) throws RemoteException;
}
