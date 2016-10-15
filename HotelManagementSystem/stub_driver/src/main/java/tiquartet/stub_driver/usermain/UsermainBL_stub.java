package tiquartet.stub_driver.usermain;



public class UsermainBL_stub implements UsermainBLService{
	 public UserPO login(long userID,String password){
		 return new UserPO;
	 }
	    public  ResultMessage logout(long userID){
	    	return ResultMessage.SUCCEED;
	    }
	    public ResultMessage signUp(long userID,String password){
	    	return ResultMessage.SUCCEED;
	    }
}
