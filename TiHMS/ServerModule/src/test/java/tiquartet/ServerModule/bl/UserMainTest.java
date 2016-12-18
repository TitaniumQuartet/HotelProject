package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;

import org.junit.Test;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.bl.usermainbl.UserMain;

public class UserMainTest {
	
	private UserMain user;

	@Test
	public void testlogin() {
		user=new UserMain();
		
		ResultMessage resultMessage = user.login("Yolanda", "123123");
		assertEquals(new ResultMessage(true).result, resultMessage.result);
	}
	
	@Test
	public void testlogout(){
		user=new UserMain();
		
		ResultMessage resultMessage = user.logout(3);
		assertEquals(new ResultMessage(true).result, resultMessage.result);
	}
	
	@Test
	public void testisUnregistered(){
		user=new UserMain();
		
		boolean result = user.isUnregistered("Yolanda");
		assertEquals(true, result);
		
	}

}
