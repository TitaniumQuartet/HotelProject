package tiquartet.client.blservice.usermainblservice;

import tiquartet.client.vo.UserVO;

public interface UsermainBLService {
    public UserVO login(long userID,String password);
    public  ResultMessage logout(long userID);
    public ResultMessage signUp(String username,String password);
    
}