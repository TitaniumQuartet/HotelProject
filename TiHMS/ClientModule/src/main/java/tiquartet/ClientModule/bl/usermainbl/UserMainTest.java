package tiquartet.ClientModule.bl.usermainbl;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import tiquartet.ClientModule.vo.UserVO;
import tiquartet.CommonModule.util.ResultMessage;

public class UserMainTest {
	
	private UserMain user;

	@Test
	public void testlogin() {
		user=new UserMain();
		UserVO userVO=user.login("Teki", "12345678");
        assertEquals(userVO.password,"12345678");
        assertEquals(userVO.userName,"Teki");
	}
	
	@Test
	public void testlogout(){
		user=new UserMain();
		UserVO userVO=user.login("Teki", "12345678");
		ResultMessage result=user.logout(userVO.userID);
		assertEquals(result,ResultMessage.SUCCEED);
	}
	
	@Test
	public void testsignUp(){
		user=new UserMain();
		ResultMessage result=user.signUp("Teki", "12345678");
		assertEquals(result,ResultMessage.SUCCEED);
	}
	
	@Test
	public void testisUnregistered(){
		user=new UserMain();
		boolean result=user.isUnregistered("Teki");
		assertEquals(result,true);
	}
	
	@Test
	public void testcurrentUser(){
		user=new UserMain();
		UserVO userVO=user.currentUser();
		assertEquals(userVO.password,"12345678");
        assertEquals(userVO.userName,"Teki");
	}

}
