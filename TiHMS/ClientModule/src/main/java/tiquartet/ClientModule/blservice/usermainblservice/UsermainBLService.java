package tiquartet.ClientModule.blservice.usermainblservice;

import tiquartet.ClientModule.vo.UserVO;
import tiquartet.CommonModule.util.ResultMessage;

public interface UsermainBLService {
	public UserVO login (String username, String password);
	public ResultMessage logout (int userID);
    public ResultMessage signUp(String username,String password);
    public boolean isUnregistered (String username);
    public UserVO currentUser ();
    
}
