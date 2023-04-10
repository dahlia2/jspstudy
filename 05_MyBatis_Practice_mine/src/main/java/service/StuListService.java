package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StuDAO;

public class StuListService implements IStuService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("StuList", StuDAO.getInstance().selectAllStuList());
		return new ActionForward();
	}

}
