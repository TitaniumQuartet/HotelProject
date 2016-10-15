package tiquartet.stub_driver.createorder;

public class CreateOrderBL_driver {
    public void drive(CreateBLService stub){
    	OrderInfoVO orderInfovo = new OrderInfoVO();
    	System.out.println(stub.orderInfo(orderInfovo));
    	OrderStrategyVO orderstrategyvo = stub.getoptimal();
    	List<OtherStrategyVO> li = stub.otherStrategy();
    	long strategyID = 0;
    	System.out.println(stub.selectStrategy(strategyID));
    }
    public static void main(String[] args){
    	
    	new CreateOrderBL_drive().drive(new CreateOrderBL_stub());
    }
}
