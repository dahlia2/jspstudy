package model;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class MyTodayService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) { // 반환값이 있기 때문에 void가 올 수 없음
			request.setAttribute("today", new Date(System.currentTimeMillis()));  // sql패키지의 Date
			
			// 어디로 어떻게 갈 것인가?
			ActionForward actionForward = new ActionForward();
			actionForward.setPath("view/output.jsp");
			actionForward.setRedirect(false);
			return actionForward;
	}

}
