/**
 * 管理用户的类。
 * 
 * @author Yolanda151250080
 * 
 */
package tiquartet.ServerModule.bl.manageuserbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tiquartet.CommonModule.blservice.manageuserblservice.ManageUserBLService;
import tiquartet.CommonModule.util.CreditChange;
import tiquartet.CommonModule.util.MemberType;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserSort;
import tiquartet.CommonModule.vo.CreditVO;
import tiquartet.CommonModule.vo.MemberVO;
import tiquartet.CommonModule.vo.UserFilterVO;
import tiquartet.CommonModule.vo.UserVO;
import tiquartet.ServerModule.dataservice.creditdataservice.CreditDataService;
import tiquartet.ServerModule.dataservice.hotelinfodataservice.HotelInfoDataService;
import tiquartet.ServerModule.dataservice.impl.CreditDataImpl;
import tiquartet.ServerModule.dataservice.impl.HotelInfoDataImpl;
import tiquartet.ServerModule.dataservice.impl.UserDataImpl;
import tiquartet.ServerModule.dataservice.userdataservice.UserDataService;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.UserPO;

public class ManageUser implements ManageUserBLService {

	private UserDataService userDataService;
	private CreditDataService creditDataService;
	private HotelInfoDataService hotelInfoDataService;

	public ManageUser() {
		userDataService = UserDataImpl.getInstance();
		creditDataService = CreditDataImpl.getInstance();
		hotelInfoDataService = HotelInfoDataImpl.getInstance();
	}

	/*
	 * 精确搜索获取用户信息列表
	 */
	public UserVO accurateSearch(String username) {

		// 获得po
		UserPO userPO = userDataService.accurateSearch(username);
		if (userPO == null)
			return null;
		// po转vo
		UserVO userVO = userPO.getVO();

		return userVO;
	}

	/*
	 * 获取酒店员工信息列表
	 */
	public List<UserVO> searchHotelStaff(int cityID, int districtID) {

		List<UserPO> userPOs = userDataService.hotelStaffList(cityID,
				districtID);
		List<UserVO> userVOs = new ArrayList<UserVO>();
		UserVO userVO;
		for (UserPO userPO : userPOs) {
			userVO = userPO.getVO();
			userVOs.add(userVO);
		}

		return userVOs;
	}

	/*
	 * 获得某个用户的信息
	 */
	public UserVO getUser(int userID) {

		UserPO userPO = userDataService.getUser(userID);
		UserVO userVO = userPO.getVO();

		return userVO;
	}

	/*
	 * 按一定条件筛选用户列表
	 */
	public List<UserVO> search(UserFilterVO filter, UserSort sort, int rank1,
			int rank2) {

		// 获取po列表
		List<UserPO> userPOs = userDataService.searchUser(filter.username,
				filter.realName, filter.userType);

		// po列表转vo列表
		List<UserVO> userVOs = new ArrayList<UserVO>();
		UserVO userVO;
		for (UserPO userPO : userPOs) {
			userVO = userPO.getVO();
			userVOs.add(userVO);
		}

		// 根据筛选条件进行筛选
		List<UserVO> filterUser = new ArrayList<UserVO>();
		for (UserVO userVO2 : userVOs) {
			if (userVO2.memberType.equals(filter.memberType)) {
				filterUser.add(userVO2);
			}
		}

		// 对列表进行排序

		// 用户名升序
		if (sort == UserSort.USERNAMEASCEND) {
			Collections.sort(filterUser, new Comparator<UserVO>() {
				@Override
				public int compare(UserVO o1, UserVO o2) {
					return (o1.userName).compareToIgnoreCase(o2.userName);
				}
			});
		}

		// 用户名降序
		else if (sort == UserSort.USERNAMEDESCEND) {
			// 先升序排列
			Collections.sort(filterUser, new Comparator<UserVO>() {
				@Override
				public int compare(UserVO o1, UserVO o2) {
					return (o1.userName).compareToIgnoreCase(o2.userName);
				}
			});
			// 对升序列表进行倒置
			Collections.reverse(filterUser);
		}

		// 真实姓名升序
		else if (sort == UserSort.REALNAMEASCEND) {
			Collections.sort(filterUser, new Comparator<UserVO>() {
				@Override
				public int compare(UserVO o1, UserVO o2) {
					return (o1.realName).compareToIgnoreCase(o2.realName);
				}
			});
		}

		// 真实姓名降序
		else if (sort == UserSort.REALNAMEDESCEND) {
			// 先升序
			Collections.sort(filterUser, new Comparator<UserVO>() {
				@Override
				public int compare(UserVO o1, UserVO o2) {
					return (o1.realName).compareToIgnoreCase(o2.realName);
				}
			});
			// 倒置升序列表
			Collections.reverse(filterUser);
		}

		// 返回rank1到rank2之间的列表
		if (rank2 - 1 > filterUser.size()) {
			rank2 = filterUser.size() + 1;
		}
		for (int i = 0; i < filterUser.size(); i++) {
			if (i < rank1 - 1 || i > rank2 - 1) {
				filterUser.remove(i);
				filterUser.add(i, null);
			}
		}

		return filterUser;
	}

	/*
	 * 信用充值
	 */
	public ResultMessage creditRecharge(int userID, double amount) {

		ResultMessage result = userDataService.addCredit(userID, amount);
		UserPO userPO = userDataService.getUser(userID);
		CreditPO creditPO = new CreditPO(CreditChange.用户线下充值信用值, amount,
				userPO.getcredit(), -1, -1, userID, null);
		creditDataService.insert(creditPO);
		return result;
	}

	/*
	 * 添加酒店
	 */
	public ResultMessage addHotel(int districtID, String hotelName) {

		// 新建一个HotelInfoPO
		HotelInfoPO hotelInfoPO = new HotelInfoPO();
		hotelInfoPO.setcircleId(districtID);
		hotelInfoPO.sethotelName(hotelName);

		ResultMessage result = hotelInfoDataService.insert(hotelInfoPO);

		return result;
	}

	/*
	 * 添加酒店员工
	 */
	public ResultMessage addHotelStaff(int hotelID, String username,
			String password) {

		// 创建一个酒店员工的po对象
		UserPO hotelStaff = new UserPO();
		hotelStaff.setuserName(username);
		hotelStaff.setpassword(password);
		hotelStaff.sethotelId(hotelID);

		// 调用数据层插入用户的方法
		ResultMessage result = userDataService.insert(hotelStaff);

		return result;
	}

	/*
	 * 获取用户的信用记录
	 */
	public List<CreditVO> getCreditRecord(int userID) {

		// 获取po列表
		List<CreditPO> creditPOs = creditDataService.getRecord(userID);
		// po列表转vo列表
		List<CreditVO> creditVOs = new ArrayList<CreditVO>();
		CreditVO creditVO;
		for (CreditPO creditPO : creditPOs) {
			creditVO = creditPO.getVO();
			creditVOs.add(creditVO);
		}

		return creditVOs;
	}

	/*
	 * 添加信用记录
	 */
	public ResultMessage addCreditItem(CreditVO creditItem) {

		// vo转po
		CreditPO creditPO = new CreditPO(creditItem);

		ResultMessage result = creditDataService.insert(creditPO);

		return result;
	}

	/*
	 * 注册会员
	 */
	public ResultMessage memberSignIn(MemberVO member) {

		UserPO memberPO = userDataService.getUser(member.userID);
		memberPO.setmemberRank(member.memberRank);
		memberPO.setisMember(true);
		memberPO.setmemberType(member.memberType);
		// 判断用户类型
		if (member.memberType == MemberType.个人会员) {
			// 会员类型为个人会员
			memberPO.setbirthday(member.birthday);
		} else {
			// 会员类型为企业会员
			memberPO.setcompany(member.companyName);
		}
		ResultMessage result = userDataService.update(memberPO);

		return result;
	}

	/*
	 * 获取网站营销人员列表
	 */
	public List<UserVO> marketerList() {

		// 获取po列表
		List<UserPO> userPOs = userDataService.marketerList();
		// po列表转vo列表
		List<UserVO> userVOs = new ArrayList<UserVO>();
		UserVO userVO;
		for (UserPO userPO : userPOs) {
			userVO = userPO.getVO();
			userVOs.add(userVO);
		}

		return userVOs;
	}

	/*
	 * 添加用户
	 */
	public ResultMessage addUser(UserVO user) {

		UserPO userPO = new UserPO(user);

		ResultMessage result = userDataService.insert(userPO);

		return result;
	}

	/*
	 * 更新用户信息
	 */
	public ResultMessage update(UserVO user) {

		// vo转po
		UserPO userPO = new UserPO(user);
		// 更新信息
		ResultMessage result = userDataService.update(userPO);

		return result;
	}

}
