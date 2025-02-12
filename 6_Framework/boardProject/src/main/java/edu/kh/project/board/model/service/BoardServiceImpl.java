package edu.kh.project.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.board.model.dao.BoardDAO;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.Pagination;
import edu.kh.project.member.model.dto.Member;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO dao;
	
	// 게시판 종류 목록 조회
	@Override
	public List<Map<String, Object>> selectBoardTypeList() {
		return dao.selectBoardTypeList();
	}

	
	
	//게시글 목록 조회
	@Override
	public Map<String, Object> selectBoardList(int boardCode, int cp) {
		//1.특정 게시판의 삭제되지 않은 게시글 수 조회
		
		int listCount = dao.getListCount(boardCode);
		System.out.println(listCount);
		
		//2.1번의 조회 결과 + cp를 이용해서 Pagination 객체 생성
		// -> 내부 필드에 게산된 값 초기화됨
		Pagination pagination = new Pagination(cp,listCount);
		
		//3.특정 게시판에서 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// ->어떤 게시판(boardCode)에서
		// 	 몇 페이지(pagination.currentPage)에 대한
		// 	 게시글 몇 개(pagination.limit) 조회
		
		List<Board> boardList= dao.selectBoardList(boardCode,pagination);
		System.out.println(boardList);
		//.pagination,boardList를 Map에 담아서 반환
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardList", boardList);
		map.put("pagination", pagination);
		
		return map;
		
		
		
		
	}


//게시글 상세 조회
	@Override
	public Board selectBoard(Map<String, Object> map) {
		return dao.selectBoard(map);
	}


/*
	@Override
	public int boardLikeCheck(int boardNo, Member loginMember) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardNo", boardNo);
		map.put("memberNo", loginMember.getMemberNo());
		
		int result=dao.boardLikeCheck(map);
		return result;
	}
	*/


 //좋아요 여부 확인
	@Override
	public int boardLikeCheck(Map<String, Object> map) {
		return dao.boardLikeCheck(map);
	}

















}
