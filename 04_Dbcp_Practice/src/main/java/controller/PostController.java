package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IPostService;
import service.PostDeleteService;
import service.PostDetailService;
import service.PostListService;
import service.PostSaveService;


@WebServlet("*.post")  // 목록보기 list.post  상세보기 detail.post  저장하기 save.post  변경하기 change.post  편집하기 edit.post  삭제하기 delete.post
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청, 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
	    // urlMapping
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// 서비스 타입 선언
		IPostService service = null;
		
		// forward 경로
		String path = null;
		
		// urlMapping에 따른 서비스 선택(생성)
		switch(urlMapping) {
		case "/list.post" :
			service = new PostListService();
			break;
		case "/save.post" :
			service = new PostSaveService();
			break;
		case "/write.post" :  // 서비스가 필요하지 않은 post  // 서비스는 null path는 write뷰로 이동
			path = "post/write.jsp"; 
			break;
		case "/detail.post":
			service = new PostDetailService();
			break;
		case "/delete.post":
			service = new PostDeleteService();
			break;
		}
		
		// 선택된 서비스 실행
		if(service != null) {
			try {
				// redirect가 필요한 서비스(삽입, 수정, 삭제)는 서비스 내에서 직접 redirect하고(location.href를 이용) path에 null을 반환한다.
				// 직접 이동하려면 location을 사용. 거기서 alert창을 띄우기 위해서
				path = service.execute(request, response);  // execute 메소드는 때문에 try-catch문이 필요하다.
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		
		// 이동할 경로(path)로 forward
		if(path != null) {
			request.getRequestDispatcher(path).forward(request, response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
