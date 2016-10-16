package manageOrderbl.stub_driver;


public class ManageOrderbl_driver {

	public void drive(ManageOrderbl stub){
		
		OrderVO ordervo = stub.clientOrderList();
		System.out.println(ordervo.list);
		
		ordervo = stub.hotelOrderList();
		System.out.println(ordervo.list);
		
		ordervo = stub.clientCancelOrder();
		System.out.println(ordervo.result);
		
		ordervo = stub.marketerCancelOrder();
		System.out.println(ordervo.result);
		
		ordervo = stub.checkIn();
		System.out.println(ordervo.result);
		
		ordervo = stub.checkOut();
		System.out.println(ordervo.result);
		
		ordervo = stub.getOrderList();
		System.out.println(ordervo.list);
	}
	
	public static void main(String[] args){
		
		new ManageOrderbl_driver().drive(new ManageOrderbl_stub());
	}
}
