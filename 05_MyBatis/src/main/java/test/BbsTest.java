package test;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.BbsDTO;
import repository.BbsDAO;

public class BbsTest {

	/*
		1. JUnit4 라이브러리
			1) junit4-4.13.2.jar
			2) hamcrest-core-1.3.jar
		
		2. JUnit4 단위 테스트 수행 방법
			1) test 패키지를 만들고 JUnit Test Case 파일을 추가한다.
			2) 테스트 메소드를 만든다.
			3) [Run As] - [JUnit Test]를 실행한다.
		
		3. JUnit4 주요 테스트 애너테이션
			1) @Test      : 단위 테스트를 수행하는 메소드
			2) @Before    : 단위 테스트(@Test) 수행 이전에 실행하는 메소드
			3) @After     : 단위 테스트(@Test) 수행 이후에 실행하는 메소드
			4) @BeforeAll : 단위 테스트 케이스(BbsTest.java) 수행 이전에 실행하는 메소드, static 처리 필요
			5) @AfterAll  : 단위 테스트 케이스(BbsTest.java) 수행 이후에 실행하는 메소드, static 처리 필요
	*/
	
	// BbsDAO의 메소드 단위로 테스트를 진행한다.
	private BbsDAO dao = BbsDAO.getInstance();
	
	//@Test
	public void 목록테스트() {     // 혼자 테스트 할 용도로 만든 메소드니까 한글 사용 O
		assertEquals(2, dao.selectAllBbsList().size());
	}

	//@Test
	public void 상세테스트() {
		assertNotNull(dao.selectBbsByNo(1));
	}
	
	//@Test
	public void 삽입테스트() {
		BbsDTO bbs = new BbsDTO();
		bbs.setTitle("테스트제목");
		bbs.setContent("테스트내용");
		assertEquals(1, dao.insertBbs(bbs));
	}
	
	//@Test
	public void 수정테스트() {
		BbsDTO bbs = new BbsDTO();
		bbs.setBbsNo(1);  // BBS_NO=1 수정
		bbs.setTitle("[수정]테스트제목");
		bbs.setContent("[수정]테스트내용");
		assertEquals(1, dao.updateBbs(bbs));
	}
	
	@Test
	public void 삭제테스트() {
		assertEquals(1, dao.deleteBbs(1));  // BBS_NO=1 삭제
	}
	
}