package tiquartet.StubDriver.blservice.personal;

import java.util.*;
import tiquartet.ClientModule.blservice.personalblservice.*;
import tiquartet.ClientModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;

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
