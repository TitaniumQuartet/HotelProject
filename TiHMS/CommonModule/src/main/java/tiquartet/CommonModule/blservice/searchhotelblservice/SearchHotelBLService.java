package tiquartet.CommonModule.blservice.searchhotelblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import tiquartet.CommonModule.util.HotelSort;
import tiquartet.CommonModule.vo.*;

public interface SearchHotelBLService extends Remote{

	/**
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
	
}
