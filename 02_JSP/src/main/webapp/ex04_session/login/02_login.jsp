<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
	
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 로그인 시나리오
		// id=admin, pw=1234인 경우에 로그인 성공
		if(id.equals("admin") && pw.equals("1234")) {  // 자바코드라서 ==로 비교하면 안 됨.
			
			// 로그인 한 회원 정보를 session에 보관하기
			session.setAttribute("loginId", id);  // 비번은 db에 저장?
			session.setMaxInactiveInterval(60 * 10); // 세션유효시간 : 600초(10분)  // 10분 후에는 로그인이 풀림(세션 정보를 지움)

		}
	
		// 다시 로그인 폼으로 돌아가기
		response.sendRedirect(request.getContextPath() + "/ex04_session/login/01_form.jsp");
	
	%>

</body>
</html>