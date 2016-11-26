/**
 * @author Yolanda151250080
 */
package tiquartet.ServerModule.bl.manageuserbl;

import java.util.Collections;
import org.apache.commons.beanutils.BeanUtils; 
import java.util.List;

import tiquartet.CommonModule.blservice.manageorderblservice.ManageOrderBLService;
import tiquartet.ServerModule.dataservice.userdataservice.UserDataController;
import tiquartet.CommonModule.vo.UserVO;
import tiquartet.ServerModule.po.UserPO;
import tiquartet.CommonModule.vo.HotelStaffVO;
import tiquartet.ServerModule.po.HotelStaffPO;
import tiquartet.CommonModule.vo.UserFilterVO;
import tiquartet.CommonModule.vo.CreditVO;
import tiquartet.CommonModule.Util.UserSort;

public class ManageUser implements ManageOrderBLService {

	/*
	 * 根据用户名和真实姓名搜索用户
	 */
	public List<UserVO> accurateSearch (String username, String realName) {
		
		//获取po的列表
		List<UserPO> user = new List<UserPO>();
		user.addAll(UserDataController.searchUser(username, realName));
		
		//po转vo
		List<UserVO> userList = new List<UserVO>();
		UserVO uservo;
		
		for(UserPO userpo: user){
			uservo = new UserVO();
			BeanUtils.copyProperties(uservo, userpo);
			userList.add(uservo);
		}
		
		return userList;
	}
	
	/*
	 * 根据城市和商圈获取酒店员工列表
	 */
	public List<HotelStaffVO> searchHotelStaff(int cityID, int districtID) {
		
		//获取po列表
		List<HotelStaffPO> hotelstaff = new List<HotelStaffPO>();
		hotelstaff.addAll(UserDataController.searchHotelStaff(cityID, districtID));
		
		//po转vo
		List<HotelStaffVO> hotelstaffList = new List<HotelStaffVO>();
		HotelStaffVO hotelstaffvo;
		
		for(HotelStaffPO hotelstaffpo: hotelstaff){
			hotelstaffvo = new HotelStaffVO();
			BeanUtils.copyProperties(hotelstaffvo, hotelstaffpo);
			hotelstaffList.add(hotelstaffvo);
		}
		
		return hotelstaffList;
	}
	
	/*
	 * 根据用户ID获取用户信息
	 */
	public UserVO getUser (int userID) {
		
		UserPO userpo = new UserPO();
		userpo = UserDataController.getUser(userID);
		
		UserVO uservo = new UserVO();
		BeanUtils.copyProperties(uservo, userpo);
		
		return uservo;
	}
	
	/*
	 * 根据筛选条件、排序方式等条件获取用户信息列表
	 */
	public List<UserVO> search (UserFilterVO filter, 
			UserSort sort, int rank1, int rank2) {
		
		//首先获取用户信息列表
		List<UserPO> user = new List<UserPO>();
		user = UserDataController.userList();
		
		List<UserVO> userlist = new List<UserVO>();
		UserVO uservo;
		
		for(UserPO userpo: user){
			uservo = new UserVO();
			BeanUtils.copyProperties(uservo, userpo);
			userlist.add(uservo);
		}
		
		//根据筛选条件进行筛选
		List<UserVO> filterUser = new List<UserVO>();
		for(UserVO uservos: userList){
			if(uservos.userName.equals(filter.userName)
					&& uservos.realName.equals(filter.realName)
					&& uservos.isMember == filter.isMember){
				filterUser.add(uservos);
			}
		}
		
		//按照要求进行排序
		//用户名升序排列
		if(sort == UserSort.USERNAMEASCEND){
			Collections.sort(filterUser, new Comparator<UserVO>(){
				@Override
				public int compare(UserVO o1, UserVO o2) {
				return (o1.userName).compareToIgnoreCase(o2.userName);
				}
				});
		}
		//用户名降序
		else if(sort == UserSort.USERNAMEDESCEND){
			//先升序排列
			Collections.sort(filterUser, new Comparator<UserVO>(){
				@Override
				public int compare(UserVO o1, UserVO o2) {
				return (o1.userName).compareToIgnoreCase(o2.userName);
				}
				});
			//再把升序的倒置
			Collections.reverse(filterUser);
		}
		//真实姓名升序
		else if(sort == UserSort.REALNAMEASCEND){
			Collections.sort(filterUser, new Comparator<UserVO>(){
				@Override
				public int compare(UserVO o1, UserVO o2) {
				return (o1.realName).compareToIgnoreCase(o2.realName);
				}
				});
		}
		//真实姓名降序
		else if(sort == UserSort.REALNAMEDESCEND){
			//先升序排列
			Collections.sort(filterUser, new Comparator<UserVO>(){
				@Override
				public int compare(UserVO o1, UserVO o2) {
				return (o1.realName).compareToIgnoreCase(o2.realName);
				}
				});
			//再把升序的倒置
			Collections.reverse(filterUser);
		}
		
		//返回rank1到rank2之间的信息
		return filterUser.subList(rank1, rank2);
	}
	
	/*
	 * 根据用户ID和金额进行信用充值
	 */
	public ResultMessage creditRecharge (int userID, double amount) {
		
		return UserDataController.addCredit(userID, amount);
	}
	
	/*
	 * 根据酒店ID和名称添加酒店
	 */
	public ResultMessage addHotel (int districtID, String hotelName) {
		
		return UserDataController.addHotel(districtID, hotelName);
	}
	
	/*
	 * 根据酒店ID、用户名、密码添加酒店员工
	 */
	public ResultMessage addHotelStaff (int hotelID, 
			String username, String password) {
		
		return UserDataController.addHotelStaff(hotelID, username, password);
	}
	
	/*
	 * 通过用户编号获取信用记录
	 */
	public List<CreditVO> getCreditRecord (int userID) {
		
		//获取po的列表
		List<CreditPO> credit = new List<CreditPO>();
		credit.addAll(UserDataController.getCreditRecord(userID));
		
		//po转vo
		List<CreditVO> creditList = new List<CreditVO>();
		CreditVO creditvo;
		
		for(CreditPO creditpo: credit){
			creditvo = new CreditVO();
			BeanUtils.copyProperties(creditvo, creditpo);
			creditList.add(creditvo);
		}
		
		return creditList;
	}
	
	/*
	 * 添加信用记录
	 */
	public List<CreditVO> addCreditItem (CreditVO creditItem) {
		
		CreditPO creditpo = new CreditPO();
		BeanUtils.copyProperties(creditpo, creditItem);
		
		return UserDataController.addCreditItem(creditpo);
	}
	
	/*
	 * 注册会员信息
	 */
	public ResultMessage memberSignIn (MemberVO member) {
		
		MemberPO memberpo = new MemberPO();
		BeanUtils.copyProperties(memberpo, member);

		return UserDataController.memberSignIn(memberpo);
	}
	
	/*
	 * 获取营销人员信息列表
	 */
	public List<UserVO> marketerList () {
		
		//获取po列表
		List<UserPO> marketer = new List<UserPO>();
		user.addAll(UserDataController.marketerList());
		
		//po转vo
		List<UserVO> marketerList = new List<UserVO>();
		UserVO marketervo;
		
		for(UserPO marketerpo: marketer){
			marketervo = new UserVO();
			BeanUtils.copyProperties(marketervo, marketerpo);
			creditList.add(marketervo);
		}
		
		return marketerList;
	}
	
}
