package tiquartet.CommonModule.blservice.searchhotelblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import tiquartet.CommonModule.vo.*;

public interface SearchHotelBLService extends Remote{

	public List<HotelBriefVO> recommend (int userID) throws RemoteException;
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, SortHotelVO sort, int rank1, int rank2) throws RemoteException;
	
}
