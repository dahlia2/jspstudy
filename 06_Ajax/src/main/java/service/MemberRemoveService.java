package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import repository.MemberDAO;

public class MemberRemoveService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		int deleteResult = MemberDAO.getInstance().deleteMember(memberNo);
		
		response.setContentType(null);
	}

}
