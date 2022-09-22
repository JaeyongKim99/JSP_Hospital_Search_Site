<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="jsp.member.model.*, java.util.*" %>
<%request.setCharacterEncoding("utf-8"); %>
<%@ page import="java.io.*, java.util.regex.Matcher, java.util.regex.Pattern"%>
<jsp:useBean id="pud" class="jsp.member.model.patientuserDAO" />
<jsp:useBean id="addrmember" class="jsp.member.model.patientuserDTO" />
<jsp:setProperty name="addrmember" property="*" />
<jsp:useBean id="hud" class="jsp.member.model.hospitaluserDAO" />
<jsp:useBean id="addrhos" class="jsp.member.model.hospitaluserDTO" />
<jsp:setProperty name="addrhos" property="*" />
<jsp:useBean id="rd" class="jsp.member.model.reserveDAO" />
<jsp:useBean id="addrrd" class="jsp.member.model.reserveDTO" />
<jsp:setProperty name="addrrd" property="*" />
<jsp:setProperty property="idHospital" name="addrrd" value="<%=request.getParameter(\"IdHospital\")%>"/>
<jsp:setProperty property="idpatient" name="addrrd" value="<%=request.getParameter(\"Idpatient\")%>"/>
<%
String action = request.getParameter("action"); // 개인 회원 열람 컨트롤러
String hosAction = request.getParameter("hosAction"); // 기업 회원 열람 컨트롤러
String rdAction = request.getParameter("rdAction"); // 예약 열람 컨트롤러

//환자 로그인
 if ("login".equals(action)) {
	String id = null;
	String password = null;
	if (request.getParameter("id") != null) {
		id = request.getParameter("id");
	}
	if (request.getParameter("password") != null) {
		password = request.getParameter("password");
	}
	// null값이 있는지 확인
	if (id == null || password == null || id.equals("") || password.equals("")) {
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('입력이 올바르지 않습니다.')");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		return;
	}
	if (id.equals("admin") || password.equals("admin")) {
		session.setAttribute("memLogin", "admin");
		response.sendRedirect("index.jsp");
	}

	// 오류를 감지하기 위해 result 값에 담음
	int result = pud.login(id, password);
	if (result == 1) {
		session.setAttribute("memLogin", "ok");
		if (request.getParameter("idSave") == null) {
	session.removeAttribute("memSave");
		} else {
	session.setAttribute("memSave", "check");
		}
		session.setAttribute("memId", id);
		session.setAttribute("memPw", password);
		//헤더부분에 이름을 보내주기 위해 로그인할때 이름을 가져와 세션에 선언한다.
		String name = pud.getNameDB(id);
		session.setAttribute("memName", name);
		response.sendRedirect("Index.jsp");
	}

	else if (result == 0) {
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('비밀번호가 틀립니다.')");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		return;
	}

	else if (result == -1) {
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('존재하지 않는 아이디입니다.')");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		return;
	} else
		throw new Exception("DB 입력오류");
}

//환자 회원 가입
else if ("insert".equals(action)) {
	String id = null; //환자 아이디
	String password = null; // 환자 비밀번호
	String name = null; // 환자 이름
	int birthdate = 0; // 환자 생년월일
	String sex = null; // 환자 성별
	String address = null; // 환자 주소
	
	if (request.getParameter("id") != null) {
		id = request.getParameter("id");
	}
	if (request.getParameter("password") != null) {
		password = request.getParameter("password");
	}
	if (request.getParameter("name") != null) {
		name = request.getParameter("name");
	}
	if(request.getParameter("birthdate") != null){
		birthdate = Integer.parseInt(request.getParameter("birthdate"));
	}
	if (request.getParameter("sex") != null) {
		sex = request.getParameter("sex");
	}
	if (request.getParameter("address") != null) {
		address = request.getParameter("address");
	}
	
	// null값이 있는지 확인
	if (id == null || password == null || name == null || birthdate == 0 || sex == null || address == null || id.equals("")
	|| password.equals("") || password.equals("")) {
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('입력이 올바르지 않습니다.')");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		return;
	}

	// 오류를 감지하기 위해 result변수에 담음
	int result = pud.insertDB(addrmember);
	if (result > 0) {
		response.sendRedirect("Index.jsp");
	}

	else if (result == -1) {
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('회원가입에 실패했습니다.')");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		return;
	} else
		throw new Exception("DB 입력오류");
}

// 병원 로그인
 if ("login".equals(hosAction)) {
	String id = null;
	String password = null;
	if (request.getParameter("id") != null) {
		id = request.getParameter("id");
	}
	if (request.getParameter("password") != null) {
		password = request.getParameter("password");
	}
	// null값이 있는지 확인
	if (id == null || password == null || id.equals("") || password.equals("")) {
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('입력이 올바르지 않습니다.')");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		return;
	}
	if (id.equals("admin") || password.equals("admin")) {
		session.setAttribute("memLogin", "admin");
		response.sendRedirect("Index.jsp");
	}

	// 오류를 감지하기 위해 result 값에 담음
	int result = hud.login(id, password);
	if (result == 1) {
		session.setAttribute("memLogin", "hos");
		if (request.getParameter("hosIdSave") == null) {
	session.removeAttribute("hosMemSave");
		} else {
	session.setAttribute("hosMemSave", "check");
		}
		session.setAttribute("hosMemId", id);
		session.setAttribute("hosMemPw", password);
		
		ArrayList<reserveDTO> datas = rd.getDBList(hud.getHosIdDB(id));
		request.setAttribute("datas", datas);
		//헤더부분에 이름을 보내주기 위해 로그인할때 이름을 가져와 세션에 선언한다.
		String name = hud.getNameDB(id);
		session.setAttribute("memName", name);
		pageContext.forward("HospitalReserve.jsp");
	}

	else if (result == 0) {
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('비밀번호가 틀립니다.')");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		return;
	}

	else if (result == -1) {
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('존재하지 않는 아이디입니다.')");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		return;
	} else
		throw new Exception("DB 입력오류");
}

//회원 가입
else if ("insert".equals(hosAction)) {
	String id = null;; //병원 아이디
	String password = null;;// 병원 비밀번호
	String hospitalName = null;;// 병원명
	String hospitalNumber = null;;// 병원 대표 전화
	String hospitalId = null;; // 병원 기관 ID
	String hospitalAddress = null;; // 병원 주소
	
	if (request.getParameter("id") != null) {
		id = request.getParameter("id");
	}
	if (request.getParameter("password") != null) {
		password = request.getParameter("password");
	}
	if (request.getParameter("hospitalName") != null) {
		hospitalName = request.getParameter("hospitalName");
	}
	if(request.getParameter("hospitalNumber") != null){
		hospitalNumber = request.getParameter("hospitalNumber");
	}
	if (request.getParameter("hospitalId") != null) {
		hospitalId = request.getParameter("hospitalId");
	}
	if (request.getParameter("hospitalAddress") != null) {
		hospitalAddress = request.getParameter("hospitalAddress");
	}
	
	// null값이 있는지 확인
	if (id == null || password == null || hospitalName == null || hospitalNumber == null || hospitalId == null || hospitalAddress == null || id.equals("")
	|| password.equals("") || password.equals("")) {
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('입력이 올바르지 않습니다.')");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		return;
	}

	// 오류를 감지하기 위해 result변수에 담음
	int result = hud.insertDB(addrhos);
	if (result > 0) {
		response.sendRedirect("Index.jsp");
	}

	else if (result == -1) {
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('회원가입에 실패했습니다.')");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		return;
	} else
		throw new Exception("DB 입력오류");
}

//예약 등록
if ("insert".equals(rdAction)) {
	String IdHospital = null; //병원 아이디
	String Idpatient = null;// 환자 아이디
	String department = null;// 예약구분
	String reserveDiv = null;// 진료과
	String registrationBackNumber = null; // 주민번호 뒷자리
	String symptom = null; // 증상
	String reserveDate = null; //예약날짜
	String Namepatient = null; //환자이름
	
	if (request.getParameter("IdHospital") != null) {
		IdHospital = request.getParameter("IdHospital");
	}
	if (request.getParameter("Idpatient") != null) {
		Idpatient = request.getParameter("Idpatient");
	}
	if (request.getParameter("department") != null) {
		department = request.getParameter("department");
	}
	if(request.getParameter("reserveDiv") != null){
		reserveDiv = request.getParameter("reserveDiv");
	}
	if (request.getParameter("registrationBackNumber") != null) {
		registrationBackNumber = request.getParameter("registrationBackNumber");
	}
	if (request.getParameter("symptom") != null) {
		symptom = request.getParameter("symptom");
	}
	if (request.getParameter("reserveDate") != null) {
		reserveDate = request.getParameter("reserveDate");
	}
	if (request.getParameter("Namepatient") != null) {
		Namepatient = request.getParameter("Namepatient");
	}
	
	// null값이 있는지 확인
	if (IdHospital == null || Idpatient == null || department == null || reserveDiv == null || registrationBackNumber == null || symptom == null || reserveDate == null || Namepatient == null)
	{
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('입력이 올바르지 않습니다.')");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		return;
	}

	// 오류를 감지하기 위해 result변수에 담음
	int result = rd.insertDB(addrrd);
	if (result > 0) {
		response.sendRedirect("Index.jsp");
	}

	else if (result == -1) {
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('예약에 실패했습니다.')");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		return;
	} else
		throw new Exception("DB 입력오류");
}

%>
