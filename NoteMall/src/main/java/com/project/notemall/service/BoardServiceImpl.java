package com.project.notemall.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.notemall.dao.BoardDAO;
import com.project.notemall.domain.BoardVO;
import com.project.notemall.domain.PagingVO;

import lombok.extern.log4j.Log4j;

@Service("boardServiceImpl")
@Log4j
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO boardDao;

	@Override
	public int insertBoard(BoardVO board) {		
		return this.boardDao.insertBoard(board);
	}

	@Override
	public List<BoardVO> selectBoardAll(Map<String, Integer> map) {
		return this.boardDao.selectBoardAll(map);
	}

	@Override
	public List<BoardVO> selectBoardAll(PagingVO paging) {
		return this.boardDao.selectBoardAll(paging);////////
	}

	@Override
	public List<BoardVO> findBoard(PagingVO paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount() {
		return this.boardDao.getTotalCount();
	}

	@Override
	public int getTotalCount(PagingVO paging) {
		return this.boardDao.getTotalCount(paging);/////////
	}

	@Override
	public BoardVO selectBoardByIdx(Integer idx) {
		return this.boardDao.selectBoardByIdx(idx);
	}

	@Override
	public int updateReadnum(Integer idx) {
		return this.boardDao.updateReadnum(idx);
	}

	@Override
	public String selectPwd(Integer idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBoard(Integer idx) {
		return this.boardDao.deleteBoard(idx);
	}

	@Override
	public int updateBoard(BoardVO board) {
		return this.boardDao.updateBoard(board);
	}

	@Override
	public int rewriteBoard(BoardVO board) {
		//[1] 부모글(원글)의 글번호(idx)로 부모글의 refer, lev, sunbun가져오기
		//==>select문
		BoardVO parent =this.boardDao.selectRefLevSunbun(board.getIdx());
		log.info("parent===="+parent);//부모글의 refer,lev,sunbun
		
		//[2] 기존에 이미 달린 답변글들이 있다면 sunbun을 하나씩
		//    뒤로 밀어내기 (내가 쓴 답변글이 끼어들어야 하므로)
		/*
		 * 3. 글입니다.						 [refer:3, lev: 0   sunbun:0]
		 * 		+-[RE]글입니다.              [refer:3, lev: 1   sunbun:1]
		 * 
		 * 			+-[RE][RE]글입니다.      [refer:3, lev: 2   sunbun:2]
		 * 
		 * 
		 * */
		int n2=this.boardDao.updateSunbun(parent);
		
		
		//[3] 답변글 쓰기 (내가쓴 답변글을 insert)
		/* 이 때 refer는 부모글의 글그룹번호(refer)와 동일하게
		 *       lev  은 부모글의 lev보다 1크게
		 *       sunbun도 부모글의 sunbun보다 1크게
		 * */
		board.setRefer(parent.getRefer());//부모의 글그룹번호와 동일하게 설정
		board.setLev(parent.getLev()+1);//부모의 lev+1
		board.setSunbun(parent.getSunbun()+1);//부모의 sunbun+1
		
		return this.boardDao.rewriteBoard(board);
	}

	@Override
	public BoardVO selectRefLevSunbun(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateSunbun(BoardVO parent) {
		// TODO Auto-generated method stub
		return 0;
	}

}
