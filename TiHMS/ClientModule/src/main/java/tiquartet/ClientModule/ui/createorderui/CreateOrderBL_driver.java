package tiquartet.ClientModule.ui.createorderui;

import java.util.List;

import tiquartet.CommonModule.vo.*;
import tiquartet.ServerModule.bl.createorderbl.CreateOrderBL_stub;

public class CreateOrderBL_driver {
    public void drive(CreateOrderBL_stub stub){
    	OrderInfoVO orderInfovo = new OrderInfoVO();
    	System.out.println(stub.orderInfo(orderInfovo));
    	OrderStrategyVO orderstrategyvo = stub.getOptimal();
    	List<OrderStrategyVO> li = stub.otherStrategy();
    	long strategyID = 0;
    	System.out.println(stub.selectStrategy(strategyID));
    }
    public static void main(String[] args){
    	
    	new CreateOrderBL_driver().drive(new CreateOrderBL_stub());
    }
}
