package tiquartet.ClientModule.ui.usermainui;

import tiquartet.CommonModule.blservice.usermainblservice.UsermainBLService;
import tiquartet.CommonModule.vo.*;
import tiquartet.ServerModule.bl.usermainbl.*;

public class UsermainBL_driver {
    public void drive(UsermainBLService stub){
    	
    	UserVO uservo = stub.login("lil_one","aintitsimple");
    	System.out.println(stub.logout(0));
    	System.out.println(stub.signUp("0","0"));
    }
    public static void main(String[] args){
    	new UsermainBL_driver().drive(new UsermainBL_stub());
    }
}
