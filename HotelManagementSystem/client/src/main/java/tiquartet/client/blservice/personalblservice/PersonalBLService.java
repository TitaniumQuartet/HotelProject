package tiquartet.client.blservice.personalblservice;

import java.util.List;
import tiquartet.common.util.ResultMessage;

import tiquartet.client.vo.CreditVO;
import tiquartet.client.vo.HotelVO;
import tiquartet.client.vo.PersonalVO;

public interface PersonalBLService {
    public List<CreditVO> getCreditRecord(long userID);
    public ResultMessage memberSignUp(PersonalVO personal);
    public ResultMessage manageInfo(PersonalVO personal);
    public PersonalVO getPersonal(long userID);
    public ResultMessage modifyPersonal(PersonalVO personal);
    public List<HotelVO> getHotelList(long userID);
}
