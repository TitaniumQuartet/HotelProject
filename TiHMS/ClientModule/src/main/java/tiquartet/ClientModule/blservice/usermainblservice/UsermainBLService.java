package tiquartet.ClientModule.blservice.usermainblservice;

import tiquartet.ClientModule.vo.UserVO;
import tiquartet.CommonModule.util.ResultMessage;

public interface UsermainBLService {
    public UserVO login(long userID,String password);
    public  ResultMessage logout(long userID);
    public ResultMessage signUp(String username,String password);
    
}
