package tiquartet.stub_driver.usermain;

public class UsermainBL_driver {
    public void drive(UsermainService stub){
    	
    }
    public static void main(String[] args){
    	new UsermainBL_driver().drive(new UsermainBL_stub());
    }
}
