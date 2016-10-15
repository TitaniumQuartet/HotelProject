package tiquartet.stub_driver.usermain;



public class UsermainBL_stub implements UsermainBLService{
	 public UserVO login(long userID,String password){
		 return new UserVO;
	 }
	    public  ResultMessage logout(long userID){
	    	return ResultMessage.SUCCEED;
	    }
	    public ResultMessage signUp(String username,String password){
	    	return ResultMessage.SUCCEED;
	    }
}
