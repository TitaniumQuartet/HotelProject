package tiquartet.ServerModule.bl;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;
import tiquartet.ServerModule.bl.manageorderbl.ManageOrder;
import tiquartet.ServerModule.bl.manageuserbl.ManageUser;

public class ManageOrderTest {
	private ManageOrder manageorder=new ManageOrder();
	private ManageUser manageuser=new ManageUser();
	@Test
	public void testmarketerCancel() throws RemoteException{
		UserVO user=manageuser.getUser(2);
		double credit=user.credit;
		ResultMessage result=manageorder.marketerCancel(2, CreditRestore.全部);
		assertEquals(result.result,true);
		user=manageuser.getUser(2);
		if(credit+500==user.credit){
			assertEquals(true,true);
		}else{
			assertEquals(true,false);
		}
	}
	@Test
	public void testclientCancekl() throws RemoteException{
		ResultMessage result=manageorder.clientCancel(6);
		assertEquals(result.result,true);
		ResultMessage result1=manageorder.clientCancel(3);
		assertEquals(result1.result,false);
	}
	public void testcheckIn() throws RemoteException{
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String estLeaveTime = format.format(new Date());
		ResultMessage result = manageorder.checkIn(2, estLeaveTime);
		assertEquals(result.result,false);
	}
}
