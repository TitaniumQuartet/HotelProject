/**
 * 管理用户的controller类。
 * 
 * @author Yolanda151250080
 * 
 */
package tiquartet.ServerModule.bl.manageuserbl;

import java.util.List;

import tiquartet.CommonModule.blservice.manageuserblservice.ManageUserBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserSort;
import tiquartet.CommonModule.vo.CreditVO;
import tiquartet.CommonModule.vo.MemberVO;
import tiquartet.CommonModule.vo.UserFilterVO;
import tiquartet.CommonModule.vo.UserVO;

public class ManageUserController implements ManageUserBLService{
	
	static ManageUser manageuser = new ManageUser();
	
	/*
	 * 精确查找用户
	 */
	public List<UserVO> accurateSearch (String username, String realName) {
		
		return manageuser.accurateSearch(username, realName);
	}
	
	/*
	 * 获取酒店员工列表
	 */
	public List<UserVO> searchHotelStaff(int cityID, int districtID) {
		
		return manageuser.searchHotelStaff(cityID, districtID);
	}
	
	/*
	 * 获取某个用户的信息
	 */
	public UserVO getUser (int userID) {
		
		return manageuser.getUser(userID);
	}
	
	/*
	 * 筛选用户列表
	 */
	public List<UserVO> search (UserFilterVO filter, 
			UserSort sort, int rank1, int rank2) {
		
		return manageuser.search(filter, sort, rank1, rank2);
	}
	
	/*
	 * 信用充值
	 */
	public ResultMessage creditRecharge (int userID, double amount) {
		
		return manageuser.creditRecharge(userID, amount);
	}
	
	/*
	 * 添加酒店
	 */
	public ResultMessage addHotel (int districtID, String hotelName) {
		
		return manageuser.addHotel(districtID, hotelName);
	}
	
	/*
	 * 添加酒店工作人员
	 */
	public ResultMessage addHotelStaff (int hotelID, 
			String username, String password) {
		
		return manageuser.addHotelStaff(hotelID, username, password);
	}
	
	/*
	 * 获取信用记录
	 */
	public List<CreditVO> getCreditRecord (int userID) {
		
		return manageuser.getCreditRecord(userID);
	}
	
	/*
	 * 添加信用记录
	 */
	public ResultMessage addCreditItem (CreditVO creditItem) {
		
		return manageuser.addCreditItem(creditItem);
	}
	
	/*
	 * 注册会员
	 */
	public ResultMessage memberSignIn (MemberVO member) {
		
		return manageuser.memberSignIn(member);
	}
	
	/*
	 * 获取营销人员列表
	 */
	public List<UserVO> marketerList () {
		
		return manageuser.marketerList();
	}
	
	/*
	 * 添加用户
	 */
	public ResultMessage addUser(UserVO user) {
		
		return manageuser.addUser(user);
	}
	
}