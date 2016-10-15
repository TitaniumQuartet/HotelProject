package tiquartet.client.blservice.personalblservice;

import java.util.List;

import tiquartet.client.vo.ClientVO;
import tiquartet.client.vo.HotelVO;
import tiquartet.client.vo.PersonalVO;

public interface PersonalBLService {
    public List<ClientVO> getCreditRecord(long userID);
    public ResultMessage memberSignIn(PersonalVO personal);
    public ResultMessage manageInfo(PersonalVO personal);
    public PersonalVO getPersonal(long userID);
    public ResultMessage modifyPersonal(PersonalVO personal);
    public List<HotelVO> getHotelList(long userID);
}
