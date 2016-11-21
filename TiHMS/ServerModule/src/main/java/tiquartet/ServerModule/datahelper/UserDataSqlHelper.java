package tiquartet.ServerModule.datahelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tiquartet.ServerModule.datahelper.service.UserDataHelper;
import tiquartet.ServerModule.po.UserPO;

public class UserDataSqlHelper implements UserDataHelper{

	
	/**
	 * 验证用户是否存在
	 * @param 
	 */
	public void userExist (String username){
		
	}
	
	/**
	 * 验证密码是否正确
	 * @param 
	 */
	public void checkPassword (String username, String password){
		
	}
	
	/**
	 * 新增用户
	 * @param 
	 */
	public void insert (UserPO user){
		
	}
	
	/**
	 * @return	从数据文件中读取用户数据
	 */
	public Map<Integer, UserPO> getUser(int userID){
		Map<Integer, UserPO> map = new HashMap<Integer, UserPO>();
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 向数据文件中写入用户数据
	 * @param list
	 */
	public void update (Map<Integer, UserPO> map){
		
	}
	
	/**
	 *搜索用户
	 * @param 
	 */
	public List<UserPO> searchUser (String username, String realName){
		return new ArrayList<UserPO>();

	}
	
	/**
	 * 得到信用记录
	 * @param 
	 */
	public void getCreditBalance (int userID){
		
	}
	
	/**
	 * 增加信用记录
	 * @param 
	 */
	public void addCredit (int userID, double addition){
		
	}
	
}
