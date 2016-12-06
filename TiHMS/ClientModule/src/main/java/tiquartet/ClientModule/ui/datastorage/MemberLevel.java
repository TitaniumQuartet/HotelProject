package tiquartet.ClientModule.ui.datastorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import tiquartet.CommonModule.util.DigitToChinese;
import tiquartet.CommonModule.util.ResultMessage;

/**
 * 管理会员级别名称数据.
 * @author greatlyr
 *
 */
public class MemberLevel {
	
	private static HashMap<Integer, String> levelNameMap = null;
	
	/**
	 * 取得相应级别会员别称.
	 * @param level
	 * @return
	 */
	public static String getLevelName(int level){
		if(level<0||level>10) return null;
		else if(level==0&&!levelNameMap.keySet().contains(0)) return "非会员";
		else return levelNameMap.get(level);
	}
	
	/**
	 * 从level文件读取会员等级名称的列表.
	 * @return
	 */
	public static ResultMessage loadLocalData(){
		try{
			File binFile = new File("src/main/java/tiquartet/ClientModule/data/level");
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(binFile));
			levelNameMap = (HashMap<Integer, String>) inputStream.readObject();
			inputStream.close();
			return new ResultMessage(true);
		}catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ResultMessage(false,"数据文件读取失败",null);
		}
	}

	/**
	 * 更新静态HashMap对象和本地数据文件.
	 * @param levelNames
	 * @return
	 */
	public static ResultMessage updateLevelName(HashMap<Integer, String> levelNames){
		//若1-10级中有任意一个没有相应的名称，则操作失败.
		for(int i=1;i<=10;i++) if(!levelNames.containsKey(i)) return new ResultMessage(false,"会员级别名称不完整",null);
		try {
			File binFile = new File("src/main/java/tiquartet/ClientModule/data/level");
			binFile.createNewFile();
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(binFile, false));
			outputStream.writeObject(levelNames);
			outputStream.close();
			levelNameMap = levelNames;
			return new ResultMessage(true);
		} catch (IOException e) {
			return new ResultMessage(false,"文件写入失败",null);
		}
	}
	
	/**
	 * 将会员级别名称设置为初始值，仅作测试用的方法.
	 * @return
	 */
	public static ResultMessage init() {
		HashMap<Integer, String> initialLevelName = new HashMap<>();
		initialLevelName.put(0, "非会员");
		for(int i=1;i<11;i++){
			initialLevelName.put(i, DigitToChinese.toChinese(i)+"级会员");
		}
		ResultMessage message = updateLevelName(initialLevelName);
		return message;
	}
	
	public static void main(String args[]){
		init();
	}
}
