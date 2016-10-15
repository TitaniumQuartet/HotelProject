package tiquartet.stub_driver.personal;

public class PersonalBL_driver {
	public void drive(PersonalBLService stub){
		
	}
	public static void main(String[] args){
		new PersonalBL_driver().drive(new PersonalBL_stub());
	}
}
