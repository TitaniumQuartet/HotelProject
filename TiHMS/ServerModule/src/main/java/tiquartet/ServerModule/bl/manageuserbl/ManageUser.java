/**
 * @author Yolanda151250080
 */
package tiquartet.ServerModule.bl.manageuserbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tiquartet.CommonModule.blservice.manageuserblservice.ManageUserBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserSort;
import tiquartet.CommonModule.vo.CreditVO;
import tiquartet.CommonModule.vo.MemberVO;
import tiquartet.CommonModule.vo.UserFilterVO;
import tiquartet.CommonModule.vo.UserVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.UserPO;

public class ManageUser implements ManageUserBLService {
	
	static DataFactory dataFactory=new DataFactory();

	/*
	 * �����û�������ʵ���������û�
	 */
	public List<UserVO> accurateSearch (String username, String realName) {
		
		//��ȡpo���б�
		List<UserPO> user = new ArrayList<UserPO>();
		user.addAll(dataFactory.getUserDataHelper().searchUser(username, realName));
		
		//poתvo
		List<UserVO> userList = new ArrayList<UserVO>();
		UserVO uservo;
		
		for(UserPO userpo: user){
			uservo = new UserVO();
			//BeanUtils.copyProperties(uservo, userpo);
			userList.add(uservo);
		}
		
		return userList;
	}
	
	/*
	 * ���ݳ��к���Ȧ��ȡ�Ƶ�Ա���б�
	 */
	public List<UserVO> searchHotelStaff(int cityID, int districtID) {
		
		//��ȡpo�б�
		//List<HotelStaffPO> hotelstaff = new ArrayList<HotelStaffPO>();
		//hotelstaff.addAll(dataFactory.getUserDataHelper().searchHotelStaff(cityID, districtID));
		
		//poתvo
		List<UserVO> hotelstaffList = new ArrayList<UserVO>();
		UserVO hotelstaffvo;
		
		//for(HotelStaffPO hotelstaffpo: hotelstaff){
			hotelstaffvo = new UserVO();
			//BeanUtils.copyProperties(hotelstaffvo, hotelstaffpo);
			hotelstaffList.add(hotelstaffvo);
		//}
		
		return hotelstaffList;
	}
	
	/*
	 * �����û�ID��ȡ�û���Ϣ
	 */
	public UserVO getUser (int userID) {
		
		UserPO userpo = new UserPO();
		//userpo = dataFactory.getUserDataHelper().getUser(userID);
		
		UserVO uservo = new UserVO();
		//BeanUtils.copyProperties(uservo, userpo);
		
		return uservo;
	}
	
	/*
	 * ����ɸѡ����������ʽ��������ȡ�û���Ϣ�б�
	 */
	public List<UserVO> search (UserFilterVO filter, 
			UserSort sort, int rank1, int rank2) {
		
		//���Ȼ�ȡ�û���Ϣ�б�
		List<UserPO> user = new ArrayList<UserPO>();
		//user = dataFactory.getUserDataHelper().userList();
		
		List<UserVO> userlist = new ArrayList<UserVO>();
		UserVO uservo;
		
		for(UserPO userpo: user){
			uservo = new UserVO();
			//BeanUtils.copyProperties(uservo, userpo);
			userlist.add(uservo);
		}
		
		//����ɸѡ��������ɸѡ
		List<UserVO> filterUser = new ArrayList<UserVO>();
		/*for(UserVO uservos: userlist){
			if(uservos.userName.equals(filter)
					&& uservos.realName.equals(filter.realName)
					&& uservos.isMember == filter.isMember){
				filterUser.add(uservos);
			}
		}*/
		
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
		
		dataFactory.getUserDataHelper().addCredit(userID, amount);
		
		return new ResultMessage(true);
	}
	
	/*
	 * ���ݾƵ�ID��������ӾƵ�
	 */
	public ResultMessage addHotel (int districtID, String hotelName) {
		
		//dataFactory.getUserDataHelper().addHotel(districtID, hotelName);
		
		return new ResultMessage(true);
	}
	
	/*
	 * ���ݾƵ�ID���û�����������ӾƵ�Ա��
	 */
	public ResultMessage addHotelStaff (int hotelID, 
			String username, String password) {
		
		//dataFactory.getUserDataHelper().addHotelStaff(hotelID, username, password);
		
		return new ResultMessage(true);
	}
	
	/*
	 * ͨ���û���Ż�ȡ���ü�¼
	 */
	public List<CreditVO> getCreditRecord (int userID) {
		
		//��ȡpo���б�
		List<CreditPO> credit = new ArrayList<CreditPO>();
		//credit.addAll(dataFactory.getUserDataHelper().getCreditRecord(userID));
		
		//poתvo
		List<CreditVO> creditList = new ArrayList<CreditVO>();
		CreditVO creditvo;
		
		for(CreditPO creditpo: credit){
			creditvo = new CreditVO();
			//BeanUtils.copyProperties(creditvo, creditpo);
			creditList.add(creditvo);
		}
		
		return creditList;
	}
	
	/*
	 * ������ü�¼
	 */
	public List<CreditVO> addCreditItem (CreditVO creditItem) {
		
		CreditPO creditpo = new CreditPO();
		//BeanUtils.copyProperties(creditpo, creditItem);
		
		//�������ݲ㷽������һ��PO�б�
		List<CreditPO> credit = new ArrayList<CreditPO>();
		//credit.addAll(dataFactory.getUserDataHelper().addCreditItem(creditpo));
		
		//poתvo������
		List<CreditVO> creditList = new ArrayList<CreditVO>();
		CreditVO creditvo;
		
		for(CreditPO creditpos: credit){
			creditvo = new CreditVO();
			//BeanUtils.copyProperties(creditvo, creditpos);
			creditList.add(creditvo);
		}
		
		return creditList;
	}
	
	/*
	 * ע���Ա��Ϣ
	 */
	public ResultMessage memberSignIn (MemberVO member) {
		
		//MemberPO memberpo = new MemberPO();
		//BeanUtils.copyProperties(memberpo, member);
		
		//dataFactory.getUserDataHelper().memberSignIn(member);

		return new ResultMessage(true);
	}
	
	/*
	 * ��ȡӪ����Ա��Ϣ�б�
	 */
	public List<UserVO> marketerList () {
		
		//��ȡpo�б�
		List<UserPO> marketer = new ArrayList<UserPO>();
		//marketer.addAll(dataFactory.getUserDataHelper().marketerList());
		
		//poתvo
		List<UserVO> marketerList = new ArrayList<UserVO>();
		UserVO marketervo;
		
		for(UserPO marketerpo: marketer){
			marketervo = new UserVO();
			//BeanUtils.copyProperties(marketervo, marketerpo);
			marketerList.add(marketervo);
		}
		
		return marketerList;
	}

	@Override
	public ResultMessage addUser(UserVO user) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
