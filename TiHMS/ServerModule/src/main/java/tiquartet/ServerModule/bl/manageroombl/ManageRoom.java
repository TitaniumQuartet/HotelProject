/**
 * @author Yolanda151250080
 */
package tiquartet.ServerModule.bl.manageroombl;

import java.util.List;
import org.apache.commons.beanutils.BeanUtils; 

import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.CommenModule.vo.RoomTypeVO;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.blservice.manageroomblservice.ManageRoomBLService;
import tiquartet.ServerModule.dataservice.roomdataservice.RoomDataController;
import tiquartet.ServerModule.po.RoomPO;


public class ManageRoom implements ManageRoomBLService {
	
	/*
	 * ���þƵ��Ż�ȡ�þƵ�ķ����б�
	 */
	public List<RoomVO> getRoomList (long hotelID) {
		
		List<RoomPO> list = new List<RoomPO>();
		list.addAll(RoomDataController.getRoom(hotelID));
		
		//��poת��vo
		List<RoomVO> roomList = new List<RoomVO>();
		RoomVO roomvo;
		
		for(RoomPO roompo: list){
			roomvo = new RoomVO();
			BeanUtils.copyProperties(roomvo, roompo);
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
		BeanUtils.copyPropertites(roompo, room);
		
		return RoomDataController.update(roompo) ;
	}
	
	/*
	 * ����һ���������Ϣ
	 */
	public ResultMessage addRoom (RoomVO room) {
		
		//voתpo
		RoomPO roompo = new RoomPO();
		BeanUtils.copyPropertites(roompo, room);
		
		return RoomDataController.insert(roompo);
	}
	
	/*
	 * ɾ��һ���������Ϣ
	 */
	public ResultMessage deleteRoom (int roomID) {
		
		return RoomDataController.delete(roomID);
	}
	
	/*
	 * ��ĳ���ͷ���״̬��Ϊ����ס��
	 */
	public ResultMessage checkIn (int roomID) {
		
		return RoomDataController.checkIn(roomID);
	}
	
	/*
	 * ��ĳ������״̬��Ϊ�����С�
	 */
	public ResultMessage checkOut (int roomID) {
		
		return RoomDataController.checkOut(roomID);
	}
	
	/*
	 * �޸ķ�������
	 */
	public ResultMessage modifyRoomType (int hotelID, RoomTypeVO roomType) {
		
		return RoomDataController.updateType(hotelID, roomType);
	}
	
	/*
	 * ɾ����������
	 */
	public ResultMessage deleteRoomType (int hotelID, int roomTypeID) {
		
		return RoomDateController.deleteType(hotelID, roomTypeID);
	}
	
}