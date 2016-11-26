/**
 * @author Yolanda151250080
 */
package tiquartet.ServerModule.bl.manageuserbl;

import java.util.List;
import tiquartet.CommonModule.vo.UserVO;
import tiquartet.CommonModule.vo.UserFilterVO;
import tiquartet.CommonModule.vo.CreditVO;
import tiquartet.CommonModule.vo.MemberVO;
import tiquartet.ServerModule.po.HotelStaffPO;
import tiquartet.CommonModule.util.Usersort;
import tiquartet.CommonModule.util.ResultMessage;

public class ManageUserController {
	
	static ManageUser manageuser = new ManageUser();
	
	/*
	 * 根据用户名和真实姓名搜索用户
	 */
	public static List<UserVO> accurateSearch (String username, String realName) {
		
		return manageuser.accurateSearch(username, realName);
	}
	
	/*
	 * 根据城市和商圈获取酒店员工列表
	 */
	public static List<HotelStaffVO> searchHotelStaff(int cityID, int districtID) {
		
		return manageuser.searchHotelStaff(cityID, districtID);
	}
	
	/*
	 * 根据用户ID获取用户信息
	 */
	public static UserVO getUser (int userID) {
		
		return manageuser.getUser(userID);
	}
	
	/*
	 * 根据筛选条件、排序方式等条件获取用户信息列表
	 */
	public static List<UserVO> search (UserFilterVO filter, 
			UserSort sort, int rank1, int rank2) {
		
		return manageuser.search(filter, sort, rank1, rank2);
	}
	
	/*
	 * 根据用户ID和金额进行信用充值
	 */
	public static ResultMessage creditRecharge (int userID, double amount) {
		
		return manageuser.creditRecharge(userID, amount);
	}
	
	/*
	 * 根据酒店ID和名称添加酒店
	 */
	public static ResultMessage addHotel (int districtID, String hotelName) {
		
		return manageuser.addHotel(districtID, hotelName);
	}
	
	/*
	 * 根据酒店ID、用户名、密码添加酒店员工
	 */
	public static ResultMessage addHotelStaff (int hotelID, 
			String username, String password) {
		
		return manageuser.addHotelStaff(hotelID, username, password);
	}
	
	/*
	 * 通过用户编号获取信用记录
	 */
	public static List<CreditVO> getCreditRecord (int userID) {
		
		return manageuser.getCreditRecord(userID);
	}
	
	/*
	 * 添加信用记录
	 */
	public static List<CreditVO> addCreditItem (CreditVO creditItem) {
		
		return manageuser.addCreditItem(creditItem);
	}
	
	/*
	 * 注册会员信息
	 */
	public static ResultMessage memberSignIn (MemberVO member) {
		
		return manageuser.memberSignIn(member);
	}
	
	/*
	 * 获取营销人员信息列表
	 */
	public static List<UserVO> marketerList () {
		
		return manageuser.marketerList();
	}
	
}