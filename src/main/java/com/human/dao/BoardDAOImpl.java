package com.human.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.human.vo.BoardVO;
import com.human.vo.PageVO;

@Repository     // dao단 임을 알려준다.
public class BoardDAOImpl implements IF_boardDAO{  // 구현받을 객체이름 뒤에 Impl 적어주면 됨
	// spring의 dao와 Mybatis sqlsession을 연결하는 객체
	private static String mapperQuery = "com.human.dao.IF_boardDAO"; // 매핑 인터페이스 경로 설정
	
	//Mybatis의 sqlsession객체가 필요하다.
	@Inject     // 컨테이너에서 가져와 자료형과 맞는 객체의 주소를 주입해준다.
	private SqlSession sqlSession;    // SqlSession 타입의 객체는 스프링 컨테이너에 있다.
									  // 여기서 이 객체가 필요하다. 그런데 컨테이너에서 가져와야한다. 스프링은 DI개념
									  // => 만들어진 객체 주입받는 것
	
	@Override
	public void insertOne(BoardVO boardvo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(mapperQuery+".insertOne",boardvo);  // 쿼리를 매핑 및 실행한다.
		 		//<SqlSession에서 지원해주는 함수 중 insert>   매핑정도              id       파라미터
		
	}

	@Override
	public List<BoardVO> selectAll(PageVO pageVO) throws Exception {
		// TODO Auto-generated method stub
		
		
		return sqlSession.selectList(mapperQuery+".selectAll", pageVO);
	}

	@Override
	public int countBoard() throws Exception {
		// TODO Auto-generated method stub
		
		return sqlSession.selectOne(mapperQuery+".countBoard");
	}

	@Override
	public void insertAttach(String filename) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(mapperQuery+".board_attach",filename);
	}

	@Override
	public BoardVO selectOne(String vno) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapperQuery+".selectOne",vno);    //mapper Id와 일치해야함
	}

	@Override
	public List<String> selectAttach(String vno) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapperQuery+".selectAttach",vno);
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(mapperQuery+".boardUpdate",boardVO);
	}  

}
