package tiquartet.CommonModule.blservice.usermainblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;

public interface UsermainBLService extends Remote{
	public UserVO login (String username, String password) throws RemoteException;
	public ResultMessage logout (int userID) throws RemoteException;
    public ResultMessage signUp(String username,String password) throws RemoteException;
    public boolean isUnregistered (String username) throws RemoteException;
    
    
}
