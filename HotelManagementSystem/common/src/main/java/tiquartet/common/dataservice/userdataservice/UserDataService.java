package tiquartet.common.dataservice.userdataservice;

public class UserDataService {
	public ResultMessage insert(UserPO user);
	public PersonalPO getUserInfo(long userID);
	public ResultMessage update(PersonalPO personal);
	public ResultMessage insert(PersonalPO personal);
	public CreditPO getCreditBalance(long userID);

}
