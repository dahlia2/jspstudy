<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());
%>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>

	$(function(){
		$('#btn_add').on('click', function(){
				alert('학생이 등록되었습니다.');
				location.href = '${contextPath}/write.do';
			})
		
	})
	
	
	
	
	function fnGetAllStudent(){
		$a.jax{
			// 요청
			type: 'get'
			url: '${contextPath}/list.do'
			// 응답
			dataType: 'json',
			success: function(resData){
			
				$('#student_member').text(resData.memberCount);
				let studentList = $('student_list');
				studentList.append('<tr><td colspan="3">등록된 학생이 없습니다.</td></tr>')
			} else {
				$.each(resData. memberList, function(i, element){
				let str = '<tr>';
				str = '<td>' + elem
					
				})
			}
		}
		
	}
	
	
</script>
</head>
<body>

	<div>
		<h1>학생 관리</h1>
		<div>
			<input type="button" value="신규학생등록" id="btn_add ">
		</div>
	</div>
	<hr>
	<div>
		<form>
			<label for="ave">평균</label>
			<input type="text" value="begin" id="ave" name="begin" size="5" maxlength="3">
			~
			<input type="text" value="end" id="ave" name="end" size="5" maxlength="3">
			<input type="button" value="조회" id="select">
			<input type="button" value="전체조회" id="allselect">
		</form>
	</div>
	<hr>
	<hr>
		<div>
			<table border="1">
				<caption>전체 학생 <span id="student_member"></span>명</caption>
				<thead>
					<tr>
						<td>학번</td>
						<td>성명</td>
						<td>국어</td>
						<td>영어</td>
						<td>수학</td>
						<td>평균</td>
						<td>학점</td>
						<td>버튼</td>
					</tr>
				</thead>
				<tbody id="member_List"></tbody>
				
			</table>
		</div>
	


</body>
</html>