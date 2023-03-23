package ex07_ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/Practice_self")
public class Practice_self extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// XMLServlet 연습하기
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클라이언트로부터 요청 받은 데이터 한글처리를 위해 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 요청 받은 파라미터
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String strPrice = request.getParameter("price");
		
		int price = 0;
		if(strPrice != null && strPrice.isEmpty() != false) {
			price = Integer.parseInt(strPrice);
		}
		
		// 응답보낼 XML 만들기 (JSon 객체를 만든 뒤에 xml로 변환한다.)
		
	
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
