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
		ResultMessage resultMessage = user.login("Tekkie", "1234567");
		 user.logout(2);
		assertEquals(true, resultMessage.result);
	}

	@Test
	public void testlogout(){
		user=new UserMain();
		user.login("Tekkie", "1234567");
		ResultMessage resultMessage = user.logout(2);
		System.out.println(resultMessage.result);
		assertEquals(true, resultMessage.result);
	}
	
	@Test
	public void testisUnregistered(){
		user=new UserMain();	
		boolean result = user.isUnregistered("Tom");
		assertEquals(false, result);
		
	}

}
