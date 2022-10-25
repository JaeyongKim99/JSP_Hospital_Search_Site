package jsp.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class hospitallistDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	
    
	/* MySQL 연결정보 */
	String jdbc_driver = "com.mysql.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost:3306/medidak?useSSL=false&useUnicode=true&characterEncoding=utf-8"; 
	
	// DB연결 메서드
	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url,"root","3548");
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
	public hospitallistDTO getDBList(String id) {
		connect();
		hospitallistDTO data = new hospitallistDTO();	
		String sql = "select * from hospitallist where hpid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			data.setHpid(rs.getString("hpid"));
			data.setPostCdn1(rs.getString("postCdn1"));
			data.setPostCdn2(rs.getString("postCdn2"));
			data.setWgs84Lon(rs.getString("wgs84Lon"));
			data.setWgs84Lat(rs.getString("wgs84Lat"));
			data.setDutyAddr(rs.getString("dutyAddr"));
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
