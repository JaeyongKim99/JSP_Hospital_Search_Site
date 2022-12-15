package jsp.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class reserveDAO {

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
	
	// 예약 추가 메소드
		public int insertDB(reserveDTO reserveDTO) {
			connect();
					
			String sql ="insert into reserve values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,reserveDTO.getIdHospital());
				pstmt.setString(2,reserveDTO.getIdpatient());
				pstmt.setString(3, reserveDTO.getDepartment());
				pstmt.setString(4,reserveDTO.getReserveDiv());
				pstmt.setString(5,reserveDTO.getRegistrationBackNumber());
				pstmt.setString(6,reserveDTO.getSymptom());
				pstmt.setString(7,reserveDTO.getReserveDate());
				pstmt.setString(8,reserveDTO.getNamepatient());
				pstmt.setString(9,reserveDTO.getNamehospital());
				System.out.println(pstmt);
				return pstmt.executeUpdate(); // 추가되는 회원의 수인 1 반환
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				disconnect();
			}
			return -1; //회원 가입 실패
		}
		
		// 특정 병원 예약 출력 메소드
		public ArrayList<reserveDTO> getDBList(String id) {
			connect();
			ArrayList<reserveDTO> datas = new ArrayList<reserveDTO>();
			
			String sql = "select * from reserve where IdHospital=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,id);
				System.out.println(pstmt);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					reserveDTO reserveDTO = new reserveDTO();
					reserveDTO.setIdHospital(rs.getString("IdHospital"));
					reserveDTO.setIdpatient(rs.getString("Idpatient"));
					reserveDTO.setDepartment(rs.getString("department"));
					reserveDTO.setReserveDiv(rs.getString("reserveDiv"));
					reserveDTO.setRegistrationBackNumber(rs.getString("registrationBackNumber"));
					reserveDTO.setSymptom(rs.getString("symptom"));
					reserveDTO.setReserveDate(rs.getString("reserveDate"));
					reserveDTO.setNamepatient(rs.getString("namepatient"));
					reserveDTO.setNamehospital(rs.getString("namehospital"));
					datas.add(reserveDTO);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				disconnect();
			}
			return datas;
		}
		// 환자 마이페이지 예약 출력 메소드
		public ArrayList<reserveDTO> getMemDBList(String id) {
			connect();
			ArrayList<reserveDTO> datas = new ArrayList<reserveDTO>();
							
			String sql = "select * from reserve where Idpatient=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,id);
				System.out.println(pstmt);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					reserveDTO reserveDTO = new reserveDTO();
					reserveDTO.setIdHospital(rs.getString("IdHospital"));
					reserveDTO.setIdpatient(rs.getString("Idpatient"));
					reserveDTO.setDepartment(rs.getString("department"));
					reserveDTO.setReserveDiv(rs.getString("reserveDiv"));
					reserveDTO.setRegistrationBackNumber(rs.getString("registrationBackNumber"));
					reserveDTO.setSymptom(rs.getString("symptom"));
					reserveDTO.setReserveDate(rs.getString("reserveDate"));
					reserveDTO.setNamepatient(rs.getString("namepatient"));
					reserveDTO.setNamehospital(rs.getString("namehospital"));
					datas.add(reserveDTO);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				disconnect();
			}
			return datas;
		}
		// 특정 예약 삭제 메소드
		public boolean deleteDB(String[]  id) {
			connect();
			
			String sql ="delete from reserve where IdHospital=? and Idpatient=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id[0]);
				pstmt.setString(2, id[1]);
				System.out.println(pstmt);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			finally {
				disconnect();
			}
			return true;
		}
}
