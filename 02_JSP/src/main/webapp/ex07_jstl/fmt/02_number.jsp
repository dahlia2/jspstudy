<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 자리 조정은 모든 경우에서 반올림 사용 --> <!-- 반올림할 게 아니라면 자바에서 미리 올림이나 내림 처리 -->

	<c:set var="n" value="12345.678" scope="page" />
	
	<%-- 천 단위 구분기호 사용하기 --%>
	<h1><fmt:formatNumber value="${n}" pattern="#,##0" /></h1>
	<h1><fmt:formatNumber value="${n}" pattern="#,##0.00" /></h1>  <!-- 쇼핑몰 구현할 때 필요 -->
	
	<%-- 백분율 : 숫자에 100을 곱한 뒤 %를 붙인다. --%>
	<h1><fmt:formatNumber value="${n}" type="percent" /></h1>
	
	<%-- 통화 : 통화 기호와 천 단위 구분기호를 모두 사용한다. --%>
	<h1><fmt:formatNumber value="${n}" type="currency" currencySymbol="￦" /></h1>
	<h1><fmt:formatNumber value="${n}" type="currency" currencySymbol="$" /></h1>	

</body>
</html>