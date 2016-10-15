package tiquartet.client.blservice.usermainblservice;

public interface UsermainBLService {
    public UserPO login(long userID,String password);
    public  ResultMessage logout(long userID);
    public ResultMessage signUp(long userID,String password);
    
}
