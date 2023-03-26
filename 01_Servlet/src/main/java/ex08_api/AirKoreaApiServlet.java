package ex08_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/AirKoreaApiServlet")
public class AirKoreaApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 요청받은 파라미터 꺼내기
		String sidoName = request.getParameter("sidoName");
		String returnType = request.getParameter("returnType");
		
		
		// 서비스키 (개인 API 인증키, 디코딩 상태)
		String serviceKey = "gj7dS2Er/XeESIZFV3uS5NLHQjEioW2Om5WzKKdNH43iCrt0OR29TPpQr1bSs3ak4sqDUl7a7JYI3b3cCxVeSg==";
		
		// 요청 주소
		String apiURL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		// 파라미터 이어붙이기 작업
		apiURL += "?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");
		apiURL += "&sidoName=" + URLEncoder.encode(sidoName, "UTF-8");
		apiURL += "&returnType=" + URLEncoder.encode(returnType, "UTF-8");  // 영어라서 인코딩 필수는 아니지만, 통일을 위해 하는 걸 권장
		
		// URL
		URL url = new URL(apiURL);
		
		// HttpURLConnection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		// 요청메소드
		con.setRequestMethod("GET"); // 대문자로 받아야 한다
		
		// returnType에 따른 Content-Type
		con.setRequestProperty("Content-Type", "application/" + returnType + "; charset=UTF-8");
		
		// 입력 스트림 생성
		BufferedReader reader = null;
		if(con.getResponseCode() == 200) {
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		// 입력 (API의 응답 결과를 StringBulder에 저장)
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = reader.readLine()) != null) {  // reader가 한 줄씩 읽어온 값이 null이 아니면 이어붙여줌
			sb.append(line);
		}
		
		// 입력 스트림, 접속 종료
		reader.close();
		con.disconnect();
		
		System.out.println(sb.toString());
		
		// API 결과를 ajax 응답 처리하기
		response.setContentType("application/" + returnType + "; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());

	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
