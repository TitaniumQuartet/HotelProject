package tiquartet.ServerModule.bl.rmiinit;

import tiquartet.CommonModule.util.ResultMessage;

/**
 * 酒店管理系统的服务器类，可建立RMI服务，运行服务器.
 * @author greatlyr
 */
public class HMSServer {
	/**
	 * 服务器初始化，提供RMI服务.
	 * @return 启动服务器的结果信息
	 */
	public ResultMessage init() {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResultMessage(true);
	}
}
