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
		//[1] �θ��(����)�� �۹�ȣ(idx)�� �θ���� refer, lev, sunbun��������
		//==>select��
		BoardVO parent =this.boardDao.selectRefLevSunbun(board.getIdx());
		log.info("parent===="+parent);//�θ���� refer,lev,sunbun
		
		//[2] ������ �̹� �޸� �亯�۵��� �ִٸ� sunbun�� �ϳ���
		//    �ڷ� �о�� (���� �� �亯���� ������� �ϹǷ�)
		/*
		 * 3. ���Դϴ�.						 [refer:3, lev: 0   sunbun:0]
		 * 		+-[RE]���Դϴ�.              [refer:3, lev: 1   sunbun:1]
		 * 
		 * 			+-[RE][RE]���Դϴ�.      [refer:3, lev: 2   sunbun:2]
		 * 
		 * 
		 * */
		int n2=this.boardDao.updateSunbun(parent);
		
		
		//[3] �亯�� ���� (������ �亯���� insert)
		/* �� �� refer�� �θ���� �۱׷��ȣ(refer)�� �����ϰ�
		 *       lev  �� �θ���� lev���� 1ũ��
		 *       sunbun�� �θ���� sunbun���� 1ũ��
		 * */
		board.setRefer(parent.getRefer());//�θ��� �۱׷��ȣ�� �����ϰ� ����
		board.setLev(parent.getLev()+1);//�θ��� lev+1
		board.setSunbun(parent.getSunbun()+1);//�θ��� sunbun+1
		
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
