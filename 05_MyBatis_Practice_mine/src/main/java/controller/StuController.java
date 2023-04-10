package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.IStuService;
import service.StuAddService;
import service.StuDetailService;
import service.StuListService;
import service.StuModifyService;
import service.StuRemoveService;


@WebServlet("*.mbs")
public class StuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String requestURL = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURL.substring(contextPath.length());
		
		IStuService service = null;
		ActionForward af = null;
		
		switch (urlMapping) {
		case "/list.do" :
			service = new StuListService();
			break;
		case "/detail.do" :
			service = new StuDetailService();
			break;
		case "/write.do" :
			af = new ActionForward("view/write.jsp", false);
		break;
		case "/add.do" :
			service = new StuAddService();
			break;
		case "/modify.do" :
			service = new StuModifyService();
			break;
		case "/remove.do" :
			service = new StuRemoveService();
			break;
		}
		
		
		if(service != null) {
			af = service.execute(request, response);
		}
		
		
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getPath());	
			} else {
				request.getRequestDispatcher(af.getPath());  
			}
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
