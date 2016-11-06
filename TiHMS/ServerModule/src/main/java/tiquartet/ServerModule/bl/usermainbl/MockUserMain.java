package tiquartet.ServerModule.bl.usermainbl;

import tiquartet.CommonModule.vo.UserVO;

public class MockUserMain extends UserMain{
	public  UserVO currentUser (){
		UserVO userVO=new UserVO();
		userVO.userID=0000000001;
		userVO.userName="Teki";
    	return new UserVO();
    }

}
