package tiquartet.stub_driver.blservice.personal;

public class PersonalBL_stub implements PersonalBLService{
	public List<CreditVO> getCreditRecord(long userID){
		return new list<CreditVO>;
	}
    public ResultMessage memberSignUp(PersonalVO personal){
    	return ResultMessage.SUCCEED;
    }
    public ResultMessage manageInfo(PersonalVO personal){
    	return ResultMessage.SUCCEED;
    }
    public PersonalVO getPersonal(long userID){
    	return new PersonalVO;
    }
    public ResultMessage modifyPersonal(PersonalVO personal){
    	return ResultMessage.SUCCEED;
    }
    public List<HotelVO> getHotelList(long userID){
    	return new List<HotelVO>;
    }
}
