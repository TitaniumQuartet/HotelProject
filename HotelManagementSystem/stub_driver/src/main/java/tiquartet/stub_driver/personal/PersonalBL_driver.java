package tiquartet.stub_driver.personal;

public class PersonalBL_driver {
	public void drive(PersonalBLService stub){
		List<CreditVO> li = stub.getCreditRecord(0);
		System.out.println(stub.memberSignUp(new PersonalVO()));
		System.out.println(stub.manageInfo(new PersonalVO()));
		PersonalVO pvo = stub.getPersonal(0);
		System.out.println(stub.modifyPersona(new PersonalVO()));
		List<HotelVO> li = stub.getHotelList();
	}
	public static void main(String[] args){
		new PersonalBL_driver().drive(new PersonalBL_stub());
	}
}
