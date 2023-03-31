package model;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class MyBmiService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("height"));
		double height = Double.parseDouble(opt1.orElse("0"));  // null값이면 0으로 값적용
		
		Optional<String> opt2 = Optional.ofNullable(request.getParameter("weight"));
		double weight = Double.parseDouble(opt2.orElse("1"));
		
		double bmi = weight/(height * height * 0.0001);
		
		String health = "";
		if(bmi < 20) {
			health = "저제충";
		} else if (bmi < 25) {
			health = "정상";
		} else if (bmi < 35) {
			health = "비만";
		} else {
			health = "알수없음";
		}
		
		request.setAttribute("bmi", bmi);
		request.setAttribute("health", health);
		
		// 어디로 어떻게 갈 것인가?
		ActionForward actionForward = new ActionForward();
		actionForward.setPath("view/output.jsp");
		actionForward.setRedirect(false);
		return actionForward;
		
	}

}
