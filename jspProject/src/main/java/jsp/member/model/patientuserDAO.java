package jsp.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class patientuserDAO {
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
	
	// 로그인 확인 메소드
	public int login(String userID, String userPw) {
		connect();
		String sql ="SELECT password from patientuser where id = ?";	
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
	public int insertDB(patientuserDTO patientuserDTO) {
		connect();
				
		String sql ="insert into patientuser values (?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,patientuserDTO.getId());
			pstmt.setString(2,patientuserDTO.getPassword());
			pstmt.setString(3, patientuserDTO.getName());
			pstmt.setInt(4,patientuserDTO.getBirthdate());
			pstmt.setString(5,patientuserDTO.getSex());
			pstmt.setString(6,patientuserDTO.getAddress());
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
			String sql = "select name from patientuser where id=?";
			
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
		// 특정 회원 개인정보 출력 메소드
		public patientuserDTO getDBList(String id) {
			connect();
			String sql = "select * from patientuser where Id=?";
			patientuserDTO data = new patientuserDTO();
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,id);
				System.out.println(pstmt);
				ResultSet rs = pstmt.executeQuery();
				
				rs.next();
				data.setId(rs.getString("Id"));
				data.setPassword(rs.getString("password"));
				data.setName(rs.getString("name"));
				data.setBirthdate(rs.getInt("birthdate"));
				data.setSex(rs.getString("sex"));
				data.setAddress(rs.getString("address"));
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				disconnect();
			}
			return data;
		}
		//환자 정보 업데이트
		public int updateDB(String Privacy[]) {
			connect();
			String sql ="update patientuser set password=? where id=?";		
			 
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, Privacy[0]);
				pstmt.setString(2, Privacy[1]);
				System.out.println(pstmt);
				pstmt.executeUpdate();
				return pstmt.executeUpdate(); // 수정되는 회원의 수인 1 반환
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				disconnect();
			}
			return -1;
		}
}