/**
 * @author Yolanda151250080
 */
package tiquartet.ServerModule.bl.manageuserbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tiquartet.CommonModule.blservice.manageuserblservice.ManageUserBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserSort;
import tiquartet.CommonModule.vo.CreditVO;
import tiquartet.CommonModule.vo.HotelStaffVO;
import tiquartet.CommonModule.vo.MemberVO;
import tiquartet.CommonModule.vo.UserFilterVO;
import tiquartet.CommonModule.vo.UserVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.HotelStaffPO;
import tiquartet.ServerModule.po.MemberPO;
import tiquartet.ServerModule.po.UserPO;

public class ManageUser implements ManageUserBLService {
	
	static DataFactory dataFactory=new DataFactory();

	/*
	 * 根据用户名和真实姓名搜索用户
	 */
	public List<UserVO> accurateSearch (String username, String realName) {
		
		//获取po的列表
		List<UserPO> user = new ArrayList<UserPO>();
		user.addAll(dataFactory.getUserDataHelper().searchUser(username, realName));
		
		//po转vo
		List<UserVO> userList = new ArrayList<UserVO>();
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
		List<HotelStaffPO> hotelstaff = new ArrayList<HotelStaffPO>();
		hotelstaff.addAll(dataFactory.getUserDataHelper().searchHotelStaff(cityID, districtID));
		
		//po转vo
		List<HotelStaffVO> hotelstaffList = new ArrayList<HotelStaffVO>();
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
		userpo = dataFactory.getUserDataHelper().getUser(userID);
		
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
		List<UserPO> user = new ArrayList<UserPO>();
		user = dataFactory.getUserDataHelper().userList();
		
		List<UserVO> userlist = new ArrayList<UserVO>();
		UserVO uservo;
		
		for(UserPO userpo: user){
			uservo = new UserVO();
			BeanUtils.copyProperties(uservo, userpo);
			userlist.add(uservo);
		}
		
		//根据筛选条件进行筛选
		List<UserVO> filterUser = new ArrayList<UserVO>();
		for(UserVO uservos: userlist){
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
		
		dataFactory.getUserDataHelper().addCredit(userID, amount);
		
		return new ResultMessage(true);
	}
	
	/*
	 * 根据酒店ID和名称添加酒店
	 */
	public ResultMessage addHotel (int districtID, String hotelName) {
		
		dataFactory.getUserDataHelper().addHotel(districtID, hotelName);
		
		return new ResultMessage(true);
	}
	
	/*
	 * 根据酒店ID、用户名、密码添加酒店员工
	 */
	public ResultMessage addHotelStaff (int hotelID, 
			String username, String password) {
		
		dataFactory.getUserDataHelper().addHotelStaff(hotelID, username, password);
		
		return new ResultMessage(true);
	}
	
	/*
	 * 通过用户编号获取信用记录
	 */
	public List<CreditVO> getCreditRecord (int userID) {
		
		//获取po的列表
		List<CreditPO> credit = new ArrayList<CreditPO>();
		credit.addAll(dataFactory.getUserDataHelper().getCreditRecord(userID));
		
		//po转vo
		List<CreditVO> creditList = new ArrayList<CreditVO>();
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
		
		//调用数据层方法返回一个PO列表
		List<CreditPO> credit = new ArrayList<CreditPO>();
		credit.addAll(dataFactory.getUserDataHelper().addCreditItem(creditpo));
		
		//po转vo并返回
		List<CreditVO> creditList = new ArrayList<CreditVO>();
		CreditVO creditvo;
		
		for(CreditPO creditpos: credit){
			creditvo = new CreditVO();
			BeanUtils.copyProperties(creditvo, creditpos);
			creditList.add(creditvo);
		}
		
		return creditList;
	}
	
	/*
	 * 注册会员信息
	 */
	public ResultMessage memberSignIn (MemberVO member) {
		
		MemberPO memberpo = new MemberPO();
		BeanUtils.copyProperties(memberpo, member);
		
		dataFactory.getUserDataHelper().memberSignIn(member);

		return new ResultMessage(true);
	}
	
	/*
	 * 获取营销人员信息列表
	 */
	public List<UserVO> marketerList () {
		
		//获取po列表
		List<UserPO> marketer = new ArrayList<UserPO>();
		marketer.addAll(dataFactory.getUserDataHelper().marketerList());
		
		//po转vo
		List<UserVO> marketerList = new ArrayList<UserVO>();
		UserVO marketervo;
		
		for(UserPO marketerpo: marketer){
			marketervo = new UserVO();
			BeanUtils.copyProperties(marketervo, marketerpo);
			marketerList.add(marketervo);
		}
		
		return marketerList;
	}
	
}
