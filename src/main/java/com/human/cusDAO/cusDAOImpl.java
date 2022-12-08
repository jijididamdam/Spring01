package com.human.cusDAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.human.vo.cusVO;

@Repository
public class cusDAOImpl implements IF_cusDAO {

	private static String mquery = "com.human.cusDAO.IF_cusDAO";
	
	@Inject
	private SqlSession sql;
	
	@Override
	public void put(cusVO cvo) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(mquery+".put",cvo);
		
	}

}
