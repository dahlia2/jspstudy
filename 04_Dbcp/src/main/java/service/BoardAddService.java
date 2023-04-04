package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardAddService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 여기서 request는 wirte.jsp에서 요청한 것
		
		// 1. 요청 파라미터
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 2. BoardDTO 객체 생성  -> title와 content 객체를 하나로 모음 (get set대신 Map 활용할 수 있음)
		BoardDTO board = new BoardDTO();
		board.setTitle(title);
		board.setContent(content);
		System.out.println(board.getTitle());
		System.out.println(board.getContent());
		// 3. 삽입을 위해서 DB로 BoardDTO를 전달 (BoardDAO의 insertBoard 메소드)
		// (서비스가 직접 DB로 가지 않음. 데이터베이스에 접근할 수 있는 건 BoardDTO)
		int insertResult = BoardDAO.getInstance().insertBoard(board); // 성공시 1, 실패시 0
		System.out.println(insertResult);
		
		// 4. 어디로 and 어떻게 이동
		return new ActionForward("board/list.jsp", true);
	}

}
