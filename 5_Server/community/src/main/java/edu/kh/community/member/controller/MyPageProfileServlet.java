package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.community.common.MyRenamePolicy;
import edu.kh.community.member.model.dto.Member;
import edu.kh.community.member.model.service.MemberService;

@WebServlet("/member/myPage/profile")
public class MyPageProfileServlet extends HttpServlet{
	
	// 메인페이지 -> 프로필 이미지 클릭
	// 마이페이지->  프로필 클릭
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/myPage-profile.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	
	//프로필 이미지 업로드-> 변경
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
//		System.out.println(req.getParameter("profileImage")); // null
			
			//파라미터가 얻어와지지 않는 이유
			
			//1. enctype="multipart/form-data" -> 인코딩이 안되어 있어 파라미터 인지X
			//2. input type="file" -> 파일 형태의 데이터
			
			// -> multipart/form-data 형식의 요청을 처리할 수 있는 전용 Request 객체가 필요
			
			// --> MultipartRequest (cos.jar 라이브러리 이용 - http://servlets.com/)
			//						 파일 업로드 라이브러리
			
			//*********************MultipartRequest 사용을 위한 준비 단계 *****************
			
			// 1. 업로드 되는 파일 크기의 전체 합을 지정(byte 단위 지정)
			int maxSize = 1024 *1024 * 20;
			// 1KB   1MB   20MB
			
			// 2. 업로드 되는 파일이 어디에 저장될지 경로 지정
			//	  -> 서버 컴퓨터 내부 폴더(절대 경로)
			
			// 2-1. server option 확인
			// servers -> 서버 설정 선택 -> Overview -> server options
			// -> Serve Modules without publising 체크
			// (업로드 되는 파일이 webapp 폴더 내부에 저장 될 수 있음)
			
			// 2-2. memberProfile 폴더까지의 절대 경로 얻어오기
			HttpSession session = req.getSession();
			
			// 최상위 경로("/" == webapp폴더)의 컴퓨터상의 실제 절대 경로를 얻어옴
			String root = session.getServletContext().getRealPath("/");
			// C:\workspace\5_Server\community\src\main\webapp
			
			//실제 파일이 저장되는 폴더의 경로
			String folderPath ="/resources/images/memberProfile/";
			
			// memberProfile 폴더 까지의 절대경로
			String filePath = root + folderPath;
			
			// 3. 저장되는 파일의 파일명 중복 방지를 위한 파일명 변경 규칙
			// -> cos.jar 에서 제공하는 FileRenamePolicy 인터페이스 상속 클래스 생성
			// --> MyRenamePolicy 클래스 생성
			
			
			// 4. 파일 이외의 파라미터들의 문자 인코딩 지정
			String encoding = "UTF-8";
			
			// 5. MultipartRequest 생성
			//*** 중요 ***
			//MultipartRequest 객체가 생성됨과 동시에 지정된 경로에
			//지정된 파일명 변경 정책에 맞게 이름이 바뀐 파일이 저장(서버에 업로드)된다
			
			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
			
			//프로필 이미지 변경 Service 호출 시 필요한 값
			
			// 1) 로그인한 회원의 회원 번호
			Member loginMember = (Member)session.getAttribute("loginMember");
			int memberNo = loginMember.getMemberNo();
			
			// 2) 업로드된 프로필 이미지의 웹 접근 경로(folderPath + 변경된 파일명)
			// -> DB에 삽입될 프로필 이미지 경로
			// -> 단, X버튼이 클릭된 상태면 null 가지게 한다.
			String profileImage = folderPath + mpReq.getFilesystemName("profileImage");
			// 원본 파일명 : getOriginalFileName("input type='file' 의 name 속성 값")
//		System.out.println(mpReq.getOriginalFileName("profileImage"));
			
			// 변경된 파일명 : getOriginalFileName("input type='file' 의 name 속성 값")
//		System.out.println(mpReq.getFilesystemName("profileImage"));
			
			
			
			// ** 프로필 이미지 삭제 **
			// 1) delete(input type='hidden') 태그의 값(파라미터) 얻어오기 
			// (주의!!) multipart/form-data 형식의 요청을 처리중 이기 때문에
			// req를 이용해서 파라미터를 얻어올 수 없다!
			// -> 해결 방법 : mpReq 이용
			int delete = Integer.parseInt(mpReq.getParameter("delete"));
			
			// 2) delete의 값이 1(눌러진 경우)이면 profileImage 값을 null로 변경
			if(delete ==1) profileImage=null;		
			
			
			
			
			//프로필 이미지 변경 Service 호출 후 결과 반환 받기
			
			//성공 시 : "프로필 이미지가 변경되었습니다." 알림창
			//실패 시 : "프로필 이미지가 변경실패." 알림창
			MemberService service = new MemberService();
			int result = service.profileChange(memberNo,profileImage);
			if(result!=0) {
				session.setAttribute("message", "프로필 이미지가 변경되었습니다.");
				loginMember.setProfileImage(profileImage);
			}else {
				session.setAttribute("message", "프로필 이미지 변경실패");
			}
			// 성공/실패 관계 없이 프로필 화면 재요청(redirect)			
			resp.sendRedirect("profile"); // 상대경로(GET)
//			resp.sendRedirect(req.getContextPath()+"/member/myPage/profile");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
