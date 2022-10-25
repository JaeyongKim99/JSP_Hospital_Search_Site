package jsp.member.model;

public class hosdepartmentDTO {
	private String hpid; //병원 아이디
	private String dgidIdName;// 병원 진료과
	public String getHpid() {
		return hpid;
	}
	public void setHpid(String hpid) {
		this.hpid = hpid;
	}
	public String getDgidIdName() {
		return dgidIdName;
	}
	public void setDgidIdName(String dgidIdName) {
		this.dgidIdName = dgidIdName;
	}
}
