package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.StuDTO;

public class StuDAO {
	
	private SqlSessionFactory factory;
	
	private static StuDAO dao = new StuDAO();
	
	private StuDAO () {
		
		try {
			
			String resSource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resSource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static StuDAO getInstance() { 
		return dao;
	}
	
	private final String NS = "mybatis.mapper.stu.";
		
	// 전체 목록
	public List<StuDTO> selectAllStuList(){
		SqlSession ss = factory.openSession();
		List<StuDTO> stuList = ss.selectList(NS + "selectAllStuList");
		ss.close();
		return stuList;
	}
	
	// 상세
	private StuDTO selectStuByNo(int stuNo) {
		SqlSession ss = factory.openSession();  // 자동 commit
		StuDTO stu = ss.selectOne(NS + "selectStuByNo", stuNo);
		ss.close();
		return stu;
	}
	
	// 삽입
	public int insertStu(StuDTO stu) {
		SqlSession ss = factory.openSession(false); // 직접 commit
		int insertResult = ss.insert(NS + "insertStu", stu);
		if(insertResult == 1) {
			ss.commit();
		}
		ss.close();
		return insertResult;
	}
	
	
	
	
}