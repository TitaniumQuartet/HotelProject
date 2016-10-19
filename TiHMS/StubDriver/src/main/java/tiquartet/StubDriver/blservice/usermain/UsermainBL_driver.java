package tiquartet.StubDriver.blservice.usermain;

import tiquartet.ClientModule.blservice.usermainblservice.*;
import tiquartet.ClientModule.vo.*;

public class UsermainBL_driver {
    public void drive(UsermainBLService stub){
    	
    	UserVO uservo = stub.login(0,"0");
    	System.out.println(stub.logout(0));
    	System.out.println(stub.signUp("0","0"));
    }
    public static void main(String[] args){
    	new UsermainBL_driver().drive(new UsermainBL_stub());
    }
}
