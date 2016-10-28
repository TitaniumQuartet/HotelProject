package tiquartet.ClientModule.ui.personalui;

import java.util.*;
import tiquartet.ClientModule.blservice.personalblservice.*;
import tiquartet.ClientModule.vo.*;
import tiquartet.ClientModule.bl.personalbl.*;

public class PersonalBL_driver {
	public void drive(PersonalBLService stub){
		List<CreditVO> creditRecord = stub.getCreditRecord(0);
		System.out.println(stub.memberSignUp(new PersonalVO()));
		System.out.println(stub.manageInfo(new PersonalVO()));
		PersonalVO pvo = stub.getPersonal(0);
		System.out.println(stub.modifyPersonal(new PersonalVO()));
		List<HotelVO> hotelList = stub.getHotelList(3242);
	}
	public static void main(String[] args){
		new PersonalBL_driver().drive(new PersonalBL_stub());
	}
}