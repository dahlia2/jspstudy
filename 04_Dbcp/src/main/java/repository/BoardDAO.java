package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.BoardDTO;

public class BoardDAO {

	// 모든 메소드가 사용할 공통 필드
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// Connection 관리를 위한 DataSource 필드
	private DataSource dataSource;
	
	// Singleton Pattern으로 DAO 생성하기
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO() {    // 아무도 사용할 수 없게 생성자를 private 처리
		// context.xml에서 <Resource name="jdbc/GDJ61" />인 Resource를 읽어서 DataSource 객체 생성하기 (JNDI 방식)
		
		try {   // context와 맞추는 코드 (톰캣 사용을 위해)
			Context context = new InitialContext(); // ArrayList 처럼 구현받음
			Context envContext = (Context)context.lookup("java:comp/env");  // 톰캣 설정
			dataSource = (DataSource)envContext.lookup("jdbc/GDJ61");  // 진짜 이름(지정값이 있음)
			/*
		 	위에 3문장을 이렇게 쓸 수도 있음
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/GDJ61");
			*/
		
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static BoardDAO getInstance() {   // 클래스를 통한 메소드로 값을 가져갈 수 있도록 함
		return dao;
	}
	
	// 자원(Connection, PreparedStatement, ResultSet) 반납하기
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 목록 반환하기
	public List<BoardDTO> selectBoardList() {
		
		// 1. ArrayList 생성
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "SELECT BOARD_NO, TITLE, CONTENT, MODIFIED_DATE, CREATED_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. PreparedStatement 객체를 이용해 쿼리문 실행(SELECT문 실행은 executeQuery 메소드로 한다.)
			rs = ps.executeQuery();  // 받아오는 타입은 ResultSet  // rs의 결과 집합 = INSERT문 2개
			
			// 6. ResultSet 객체(결과 집합)를 이용해서 ArrayList를 만듬
			while(rs.next()) {
				
				// Step 1. Board 테이블의 결과 행(ROW)을 읽는다.
				int board_no = rs.getInt("BOARD_NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date modified_date = rs.getDate("MODIFIED_DATE");
				Date created_date = rs.getDate("CREATED_DATE");

				// Step 2. 읽은 정보를 이용해서 BoardDTO 객체를 만든다.  (읽은 결과 하나당 BoardDTO 객체로 만든다)
				BoardDTO board = new BoardDTO(board_no, title, content, modified_date, created_date);
				
				// Step 3. BoardDTO 객체를 ArrayList에 추가한다.
				boardList.add(board);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 발생 여부와 상관 없이 항상 자원의 반납을 해야 한다.
			close();
		}
		
		// 7. ArrayList 반환
		return boardList;
		
	}
	
	// 게시글 반환하기
	public BoardDTO selectBoardByNo(int board_no) {
		
		// 1. 반환할 BoardDTO board 선언
		BoardDTO board = null;
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "SELECT BOARD_NO, TITLE, CONTENT, MODIFIED_DATE, CREATED_DATE FROM BOARD WHERE BOARD_NO = ?";
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문의 변수 값 전달하기
			ps.setInt(1, board_no);  
			
			// 6. PreparedStatement 객체를 이용해 쿼리문 실행(SELECT문 실행은 executeQuery 메소드로 한다.)
			rs = ps.executeQuery();  // 받아오는 타입은 ResultSet  // rs의 결과 집합 = INSERT문 2개
			
			// 6. ResultSet 객체(결과 집합)를 이용해서 BoardDTO를 만듬
			// BOARD_NO은 기본키이기 때문에 값이 2개밖에(성공 1 실패 0) 없으므로 while문 대신 if문 사용. 
			if(rs.next()) {
				
				// Step 1. Board 테이블의 결과 행(ROW)을 읽는다.
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date modified_date = rs.getDate("MODIFIED_DATE");
				Date created_date = rs.getDate("CREATED_DATE");

				// Step 2. 읽은 정보를 이용해서 BoardDTO 객체를 만든다.  (읽은 결과 하나당 BoardDTO 객체로 만든다)
				board = new BoardDTO(board_no, title, content, modified_date, created_date);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 발생 여부와 상관 없이 항상 자원의 반납을 해야 한다.
			close();
		}
		// 8. 조회된 게시글 BoardDTO board 반환
		return  board;
		// return new BoardDTO(1, "제목", "내용", null, new Date(System.currentTimeMillis()));
		
		
	}
	
	
	
	
	// 게시글 삽입하기
	public int insertBoard(BoardDTO board) {
		
		// 1. 삽입 결과 변수 선언
		int insertResult = 0;
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문  (게시판번호, 타이틀, 내용, 최종수정일, 작성일)
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, NULL, SYSDATE)";  // 변수(타이틀, 내용) 처리할 때 물음표 처리
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문의 변수 값 전달하기
			ps.setString(1, board.getTitle());    // 1번째 물음표(?)에 title 전달하기 -> DTO의 객체 중 title
			ps.setString(2, board.getContent());  // 2번째 물음표(?)에 content 전달하기
			
			// 6. PreparedStatement 객체를 이용해 쿼리문 실행(INSERT문 실행은 excuteUpdate 메소드로 한다.)
			insertResult = ps.executeUpdate();  // 결과는 성공1, 실패0
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  // 예외가 발생하든 아니든 항상 실행되는 finally문
			
			// 예외 발생 여부와 상관 없이 항상 자원의 반납을 해야 한다.
			close();
		}
		
		// 7. 삽입 결과 반환
		return insertResult;
	}
	
	
	// 게시글 수정하기
	public int updateBoard(BoardDTO board) {
		
		// 1. 수정 결과 변수 선언
		int updateResult = 0;
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문 
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFIED_DATE = SYSDATE WHERE BOARD_NO = ?";  // 수정일은 지금 날짜로 지정  
			                                                                                              // 유니크한 특징을 갖고 있는 BOARD_NO로 조건문 형성
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문의 변수 값 전달하기
			ps.setString(1, board.getTitle());      // 1번째 물음표(?)에 title     전달하기
			ps.setString(2, board.getContent());	// 2번째 물음표(?)에 content   전달하기
			ps.setInt(3, board.getBoard_no());		// 3번째 물음표(?)에 board_no  전달하기
			
			// 6. PreparedStatement 객체를 이용해 쿼리문 실행(UPDATE문 실행은 excuteUpdate 메소드로 한다.)
			updateResult = ps.executeUpdate();  // 결과는 성공1, 실패0
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 7. 수정 결과 반환
		return updateResult;
		
	}
		
	
	// 게시글 삭제하기
	public int deleteBoard(int board_no) {
		
		// 1. 삭제 결과 변수 선언
		int deleteResult = 0;
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문 
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";  // 번호가 일치하면 삭제
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문의 변수 값 전달하기
			ps.setInt(1, board_no);  
			
			// 6. PreparedStatement 객체를 이용해 쿼리문 실행(DELETE문 실행은 excuteUpdate 메소드로 한다.)
			deleteResult = ps.executeUpdate();  // 결과는 성공1, 실패0
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {  // 예외가 발생하든 아니든 항상 실행되는 finally문
			
			// 예외 발생 여부와 상관 없이 항상 자원의 반납을 해야 한다.
			close();
		}
			return deleteResult;
		}
	
	}