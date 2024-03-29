package service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO; 

public class MemberListService implements IMemberService {  
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		// 하나의 서비스는 여러 개의 DAO 메소드를 호출할 수 있다.  => 전체 목록과 전체 회원 수를 가지고 온다.
		MemberDAO dao = MemberDAO.getInstance();
		List<Member> memberList = dao.selectAllMembers();
		int memberCount = dao.getMemberCount();
		
		// 응답할 JSON 데이터 만들기
		/*
		 자바스크립트 객체 {}, 배열 []
			{  // 마지막은 괄호로 묶여있으니 배열 jsonObject
				"memberCount": 2,
				"memberList": [
					{
						"memberNo": 1,
						"id": "회원아이디",
						"name": "회원명",
						"gender": "회원성별",
						"address": "회원주소"
					},
					{
						"memberNo": 1,
						"id": "회원아이디",
						"name": "회원명",
						"gender": "회원성별",
						"address": "회원주소"
					}
				]  // 이와 같은 JSON 데이터를 만들어서 응답
			}
		*/
		
		JSONObject obj = new JSONObject();
		obj.put("memberCount", memberCount);
		obj.put("memberList", memberList); // JSON라이브러리가 Java의 ArrayList를 JavaScript의 배열([])로 바꾸고, Java의 Member 객체를 JavaScript의 객체({})로 바꾼다.
		
		// 응답 (요청한 곳으로 그대로 응답된다. 즉 ajax() 메소드로 응답 처리된다.)
		response.setContentType("application/json; charset=UTF-8"); 	// json의 응답 타입. 암기 필수
		PrintWriter out = response.getWriter();
		out.println(obj.toString()); // JSON 데이터를 텍스트 형식으로 변경해서 반환하기
		out.flush();
		out.close();
	}

}
