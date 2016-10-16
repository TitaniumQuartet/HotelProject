package tiquartet.stub_driver.blservice.personal;

import java.util.*;
import tiquartet.client.blservice.personalblservice.*;
import tiquartet.client.vo.*;
import tiquartet.common.util.ResultMessage;

public class PersonalBL_stub implements PersonalBLService{
	public List<CreditVO> getCreditRecord(long userID){
		return new ArrayList<CreditVO>();
	}
    public ResultMessage memberSignUp(PersonalVO personal){
    	return ResultMessage.SUCCEED;
    }
    public ResultMessage manageInfo(PersonalVO personal){
    	return ResultMessage.SUCCEED;
    }
    public PersonalVO getPersonal(long userID){
    	return new PersonalVO();
    }
    public ResultMessage modifyPersonal(PersonalVO personal){
    	return ResultMessage.SUCCEED;
    }
    public List<HotelVO> getHotelList(long userID){
    	return new ArrayList<HotelVO>();
    }
}
