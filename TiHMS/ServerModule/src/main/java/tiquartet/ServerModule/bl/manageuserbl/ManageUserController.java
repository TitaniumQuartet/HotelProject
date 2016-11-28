/**
 * @author Yolanda151250080
 */
package tiquartet.ServerModule.bl.manageuserbl;

import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserSort;
import tiquartet.CommonModule.vo.CreditVO;
import tiquartet.CommonModule.vo.MemberVO;
import tiquartet.CommonModule.vo.UserFilterVO;
import tiquartet.CommonModule.vo.UserVO;

public class ManageUserController {
	
	static ManageUser manageuser = new ManageUser();
	
	/*
	 * �����û�������ʵ���������û�
	 */
	public static List<UserVO> accurateSearch (String username, String realName) {
		
		return manageuser.accurateSearch(username, realName);
	}
	
	/*
	 * ���ݳ��к���Ȧ��ȡ�Ƶ�Ա���б�
	 */
	public static List<UserVO> searchHotelStaff(int cityID, int districtID) {
		
		return manageuser.searchHotelStaff(cityID, districtID);
	}
	
	/*
	 * �����û�ID��ȡ�û���Ϣ
	 */
	public static UserVO getUser (int userID) {
		
		return manageuser.getUser(userID);
	}
	
	/*
	 * ����ɸѡ����������ʽ��������ȡ�û���Ϣ�б�
	 */
	public static List<UserVO> search (UserFilterVO filter, 
			UserSort sort, int rank1, int rank2) {
		
		return manageuser.search(filter, sort, rank1, rank2);
	}
	
	/*
	 * �����û�ID�ͽ��������ó�ֵ
	 */
	public static ResultMessage creditRecharge (int userID, double amount) {
		
		return manageuser.creditRecharge(userID, amount);
	}
	
	/*
	 * ���ݾƵ�ID��������ӾƵ�
	 */
	public static ResultMessage addHotel (int districtID, String hotelName) {
		
		return manageuser.addHotel(districtID, hotelName);
	}
	
	/*
	 * ���ݾƵ�ID���û�����������ӾƵ�Ա��
	 */
	public static ResultMessage addHotelStaff (int hotelID, 
			String username, String password) {
		
		return manageuser.addHotelStaff(hotelID, username, password);
	}
	
	/*
	 * ͨ���û���Ż�ȡ���ü�¼
	 */
	public static List<CreditVO> getCreditRecord (int userID) {
		
		return manageuser.getCreditRecord(userID);
	}
	
	/*
	 * ������ü�¼
	 */
	public static List<CreditVO> addCreditItem (CreditVO creditItem) {
		
		return manageuser.addCreditItem(creditItem);
	}
	
	/*
	 * ע���Ա��Ϣ
	 */
	public static ResultMessage memberSignIn (MemberVO member) {
		
		return manageuser.memberSignIn(member);
	}
	
	/*
	 * ��ȡӪ����Ա��Ϣ�б�
	 */
	public static List<UserVO> marketerList () {
		
		return manageuser.marketerList();
	}
	
}