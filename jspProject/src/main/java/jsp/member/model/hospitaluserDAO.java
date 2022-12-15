package jsp.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class hospitaluserDAO {
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
	
	// 로그인 확인 메소드
	public int login(String userID, String userPw) {
		connect();
		String sql ="SELECT password from hospitaluser where id = ?";	
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPw)) {
					return 1; // 로그인 성공
				}
				else {
					return 0; // 로그인 실패(비밀번호 틀림)				}
				}
			}
			return -1; //아이디가 없음
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return -2; //데이터베이스 오류
	}
	
	
	// 신규 회원 추가 메소드
	public int insertDB(hospitaluserDTO hospitaluserDTO) {
		connect();
				
		String sql ="insert into hospitaluser values (?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,hospitaluserDTO.getId());
			pstmt.setString(2,hospitaluserDTO.getPassword());
			pstmt.setString(3,hospitaluserDTO.getHospitalName());
			pstmt.setString(4,hospitaluserDTO.getHospitalNumber());
			pstmt.setString(5,hospitaluserDTO.getHospitalId());
			pstmt.setString(6,hospitaluserDTO.getHospitalAddress());
			return pstmt.executeUpdate(); // 추가되는 회원의 수인 1 반환
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return -1; //회원 가입 실패
	}
	
	//id로 이름 구하는 메소드
			public String getNameDB(String id) {
				connect();
				String name = null;
				String sql = "select hospitalName from hospitaluser where id=?";
				
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,id);
					ResultSet rs = pstmt.executeQuery();
					// 데이터가 하나만 있으므로 rs.next()를 한번만 실행 한다.
					if(rs.next()) {
						name = rs.getString(1);
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					disconnect();
				}
				return name;
			}
			
			//id로 기관 id 구하는 메소드
			public String getHosIdDB(String id) {
				connect();
				String hosId = null;
				String sql = "select hospitalId from hospitaluser where Id=?";
				
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,id);
					ResultSet rs = pstmt.executeQuery();
					// 데이터가 하나만 있으므로 rs.next()를 한번만 실행 한다.
					if(rs.next()) {
						hosId = rs.getString(1);
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					disconnect();
				}
				return hosId;
			}
}
