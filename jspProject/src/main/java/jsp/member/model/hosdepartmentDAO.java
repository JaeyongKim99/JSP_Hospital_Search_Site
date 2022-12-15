package jsp.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class hosdepartmentDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	//ip주소 가져오기
	ResourceBundle resource = ResourceBundle.getBundle("apikey");
	String ipAddress = resource.getString("ipAddress");
	/* MySQL 연결정보 */
	String jdbc_driver = "com.mysql.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://"+ ipAddress + ":3306/medidak?useSSL=false&useUnicode=true&characterEncoding=utf-8"; 
	
	// DB연결 메서드
	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url,"admin","3548");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 특정 병원 진료과 출력 메소드
	public hosdepartmentDTO getDBList(String id) {
		connect();
		hosdepartmentDTO data = new hosdepartmentDTO();
		String sql = "select * from hosdepartment where hpid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			data.setHpid(rs.getString("hpid"));
			data.setDgidIdName(rs.getString("dgidIdName"));
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return data;
	}
}
