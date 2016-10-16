package tiquartet.stub_driver.dataservice.creditdata;
import java.util.List;
import tiquartet.common.dataservice.creditdataservice;

public class CreditData_stub implements CreditDataService{
	public List<CreditPO> getRecord(long userID){
		System.out.println("yes");
		return new CreditPO();
	}

}
