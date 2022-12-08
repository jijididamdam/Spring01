package com.human.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.human.dao.IF_boardDAO;
import com.human.vo.BoardVO;
import com.human.vo.PageVO;
@Service
public class BoardServiceImpl implements IF_boardService {
	@Inject
	private IF_boardDAO boarddao;
	
	@Override
	public void insertOne(BoardVO boardvo) throws Exception {
		// TODO Auto-generated method stub
		//board table insert
		//board_attach table insert
		
		boarddao.insertOne(boardvo);
		String[] fname = boardvo.getFiles();
		if(fname != null) {
			//board_attach table insert
			for(int i=0; i < fname.length; i++) {
				if(fname[i]!=null) {
				boarddao.insertAttach(fname[i]);
				}
			}
		}
	}

	@Override
	public List<BoardVO> selectAll(PageVO pageVO) throws Exception {   // 컨트롤러가 접근하는 메서드
		// TODO Auto-generated method stub
		return boarddao.selectAll(pageVO);          // 구현기능에서 dao로 접근
	}
	@Override
	public int countBoard() throws Exception {
		return boarddao.countBoard(); 
	}
	@Override
	public BoardVO selectOne(String vno) throws Exception {
		// TODO Auto-generated method stub
		return boarddao.selectOne(vno);
	}

	@Override
	public List<String> selectAttach(String vno) throws Exception {
		// TODO Auto-generated method stub
		return boarddao.selectAttach(vno);
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		boarddao.updateBoard(boardVO);
		
	}

}
