package practice07;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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


@WebServlet("/PapagoServlet")
public class PapagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String source = request.getParameter("source");  // 원본 언어 코드 (ko, en, ja 중 하나)
		String target = request.getParameter("target");  // 목적 언어 코드 (ko, en, ja 중 하나)
		String text = request.getParameter("text"); 	 // 번역할 테스트
		
		// 클라이언트 아이디, 시크릿 (네이버개발자센터에서 발급 받은 본인 정보 사용)
		String clientId = "QiCcDlsw4QIrGip09ROG";
		String clientSecret = "J58f0EAvhE";
		
		// API 주소
		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		
		// URL
		URL url = new URL(apiURL);
		
		// HttpURLConnection
		HttpURLConnection con = (HttpURLConnection) url.openConnection(); // URL 객체가 openConnection한 걸 HttpURLConnection으로 변환
		
		// 요청 메소드
		con.setRequestMethod("POST");
		
		// 요청 헤더에 포함하는 내용
		con.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		
		// Papago API로 보내야 하는 파라미터(source, target, text)
		String params = "source=" + source + "&target=" + target + "&text=" + URLEncoder.encode(text, "UTF-8");
		
		// Papago API로 파라미터 보내기 위해서 출력 스트림 생성
		con.setDoOutput(true); 
		DataOutputStream dos = new DataOutputStream(con.getOutputStream());
		
		// Papago API로 파라미터 보내기
		dos.write(params.getBytes()); // String은 받지 못하니까 bytes으로 변환
		dos.flush();
		dos.close();
		
		// Papago API로부터 번역 결과를 받아 올 입력 스트림 생성
		BufferedReader reader = null;
		if(con.getResponseCode() == 200) {
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		// Papago API로부터 번역 결과를 받아서 StringBuilder에 저장
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = reader.readLine()) != null) {  // reader가 한줄씩 읽어드린 걸 line에 저장하고 그 값이 null이 아닐 때
			sb.append(line);
		}
		
		// StringBuilder의 번역 결과를 client.html의 ajax으로 보내기
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());    // StringBuilder 객체를 String으로 변환
		out.flush();
		out.close();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
