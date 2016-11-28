/**
 * @author Yolanda151250080
 */
package tiquartet.ServerModule.bl.manageroombl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import tiquartet.CommonModule.blservice.manageroomblservice.ManageRoomBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.po.RoomPO;
import tiquartet.ServerModule.po.RoomTypePO;


public class ManageRoom implements ManageRoomBLService {
	
	static DataFactory dataFactory=new DataFactory();
	
	/*
	 * ���þƵ��Ż�ȡ�þƵ�ķ����б�
	 */
	public List<RoomVO> getRoomList (long hotelID) {
		
		List<RoomPO> list = new ArrayList<RoomPO>();
		//list.addAll(dataFactory.getRoomDataHelper().getRoom(hotelID));
		
		//��poת��vo
		List<RoomVO> roomList = new ArrayList<RoomVO>();
		RoomVO roomvo;
		
		for(RoomPO roompo: list){
			roomvo = new RoomVO();
			//BeanUtils.copyProperties(roomvo, roompo);
			roomList.add(roomvo);
		}
		
		return roomList;
	}
	
	/*
	 * �޸�ĳ���������Ϣ
	 */
	public ResultMessage modifyRoomInfo (RoomVO room) {
		
		//voתpo
		RoomPO roompo = new RoomPO();
		//BeanUtils.copyPropertites(roompo, room);
		
		dataFactory.getRoomDataHelper().update(roompo);
		
		return new ResultMessage(true);
	}
	
	/*
	 * ����һ���������Ϣ
	 */
	public ResultMessage addRoom (RoomVO room) {
		
		//voתpo
		RoomPO roompo = new RoomPO();
		//BeanUtils.copyPropertites(roompo, room);
		
		dataFactory.getRoomDataHelper().insert(roompo);
		
		return new ResultMessage(true);
	}
	
	/*
	 * ɾ��һ���������Ϣ
	 */
	public ResultMessage deleteRoom (int roomID) {
		
		dataFactory.getRoomDataHelper().delete(roomID);
		
		return new ResultMessage(true);
	}
	
	/*
	 * ��ĳ���ͷ���״̬��Ϊ����ס��
	 */
	public ResultMessage checkIn (int roomID) {
		
		dataFactory.getRoomDataHelper().checkIn(roomID);
		
		return new ResultMessage(true);
	}
	
	/*
	 * ��ĳ������״̬��Ϊ�����С�
	 */
	public ResultMessage checkOut (int roomID) {
		
		dataFactory.getRoomDataHelper().checkOut(roomID);
		
		return new ResultMessage(true);
	}
	
	/*
	 * �޸ķ�������
	 */
	public ResultMessage modifyRoomType (int hotelID, RoomTypeVO roomType) {
		
		//voתpo
		RoomTypePO roompo = new RoomTypePO();
		//BeanUtils.copyPropertites(roompo, roomType);
		
		dataFactory.getRoomDataHelper().updateType(hotelID, roompo);
		
		return new ResultMessage(true);
	}
	
	/*
	 * ɾ����������
	 */
	public ResultMessage deleteRoomType (int hotelID, int roomTypeID) {
		
		//dataFactory.getRoomDataHelper().deleteType(hotelID, roomTypeID);
		
		return new ResultMessage(true);
	}

	@Override
	public ResultMessage addRoomType(RoomTypeVO roomType)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modifyRoomType(RoomTypeVO roomType)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
}