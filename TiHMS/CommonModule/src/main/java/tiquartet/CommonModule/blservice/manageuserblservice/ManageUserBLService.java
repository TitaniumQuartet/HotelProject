package tiquartet.CommonModule.blservice.manageuserblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserSort;
import tiquartet.CommonModule.vo.*;

public interface ManageUserBLService extends Remote{
	
	public List<UserVO> accurateSearch (String username, String realName) throws RemoteException;

	public List<HotelStaffVO> searchHotelStaff(int cityID, int districtID) throws RemoteException;
	
	public UserVO getUser (int userID) throws RemoteException;
	
	public List<UserVO> search (UserFilterVO filter, UserSort sort, int rank1, int rank2) throws RemoteException;
	
	public ResultMessage creditRecharge (int userID, double amount) throws RemoteException;
	
	public ResultMessage addHotel(int districtID, String hotelName) throws RemoteException;
	
	public ResultMessage addUser(UserVO user) throws RemoteException;
	
	public ResultMessage memberSignIn (MemberVO member) throws RemoteException;
	
	public List<CreditVO> addCreditItem (CreditVO creditItem) throws RemoteException;
	
	public List<CreditVO> getCreditRecord (int userID) throws RemoteException;
	
	public List<UserVO> marketerList() throws RemoteException;
	
}
