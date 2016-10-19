package tiquartet.ClientModule.blservice.personalblservice;

import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;

import tiquartet.ClientModule.vo.CreditVO;
import tiquartet.ClientModule.vo.HotelVO;
import tiquartet.ClientModule.vo.PersonalVO;

public interface PersonalBLService {
    public List<CreditVO> getCreditRecord(long userID);
    public ResultMessage memberSignUp(PersonalVO personal);
    public ResultMessage manageInfo(PersonalVO personal);
    public PersonalVO getPersonal(long userID);
    public ResultMessage modifyPersonal(PersonalVO personal);
    public List<HotelVO> getHotelList(long userID);
}
