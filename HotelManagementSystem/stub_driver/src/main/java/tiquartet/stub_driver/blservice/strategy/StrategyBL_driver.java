package tiquartet.stub_driver.blservice.strategy;

import java.util.*;
import tiquartet.client.blservice.strategyblservice.*;
import tiquartet.client.vo.*;

public class StrategyBL_driver {
    public void drive(StrategyBLService stub){
	  System.out.println(stub.addStrategy(new StrategyVO()));
	  System.out.println(stub.deleteStrategy(0));
	  List<StrategyVO> li = stub.getStrategy(0);
	  System.out.println(stub.changeStrategy(new StrategyVO()));
    }
    public static void main(String[] args){
    	new StrategyBL_driver().drive(new StrategyBL_stub());
    }
}
