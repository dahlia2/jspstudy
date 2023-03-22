package ex07_ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.json.JSONObject;


@WebServlet("/JSONServlet")
public class JSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// 요청 인코딩
			request.setCharacterEncoding("UTF-8");
			
			// 요청 파라미터
			String name = request.getParameter("name");
			String strAge = request.getParameter("age");
			int age = 0;
			if(strAge != null && strAge.isEmpty() == false) {
				age = Integer.parseInt(strAge);
			}
			
			/*
			 if(age < 0 || age > 100) { throw (age + "살은 잘못된 나이입니다."); }
			 
			 if(name.length() < 2 || name.length() > 6) { (name + "은 잘못된 이름입니다.") }
			*/
			
			// 응답할 JSON 데이터
			JSONObject obj = new JSONObject();		
			obj.put("name", name);
			obj.put("age", age);
			// System.out.println(obj.toString());  // {"name": "마돈나", "age": 50}
			
			// 응답할 데이터 타입
			response.setContentType("application/json; charset=UTF-8");
			
			// 출력 스트림 생성
			PrintWriter out = response.getWriter();
			
			// 출력
			String resData = obj.toString();
			out.println(resData); // 텍스트 형식으로 된 JSON 데이터
			out.flush();
			out.close();
			
		} catch (NumberFormatException e) {
			response.setContentType("application/json; charset=UTF-8");
			response.setStatus(600);
			
			PrintWriter out = response.getWriter();
		}
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
