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
	 * �����û�������ʵ���������û�
	 */
	public List<UserVO> accurateSearch (String username, String realName) {
		
		//��ȡpo���б�
		List<UserPO> user = new List<UserPO>();
		user.addAll(UserDataController.searchUser(username, realName));
		
		//poתvo
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
	 * ���ݳ��к���Ȧ��ȡ�Ƶ�Ա���б�
	 */
	public List<HotelStaffVO> searchHotelStaff(int cityID, int districtID) {
		
		//��ȡpo�б�
		List<HotelStaffPO> hotelstaff = new List<HotelStaffPO>();
		hotelstaff.addAll(UserDataController.searchHotelStaff(cityID, districtID));
		
		//poתvo
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
	 * �����û�ID��ȡ�û���Ϣ
	 */
	public UserVO getUser (int userID) {
		
		UserPO userpo = new UserPO();
		userpo = UserDataController.getUser(userID);
		
		UserVO uservo = new UserVO();
		BeanUtils.copyProperties(uservo, userpo);
		
		return uservo;
	}
	
	/*
	 * ����ɸѡ����������ʽ��������ȡ�û���Ϣ�б�
	 */
	public List<UserVO> search (UserFilterVO filter, 
			UserSort sort, int rank1, int rank2) {
		
		//���Ȼ�ȡ�û���Ϣ�б�
		List<UserPO> user = new List<UserPO>();
		user = UserDataController.userList();
		
		List<UserVO> userlist = new List<UserVO>();
		UserVO uservo;
		
		for(UserPO userpo: user){
			uservo = new UserVO();
			BeanUtils.copyProperties(uservo, userpo);
			userlist.add(uservo);
		}
		
		//����ɸѡ��������ɸѡ
		List<UserVO> filterUser = new List<UserVO>();
		for(UserVO uservos: userList){
			if(uservos.userName.equals(filter.userName)
					&& uservos.realName.equals(filter.realName)
					&& uservos.isMember == filter.isMember){
				filterUser.add(uservos);
			}
		}
		
		//����Ҫ���������
		//�û�����������
		if(sort == UserSort.USERNAMEASCEND){
			Collections.sort(filterUser, new Comparator<UserVO>(){
				@Override
				public int compare(UserVO o1, UserVO o2) {
				return (o1.userName).compareToIgnoreCase(o2.userName);
				}
				});
		}
		//�û�������
		else if(sort == UserSort.USERNAMEDESCEND){
			//����������
			Collections.sort(filterUser, new Comparator<UserVO>(){
				@Override
				public int compare(UserVO o1, UserVO o2) {
				return (o1.userName).compareToIgnoreCase(o2.userName);
				}
				});
			//�ٰ�����ĵ���
			Collections.reverse(filterUser);
		}
		//��ʵ��������
		else if(sort == UserSort.REALNAMEASCEND){
			Collections.sort(filterUser, new Comparator<UserVO>(){
				@Override
				public int compare(UserVO o1, UserVO o2) {
				return (o1.realName).compareToIgnoreCase(o2.realName);
				}
				});
		}
		//��ʵ��������
		else if(sort == UserSort.REALNAMEDESCEND){
			//����������
			Collections.sort(filterUser, new Comparator<UserVO>(){
				@Override
				public int compare(UserVO o1, UserVO o2) {
				return (o1.realName).compareToIgnoreCase(o2.realName);
				}
				});
			//�ٰ�����ĵ���
			Collections.reverse(filterUser);
		}
		
		//����rank1��rank2֮�����Ϣ
		return filterUser.subList(rank1, rank2);
	}
	
	/*
	 * �����û�ID�ͽ��������ó�ֵ
	 */
	public ResultMessage creditRecharge (int userID, double amount) {
		
		return UserDataController.addCredit(userID, amount);
	}
	
	/*
	 * ���ݾƵ�ID��������ӾƵ�
	 */
	public ResultMessage addHotel (int districtID, String hotelName) {
		
		return UserDataController.addHotel(districtID, hotelName);
	}
	
	/*
	 * ���ݾƵ�ID���û�����������ӾƵ�Ա��
	 */
	public ResultMessage addHotelStaff (int hotelID, 
			String username, String password) {
		
		return UserDataController.addHotelStaff(hotelID, username, password);
	}
	
	/*
	 * ͨ���û���Ż�ȡ���ü�¼
	 */
	public List<CreditVO> getCreditRecord (int userID) {
		
		//��ȡpo���б�
		List<CreditPO> credit = new List<CreditPO>();
		credit.addAll(UserDataController.getCreditRecord(userID));
		
		//poתvo
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
	 * ������ü�¼
	 */
	public List<CreditVO> addCreditItem (CreditVO creditItem) {
		
		CreditPO creditpo = new CreditPO();
		BeanUtils.copyProperties(creditpo, creditItem);
		
		return UserDataController.addCreditItem(creditpo);
	}
	
	/*
	 * ע���Ա��Ϣ
	 */
	public ResultMessage memberSignIn (MemberVO member) {
		
		MemberPO memberpo = new MemberPO();
		BeanUtils.copyProperties(memberpo, member);

		return UserDataController.memberSignIn(memberpo);
	}
	
	/*
	 * ��ȡӪ����Ա��Ϣ�б�
	 */
	public List<UserVO> marketerList () {
		
		//��ȡpo�б�
		List<UserPO> marketer = new List<UserPO>();
		user.addAll(UserDataController.marketerList());
		
		//poתvo
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
