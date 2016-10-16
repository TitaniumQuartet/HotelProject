package manageUserstub_driver;

public class ManageUserbl_drive {

	public void drive(ManageUserbl stub){
		
		UserPO userpo = stub.searcUser();
		System.out.println(userpo);
		
		HotelStaffPO staffpo = stub.searchHotelStaff();
		System.out.println(staffpo);
		
		staffpo = stub.searchStaff();
		System.out.println(staffpo);
		
		userpo = stub.getUserList();
		System.out.println(userpo);
		
		System.out.println(stub.credit());
		
		System.out.println(stub.addHotel());
		
		System.out.println(stub.add());
	}
	
	public static void main(String[] args){
		
		new ManageUserbl_drive().drive(new ManageUserbl_stub());
	}
}
