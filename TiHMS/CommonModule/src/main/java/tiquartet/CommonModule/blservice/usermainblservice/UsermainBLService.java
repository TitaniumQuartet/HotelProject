package tiquartet.CommonModule.blservice.usermainblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;

public interface UsermainBLService extends Remote{
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage login (String username, String password) throws RemoteException;
	/**
	 * 登出
	 * @param userID
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage logout (int userID) throws RemoteException;

    /**
     * 验证是否存在此用户名
     * @param username
     * @return
     * @throws RemoteException
     */
    public boolean isUnregistered (String username) throws RemoteException;
    
    
}
