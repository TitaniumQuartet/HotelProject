package tiquartet.common.dataservice.userdataservice;

import java.util.List;
import tiquartet.common.po.userpo.UserPO;
import tiquartet.common.util.ResultMessage;
import tiquartet.common.po.personalpo.PersonalPO;
import tiquartet.common.po.creditpo.CreditPO;

public interface UserDataService {
	public ResultMessage insert(UserPO user);
	public PersonalPO getUserInfo(long userID);
	public ResultMessage update(PersonalPO personal);
	public ResultMessage insert(PersonalPO personal);
	public CreditPO getCreditBalance(long userID);
	public List<UserPO> searchClient(String username, String realName);

}
