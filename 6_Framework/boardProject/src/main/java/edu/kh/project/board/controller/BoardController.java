package edu.kh.project.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.service.BoardService;
import edu.kh.project.member.model.dto.Member;

@Controller
@RequestMapping("/board")
@SessionAttributes("loginMember")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	/* 목록 조회 : /board/1?cp=1 (cp : current page, 현재페이지)
	 * 상세 조회 : /board/1/1500?cp=1
	 * 
	 * ** 컨트롤러 별도 생성 예정 **
	 * 삽입 : /board2/1/insert (code == BOARD_CODE, 게시판 종류)
	 * 수정 : /board2/1/1500/update (no == BOARD_NO, 게시글 번호)
	 * 삭제 : /board2/1/1500/delete
	 * */
	
	
	
	// @PathVariable : URL 경로에 있는 값을 매개변수로 이용할 수 있게 하는 어노테이션
	//					+ request scope에 세팅
	
	// @PathVariable을 사용하는 경우
	// -자원(resource) 구분 /식별
	// ex) github.com/userId
	// ex) /board/1 -> 1번(공지사항) 게시판
	// ex) /board/2 -> 2번(자유 게시판) 게시판
	
	
	// Query String을 사용하는 경우
	// -정렬, 필터링
	// ex) search.naver.com?query=날씨
	// ex) search.naver.com?query=점심
	// ex) /board2/insert?code=1
	// ex) /board2/insert?code=2
	// -> 삽입이라는 공통된 동작 수행
	//	  단, code에 따라 어디에 삽입할지 지정(필터링)
	
	// ex) /board/list?order=recent(최신순)
	// ex) /board/list?order=most(인기순)
	
	//게시글 목록 조회
	@GetMapping("/{boardCode}")
	public String selectBoardList(@PathVariable("boardCode") int boardCode
			,@RequestParam(value="cp",required=false,defaultValue="1") int cp
			,Model model) {
		
		//boardCode 확인
		//System.out.println("boardCode:"+boardCode);
		
		
		//게시글 목록 조회 서비스 호출
		Map<String, Object> map = service.selectBoardList(boardCode,cp);
		

		// 조회 결과를 request scope에 세팅 후 forward
		model.addAttribute("map",map);
		
		return "board/boardList";
	}
	
	//게시글 상세조회
	@GetMapping("/{boardCode}/{boardNo}")
	public String boardDetail(@PathVariable("boardCode") int boardCode ,
							@PathVariable("boardNo") int boardNo,
							Model model /*데이터 전달용 객체*/
							,RedirectAttributes ra // 리다이렉트 시 데이터 전달용 객체
							, @SessionAttribute(value="loginMember", required=false) Member loginMember
								//세션에서 loginMember 얻어와서 회원 정보 저장, 없는 경우 null
							) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		
		//게시글 상세 조회 서비스 호출
		Board board = service.selectBoard(map);
		String path;
		if(board!=null) { //조회 결과가 있을 경우
			//-----------------------------------------------
			// 현재 로그인한 상태인 경우
			// 로그인한 회원이 해당 게시글에 좋아요를 눌렀는지 확인
			
			if(loginMember!=null) {
				// 좋아요 여부 확인 서비스 호출
			//	int result = service.boardLikeCheck(boardNo,loginMember);
			//	model.addAttribute("result",result);
				
				map.put("memberNo", loginMember.getMemberNo());
				
				int result=service.boardLikeCheck(map);
				
				// 좋아요를 누른 적이 있을 경우
				if(result>0) {
					model.addAttribute("likeCheck","yes");
				}
			}
			//-----------------------------------------------
			 path = "board/boardDetail"; 			//boardDetail 페이지
			 model.addAttribute("board", board);
			
		}else {//조회 결과가 없을 경우
			path = "redirect:/board/"+ boardCode; // 해당 게시판 목록 첫 페이지
			ra.addFlashAttribute("message","해당 게시글이 존재하지 않습니다.");
			
		}
		
		return path;
	}
	
	
	
	
	
	
	
	

}
