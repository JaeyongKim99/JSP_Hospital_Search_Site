package jsp.member.model;

public class reserveDTO {
	private String idHospital; //병원 아이디
	private String idpatient;// 환자 아이디
	private String department;// 예약구분
	private String reserveDiv;// 진료과
	private String registrationBackNumber; // 주민번호 뒷자리
	private String symptom; // 증상
	private String reserveDate; //예약날짜
	private String namepatient; //환자 이름
	
	
	public String getNamepatient() {
		return namepatient;
	}
	public void setNamepatient(String namepatient) {
		this.namepatient = namepatient;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getIdHospital() {
		return idHospital;
	}
	public void setIdHospital(String idHospital) {
		this.idHospital = idHospital;
	}
	public String getIdpatient() {
		return idpatient;
	}
	public void setIdpatient(String idpatient) {
		this.idpatient = idpatient;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getReserveDiv() {
		return reserveDiv;
	}
	public void setReserveDiv(String reserveDiv) {
		this.reserveDiv = reserveDiv;
	}
	public String getRegistrationBackNumber() {
		return registrationBackNumber;
	}
	public void setRegistrationBackNumber(String registrationBackNumber) {
		this.registrationBackNumber = registrationBackNumber;
	}
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
}
