package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BbsDTO;
import repository.BbsDAO;

public class BbsAddService implements IBbsService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// request에 있는 파라미터 title과 content
		
		BbsDTO bbs = new BbsDTO();
		bbs.setTitle(request.getParameter("title"));
		bbs.setContent(request.getParameter("content"));
		
		int insertResult = BbsDAO.getInstance().insertBbs(bbs);
		
		try {
			
			PrintWriter out = response.getWriter();
			if(insertResult == 1) {
				out.println("<script>");
				out.println("alert('BBS가 등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/list.do'");
				out.println("</script>");
				out.flush();
				out.close();
				return null; // BBS Controller을 통한 이동 금지 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// BBS 등록 실패
		return new ActionForward(request.getContextPath() + "/list.do", true);
	}

}
