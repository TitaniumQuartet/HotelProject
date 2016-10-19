package tiquartet.StubDriver.blservice.strategy;

import java.util.*;
import tiquartet.ClientModule.blservice.strategyblservice.*;
import tiquartet.ClientModule.vo.*;

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
