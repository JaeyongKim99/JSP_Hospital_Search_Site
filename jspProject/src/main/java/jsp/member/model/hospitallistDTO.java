package jsp.member.model;

public class hospitallistDTO {
	private String hpid; //병원 아이디
	private String postCdn1;// 우편번호 1
	private String postCdn2;// 우편번호 2
	private String wgs84Lon;// 경도
	private String wgs84Lat; // 위도
	private String dutyAddr; // 병원 주소
	private String rnum; //로우 넘버
	public String getHpid() {
		return hpid;
	}
	public void setHpid(String hpid) {
		this.hpid = hpid;
	}
	public String getPostCdn1() {
		return postCdn1;
	}
	public void setPostCdn1(String postCdn1) {
		this.postCdn1 = postCdn1;
	}
	public String getPostCdn2() {
		return postCdn2;
	}
	public void setPostCdn2(String postCdn2) {
		this.postCdn2 = postCdn2;
	}
	public String getWgs84Lon() {
		return wgs84Lon;
	}
	public void setWgs84Lon(String wgs84Lon) {
		this.wgs84Lon = wgs84Lon;
	}
	public String getWgs84Lat() {
		return wgs84Lat;
	}
	public void setWgs84Lat(String wgs84Lat) {
		this.wgs84Lat = wgs84Lat;
	}
	public String getDutyAddr() {
		return dutyAddr;
	}
	public void setDutyAddr(String dutyAddr) {
		this.dutyAddr = dutyAddr;
	}
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
}
