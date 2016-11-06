package tiquartet.ServerModule.dataservice.userdataservice;

import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.PersonalPO;
import tiquartet.ServerModule.po.UserPO;

public interface UserDataService {
	public ResultMessage insert(UserPO user);
	public PersonalPO getUserInfo(long userID);
	public ResultMessage update(PersonalPO personal);
	public ResultMessage insert(PersonalPO personal);
	public CreditPO getCreditBalance(long userID);
	public List<UserPO> searchClient(String username, String realName);

}
