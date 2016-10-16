package tiquartet.common.dataservice.userdataservice;

import java.util.List;
public interface UserDataService {
	public ResultMessage insert(UserPO user);
	public PersonalPO getUserInfo(long userID);
	public ResultMessage update(PersonalPO personal);
	public ResultMessage insert(PersonalPO personal);
	public CreditPO getCreditBalance(long userID);
	public List<UserPO> searchClient(String username, String realName);

}
