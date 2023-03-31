package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import model.MyAgeService;
import model.MyBmiService;
import model.MyService;
import model.MyTodayService;


@WebServlet("*.do")   // .do로 끝나는 모든 요청
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 인코딩 & 응답 인코딩 (여러 서비스들에게 전달하기 전 미리 처리)
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset-UTF-8");
		
		// URLMapping 확인(todat.do, /age.do)
		String requestURI = request.getRequestURI();   					 /*  /03_Mvc/today.do */    
		String contextpath = request.getContextPath();  				 /*  /03_Mvc          */		
		String urlMapping= requestURI.substring(contextpath.length());   /*  /today.do		  */   
		
		// ActionForward 객체 선언하기
		ActionForward actionForward = null;
		
		// MyService 인터페이스 타입의 myService 객체 선언하기
		MyService myService = null;
		
		// URLMapping에 따른 모델(서비스) 생성하기
		/* if(urlMapping.equals("/today.do")) {
				myService = new MyTodayService();
			} else if(urlMapping.equals("/age.do")) {
				myService = new MyAgeService();
			} */
		
		switch (urlMapping) {
		case "/today.do":
			myService = new MyTodayService();
			break;
		case "/age.do" :
			myService = new MyAgeService();
			break;
		case "/bmi.do" :
			myService = new MyBmiService();
			break;
		}
		
		// 모델(서비스) 실행하기
		if(myService != null) {
			actionForward = myService.execute(request, response);
		}
		
		// 응답 View로 이동하기
		if(actionForward != null) {
			if(actionForward.isRedirect()) {
				response.sendRedirect(actionForward.getPath());
			} else {
				request.getRequestDispatcher(actionForward.getPath()).forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
