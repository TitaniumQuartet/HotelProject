package tiquartet.ClientModule.ui.datastorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.DistrictVO;

/**
 * 用于管理本地以二进制文件存储的DistrictVO.
 * @author greatlyr
 *
 */
public class DistrictData {
	
	private static DistrictVO districtVO = null;
	
	/**
	 * 从本地的district文件加载DistrictVO值对象.
	 * @return
	 */
	public static ResultMessage loadLocalData(){
		try{
			//保存到district文件
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("/ClientModule/src/main/resources/data/district")));
			districtVO = (DistrictVO) inputStream.readObject();
			inputStream.close();
			return new ResultMessage(true);
		}catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ResultMessage(false,"数据文件读取失败",null);
		}
		
	}
	
	/**
	 * 用传入的值对象更新本地数据文件，再更新静态DistrictVO成员.
	 * @param newData
	 * @return
	 */
	public static ResultMessage updateData(DistrictVO newData){
		try{
			//将传入额VO保存到本地数据文件
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("/ClientModule/src/main/resources/data/district"), false));
			outputStream.writeObject(newData);
			outputStream.close();
			//从本地数据文件中读取DistrictVO对象
			ResultMessage loadResult = loadLocalData();
			return loadResult;
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(false,"数据文件写入失败",null);
		}
	}
	
	/**
	 * 获取城市编号与名称的HashMap.
	 * @return
	 */
	public HashMap<Integer, String> getCityMap(){
		return districtVO.cityMap;
	}
	
	/**
	 * 获取商圈编号与名称的HashMap.
	 * @return
	 */
	public HashMap<Integer, String> getDistrictMap(){
		return districtVO.cityMap;
	}
	
}
