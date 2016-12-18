package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tiquartet.CommonModule.util.CreditChange;
import tiquartet.CommonModule.util.MemberType;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserSort;
import tiquartet.CommonModule.util.UserType;
import tiquartet.CommonModule.vo.CreditVO;
import tiquartet.CommonModule.vo.MemberVO;
import tiquartet.CommonModule.vo.UserFilterVO;
import tiquartet.CommonModule.vo.UserVO;
import tiquartet.ServerModule.bl.manageuserbl.ManageUser;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.UserPO;

public class ManageUserTest {
	
	private ManageUser user;
	
	@Test
	public void testaccurateSearch(){
		user = new ManageUser();
		
		UserVO userVO = user.accurateSearch("XueRuihahaha");
		UserVO user = new UserPO(1, "XueRuihahaha", "123456", UserType.网站营销人员, "XueRui", 0, "1998-02-14", 0, false, null, -1, false).getVO();
		
		assertEquals(userVO.userName, user.userName);
		assertEquals(userVO.password, user.password);
	}
	
	@Test
	public void testsearchHotelStaff(){
		user = new ManageUser();
		
		List<UserVO> staffs = user.searchHotelStaff(0001, 01);
		
		List<UserVO> userVOs = new ArrayList<UserVO>();
		UserPO staff = new UserPO(3, "Yolanda", "123123", UserType.酒店工作人员, "Yolanda", 0, null, 0, false, null, 000101032, true);
		userVOs.add(staff.getVO());
		
		for(int i = 0; i < staffs.size(); i++){
			assertEquals(userVOs.get(i).hotelID, staffs.get(i).hotelID);
			assertEquals(userVOs.get(i).userName, staffs.get(i).userName);
			assertEquals(userVOs.get(i).password, staffs.get(i).password);
		}
	}
	
	@Test
	public void testgetUser(){
		user = new ManageUser();
		
		UserPO userPO = new UserPO(2, "shisugara", "654321", UserType.客户, "Tang Yufen", 0, "1998-01-25", 0, false, null, -1, true);
		UserVO userVO = userPO.getVO();
		
		UserVO userVO2 = user.getUser(2);
		assertEquals(userVO.realName, userVO2.realName);
	}
	
	@Test
	public void testsearch(){
		user = new ManageUser();
		UserFilterVO userFilterVO = new UserFilterVO("shisugara", "Tang Yufen", UserType.客户, MemberType.非会员);
		
		List<UserVO> userVOs = user.search(userFilterVO, UserSort.USERNAMEASCEND , 0, 10);
		
		UserPO userPO = new UserPO(2, "shisugara", "654321", UserType.客户, "Tang Yufen", 0, "1998-01-25", 0, false, null, -1, true);
		UserVO userVO = userPO.getVO();
		List<UserVO> users = new ArrayList<UserVO>();
		users.add(userVO);
		
		for(int i = 0; i < userVOs.size(); i++){
			assertEquals(users.get(i).birthday, userVOs.get(i).birthday);
			assertEquals(users.get(i).company, userVOs.get(i).company);
			assertEquals(users.get(i).hotelID, userVOs.get(i).hotelID);
			assertEquals(users.get(i).login, userVOs.get(i).login);
			assertEquals(users.get(i).memberLevel, userVOs.get(i).memberLevel);
			assertEquals(users.get(i).memberType, userVOs.get(i).memberType);
			assertEquals(users.get(i).password, userVOs.get(i).password);
			assertEquals(users.get(i).realName, userVOs.get(i).realName);
			assertEquals(users.get(i).userID, userVOs.get(i).realName);
			assertEquals(users.get(i).userName, userVOs.get(i).userName);
			assertEquals(users.get(i).userType, userVOs.get(i).userType);
		}
		
		
	}
	
	@Test
	public void testcreditRecharge(){
		user = new ManageUser();
		
		ResultMessage resultMessage = user.creditRecharge(2, 5);
		assertEquals(new ResultMessage(true).result, resultMessage.result);
	}
	
	@Test
	public void testaddHotel(){
		user = new ManageUser();
		
		ResultMessage resultMessage = user.addHotel(01, "Titanium Hotel");
		assertEquals(new ResultMessage(true).result, resultMessage.result);
	}
	
	@Test
	public void testaddhotelStaff(){
		user = new ManageUser();
		
		ResultMessage resultMessage = user.addHotelStaff(000101032, "Yolanda", "123123");
		assertEquals(new ResultMessage(true).result, resultMessage.result);
	}
	
	@Test
	public void testgetCreditRecord(){
		user = new ManageUser();
		List<CreditVO> creditVOs = user.getCreditRecord(2);
		
		CreditPO creditPO = new CreditPO(CreditChange.客户较晚撤销订单时扣除信用值, 10, 90, 1, 1, 2);
		List<CreditVO> credits = new ArrayList<CreditVO>();
		credits.add(creditPO.getVO());
		
		for(int i = 0; i < creditVOs.size(); i++){
			assertEquals(credits.get(i).changeType, creditVOs.get(i).changeType);
			assertEquals(credits.get(i).creditRecordID, creditVOs.get(i).creditRecordID);
			assertEquals(credits.get(i).orderID, creditVOs.get(i).orderID);
			assertEquals(credits.get(i).userID, creditVOs.get(i).userID);
		}
	}
	
	@Test
	public void testaddCreditItem(){
		user = new ManageUser();
		CreditPO creditPO = new CreditPO(CreditChange.客户较晚撤销订单时扣除信用值, 10, 90, 1, 1, 2);
		CreditVO creditVO = creditPO.getVO();
		
		ResultMessage resultMessage = user.addCreditItem(creditVO);
		assertEquals(new ResultMessage(true).result, resultMessage.result);
	}
	
	@Test
	public void testmemberSignIn(){
		user = new ManageUser();
		MemberVO memberVO = new MemberVO();
		memberVO.memberRank = 1;
		ResultMessage resultMessage = user.memberSignIn(memberVO);
		assertEquals(new ResultMessage(true).result, resultMessage.result);
	}
	
	@Test
	public void testmarketerList(){
		user = new ManageUser();
		List<UserVO> userVOs = user.marketerList();
		
		UserPO userPO = new UserPO(1, "XueRuihahaha", "123456", UserType.网站营销人员, "XueRui", 0, "1998-02-14", 0, false, null, -1, false);
		List<UserVO> users = new ArrayList<UserVO>();
		users.add(userPO.getVO());
		for(int i = 0; i < userVOs.size(); i++){
			assertEquals(users.get(i).birthday, userVOs.get(i).birthday);
			assertEquals(users.get(i).company, userVOs.get(i).company);
			assertEquals(users.get(i).hotelID, userVOs.get(i).hotelID);
			assertEquals(users.get(i).login, userVOs.get(i).login);
			assertEquals(users.get(i).memberLevel, userVOs.get(i).memberLevel);
			assertEquals(users.get(i).memberType, userVOs.get(i).memberType);
			assertEquals(users.get(i).password, userVOs.get(i).password);
			assertEquals(users.get(i).realName, userVOs.get(i).realName);
			assertEquals(users.get(i).userID, userVOs.get(i).realName);
			assertEquals(users.get(i).userName, userVOs.get(i).userName);
			assertEquals(users.get(i).userType, userVOs.get(i).userType);
		}
	}
	
	@Test 
	public void testaddUser1(){
		user = new ManageUser();
		//添加营销人员
		UserPO userPO = new UserPO(1, "XueRuihahaha", "123456", UserType.网站营销人员, "Xue Rui", 0, "1998-02-14", 0, false, null, -1, false);
		UserVO userVO = userPO.getVO();
		ResultMessage resultMessage = user.addUser(userVO);
		assertEquals(new ResultMessage(true).result, resultMessage.result);
	}
	
	@Test
	public void testaddUser2(){
		user = new ManageUser();
		//添加客户
		UserPO userPO = new UserPO(2, "shisugara", "654321", UserType.客户, "Tang Yufen", 0, "1998-01-25", 0, false, null, -1, true);
		UserVO userVO = userPO.getVO();
		ResultMessage resultMessage = user.addUser(userVO);
		assertEquals(new ResultMessage(true).result, resultMessage.result);
	}
	
	@Test
	public void testupdate(){
		user = new ManageUser();
		UserPO userPO = new UserPO(2, "shisugara", "654321", UserType.客户, "Tang Yufen", 100, "1998-01-25", 0, false, null, -1, true);
		UserVO userVO = userPO.getVO();
		ResultMessage resultMessage = user.update(userVO);
		assertEquals(new ResultMessage(true).result, resultMessage.result);
	}
}
