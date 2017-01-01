package tiquartet.ClientModule.ui.usermainui;

import java.util.prefs.Preferences;

import tiquartet.CommonModule.vo.UserVO;

public class UserPreferences {
	
	public static void setLoginPref(String username, String encrypted){
		Preferences preferences = Preferences.userRoot().node(UserPreferences.class.getName());
		preferences.put("name", username);
		preferences.put("info", encrypted);
	}
	
	public static UserVO getLoginPref(){
		Preferences preferences = Preferences.userRoot().node(UserPreferences.class.getName());
		UserVO userVO = new UserVO();
		userVO.userName = preferences.get("name", null);
		userVO.password = preferences.get("info", null);
		if(userVO.userName==null||userVO.password==null||userVO.userName.isEmpty()||userVO.password.isEmpty()) userVO = null;
		return userVO;
	}
}
