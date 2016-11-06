package tiquartet.CommonModule.blservice.usermainblservice;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;

public interface UsermainBLService {
	public UserVO login (String username, String password);
	public ResultMessage logout (int userID);
    public ResultMessage signUp(String username,String password);
    public boolean isUnregistered (String username);
    public UserVO currentUser ();
    
}
