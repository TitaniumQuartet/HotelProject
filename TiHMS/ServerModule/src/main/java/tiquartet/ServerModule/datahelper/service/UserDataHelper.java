package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;
import tiquartet.ServerModule.po.UserPO;

public interface UserDataHelper {

	/**
	 * 验证用户是否存在
	 * @param 
	 */
	public void userExist (String username);
	
	/**
	 * 验证密码是否正确
	 * @param 
	 */
	public void checkPassword (String username, String password);
	
	/**
	 * 新增用户
	 * @param 
	 */
	public void insert (UserPO user);
	
	/**
	 * @return	从数据文件中读取用户数据
	 */
	public Map<Integer, UserPO> getUser (int userID);
	
	/**
	 * 向数据文件中写入用户数据
	 * @param list
	 */
	public void update (Map<Integer, UserPO> map);
	
	/**
	 *搜索用户
	 * @param 
	 */
	public List<UserPO> searchUser (String username, String realName);
	
	/**
	 * 得到信用记录
	 * @param 
	 */
	public void getCreditBalance (int userID);
	
	/**
	 * 增加信用记录
	 * @param 
	 */
	public void addCredit (int userID, double addition);
}
