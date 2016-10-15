package tiquartet.client.blservice.personalblservice;

public interface PersonalBLService {
    public list<CreditVO> getCreditRecord(long userID);
    public ResultMessage memberSignIn(PersonalVO personal);
    public ResultMessage manageInfo(PersonalVO personal);
    public PersonalVO getPersonal(long userID);
    public ResultMessage modifyPersonal(PersonalVO personal);
    public List<HotelVO> getHotelList(long userID);
}
