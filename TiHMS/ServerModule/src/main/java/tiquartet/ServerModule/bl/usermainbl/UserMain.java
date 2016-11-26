package tiquartet.ServerModule.bl.usermainbl;

import tiquartet.CommonModule.blservice.usermainblservice.UsermainBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.dataservice.userdataservice.UserDataController;
import org.apache.commons.beanutils.BeanUtils; 
import tiquartet.CommonModule.vo.UserVO;
import tiquartet.ServerModule.po.UserPO;

public class UserMain implements UserMainBLService{
	
	/*
	 * �û���¼
	 */
	public UserVO login (String username, String password){
		
		//���ж��û��Ƿ����
		ResultMessage res = UserDataContrller.userExist(username);
		//�û�����
		if(res.result == true){
			//��֤�û����������Ƿ�ƥ��
			ResultMessage match = UserDataController.checkPassword(username, password);
			//ƥ�䷵���û���Ϣ
			if(match.result == true){
				UserPO userpo = UserDataController.getUser(username, password);
				UserVO uservo = new UserVO();
				BeanUtils.copyProperties(uservo, userpo);
				
				return uservo;
			}
			//��ƥ�䷵�ؿ�
			else{
				return null;
			}
		}
		//�����ڷ��ؿ�
		else{
			return null;
		}
		
    }
	
	/*
	 * �û��ǳ�
	 */
	public ResultMessage logout (int userID){
		
		return new ResultMessage(true);
	}
	
	/*
	 * �û�ע��
	 */
    public ResultMessage signUp(String username,String password){
    	
    	UserVO newUser = new UserVO();
    	newUser.password = password;
    	newUser.username = username;
    	
    	//�������ݿ�
    	UserPO userpo = new UserPO();
    	BeanUtils.copyProperties(userpo, newUser);
    	
    	return UserDataController.insert(userpo);
    }
    
    /*
     * �ж��û����Ƿ��Ѵ���
     */
    public boolean isUnregistered (String username){
    	
    	boolean exist = UserDataController.userExist(username).result;
    	
    	return exist;
    }
    
    //�Ȳ������������
    public UserVO currentUser (){
    	UserVO user=this.login("Teki", "12345678");
    	return user;
    }

}
