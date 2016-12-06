package tiquartet.ClientModule.ui.datastorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
	
	private static HashMap<Integer, ArrayList<Integer>> districtCityList;
	
	private static void setList(){
		for(int i:districtVO.cityMap.keySet()){
			districtCityList.put(i, new ArrayList<Integer>());
		}
		for(int i:districtVO.districtMap.keySet()){
			ArrayList<Integer> list = districtCityList.get(i/100);
			if(list!=null) list.add(i);
		}
	}
	
	/**
	 * 从本地的district文件加载DistrictVO值对象.
	 * @return
	 */
	public static ResultMessage loadLocalData(){
		try{
			//读取district文件
			File binFile = new File("src/main/java/tiquartet/ClientModule/data/district");
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(binFile));
			districtVO = (DistrictVO) inputStream.readObject();
			setList();
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
			File binFile = new File("src/main/java/tiquartet/ClientModule/data/district");
			binFile.createNewFile();
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(binFile, false));
			outputStream.writeObject(newData);
			outputStream.close();
			districtVO = newData;
			setList();
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
	
	/**
	 * 返回该城市的所有商圈的编号的列表.
	 * @param cityID
	 * @return
	 */
	public ArrayList<Integer> districtIDListOfCity(int cityID){
		return districtCityList.get(cityID);
	}
	
	
	
}
