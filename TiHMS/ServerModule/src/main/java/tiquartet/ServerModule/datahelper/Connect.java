package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 加载驱动
 * @author Teki
 */
public class Connect {
	
	public static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://127.0.0.1:3306/hotelmanage?useUnicode=true&characterEncoding=UTF-8&useSSL=true";
	    String username = "root";
	    String password = "jjmoon05";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch(ClassNotFoundException e){   
	        System.out.println("找不到驱动程序类 ，加载驱动失败！");   
	        e.printStackTrace() ;   
	    }    catch (SQLException e) {
	    	System.out.println("SQL语句处理失败！");   
	        e.printStackTrace();
	    }
	    return conn;
	}

}
