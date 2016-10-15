package tiquartet.stub_driver.strategy;



public class StrategyBL_driver {
    public void drive(StrategyBLService stub){
	  System.out.println(stub.addStrategy(new StrategyVO()));
	  System.out.println(stub.delateStrategy(0));
	  List<StrategyVO> li = stub.getStrategy(0);
	  System.out.println(stub.changeStrategy(new StrategyVO()));
    }
    public static void main(String[] args){
    	new StrategyBL_driver().drive(new StrategyBL_stub());
    }
}
