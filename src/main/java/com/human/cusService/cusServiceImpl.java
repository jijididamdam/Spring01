package com.human.cusService;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.human.cusDAO.IF_cusDAO;
import com.human.vo.cusVO;

@Service
public class cusServiceImpl implements IF_cusService{
	@Inject
	private IF_cusDAO cdao;

	@Override
	public void put(cusVO cvo) throws Exception {
		// TODO Auto-generated method stub
		cdao.put(cvo);
		
	}

}
