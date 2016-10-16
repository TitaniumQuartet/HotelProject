package manageUserstub_driver;

public class ManageUserbl_drive {

	public void drive(ManageUserbl stub){
		
		UserPO userpo = stub.searchUser();
		System.out.println(userpo.user);
		
		HotelStaffPO staffpo = stub.searchHotelStaff();
		System.out.println(staffpo.staff);
		
		staffpo = stub.searchStaff();
		System.out.println(staffpo.staff);
		
		userpo = stub.getUserList();
		System.out.println(userpo.list);
		
		System.out.println(stub.credit().credit);
		
		System.out.println(stub.addHotel().hotel);
		
		System.out.println(stub.add().staff);
	}
	
	public static void main(String[] args){
		
		new ManageUserbl_drive().drive(new ManageUserbl_stub());
	}
}
