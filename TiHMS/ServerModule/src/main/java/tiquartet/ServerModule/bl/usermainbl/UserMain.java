package tiquartet.ServerModule.bl.usermainbl;

import tiquartet.CommonModule.blservice.usermainblservice.UsermainBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.po.UserPO;

public class UserMain implements UsermainBLService{
	
	static DataFactory dataFactory=new DataFactory();
	
	/*
	 * �û���¼
	 */
	public UserVO login (String username, String password){
		
		//���ж��û��Ƿ����
		ResultMessage res = new ResultMessage(true); 
		dataFactory.getUserDataHelper().userExist(username);
		
		//�û�����
		if(res.result == true){
			//��֤�û����������Ƿ�ƥ��
			ResultMessage match = new ResultMessage(true);
			dataFactory.getUserDataHelper().checkPassword(username, password);
			
			//ƥ�䷵���û���Ϣ
			if(match.result == true){
				//UserPO userpo = dataFactory.getUserDataHelper().getUser(username);
				UserVO uservo = new UserVO();
				//BeanUtils.copyProperties(uservo, userpo);
				
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
    	newUser.userName = username;
    	
    	//�������ݿ�
    	UserPO userpo = new UserPO();
    	//BeanUtils.copyProperties(userpo, newUser);
    	
    	dataFactory.getUserDataHelper().insert(userpo);
    	
    	return new ResultMessage(true);
    }
    
    /*
     * �ж��û����Ƿ��Ѵ���
     */
    public boolean isUnregistered (String username){
    	
    	dataFactory.getUserDataHelper().userExist(username);
    	
    	return true;
    }
    
    //�Ȳ������������
    public UserVO currentUser (){
    	UserVO user=this.login("Teki", "12345678");
    	return user;
    }

}
