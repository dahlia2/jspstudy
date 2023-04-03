package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardAddService;
import service.BoardDetailService;
import service.BoardListService;
import service.BoardModifyService;
import service.BoardRemoveService;
import service.IBoardService;

@WebServlet("*.do")  // 받을 mapping 목록 : getAllBoardList.do  getBoardByNo.do  writeBoard.do(작성화면)  addBoard.do(작성완료)  modifyBoard.do  removeBoard.do
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청과 응답의 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// URLMapping 확인
		String requestURI = request.getRequestURI();                    /*    /04_Dbcp/getAllBoardList.do    */
		String contextPath = request.getContextPath();                  /*    /04_Dbcp                       */
		String urlMapping = requestURI.substring(contextPath.length()); /*    /getAllBoardList.do            */   //contextPath.length = 프로젝트 이름
		
		// 모든 서비스의 공통 타입 선언
		IBoardService service = null;
		
		// ActionForward 선언
		ActionForward af = null;
		
		// URLMapping에 따른 서비스 생성 (요청에 따른 서비스 생성)
		switch(urlMapping) {
		case "/getAllBoardList.do":   // 실무에서는 getAllBoard's'라고 쓰는 경우가 많음
			service = new BoardListService();
			break;
		case "/getBoardByNo.do":
			service = new BoardDetailService();
			break;
		case "/addBoard.do":
			service = new BoardAddService();
			break;
		case "/modifyBoard.do":
			service = new BoardModifyService();
			break;
		case "/removeBoard.do":
			service = new BoardRemoveService();
			break;
		case "/writeBoard.do":  // 작성화면은 데이터가 필요하지 않기에 service 생성을 하지 않는다.  -> service은 null값
			af = new ActionForward("board/write.jsp", false);  // board 폴더 아래 write.jsp로 forward한다. (단순 이동인 경우 forward한다.)
			break;  
		}
		
		// 서비스 실행
		if(service != null) {   // IOException을 피하기 위해서 (null 처리)  // writeBoard.do은 서비스가 없어도 if문 덕분에 요청과 응답이 생김
			af = service.execute(request, response);  // 어디로 어떻게 이동할 건지 af에 저장
		} 
		
		// 응답View로 이동
		if(af != null) {  // IOException을 피하기 위해서 (null 처리)
			if(af.isRedirect()) {
				response.sendRedirect(af.getPath());
			} else {
				request.getRequestDispatcher(af.getPath()).forward(request, response);
			}
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}