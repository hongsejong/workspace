package edu.kh.project.myPage.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.common.utility.Util;
import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.dao.MyPageDAO;

@Service // 비즈니스 로직 처리 + Bean등록(IOC)
public class MyPageServiceImpl implements MyPageService{
	
	@Autowired // MyPageDAO 의존성 주입(DI)
	private MyPageDAO dao;
	@Autowired // bean으로 등록된 객체 중 타입이 일치하는 객체 DI 
	private BCryptPasswordEncoder bcrypt;

	// 스프링에서는 트랜잭션을 처리할 방법을 지원해줌.(코드기반, 선언적)
	   // 1) <tx:advice> -> AOP를 이용한 방식(XML에 작성)
	   
	   // 2) @Transactional 어노테이션을 이용한 방식(클래스 또는 인터페이스에 작성)
	   // - 인터페이스를 구현한 클래스로 선언된 빈은 인터페이스 메소드에 한해서 트랜잭션이 적용됨
	   // * 트랜잭션 처리를 위해서는 트랜잭션 매니저가 bean으로 등록되어 있어야 함. 
	   //   -> root-context.xml 작성
	   
	   // 정상 여부는 RuntimeException이 발생했는지 기준으로 결정되며, 
	   // RuntimeException 외 다른 Exception(대표적으로 SQLException 등)에도 트랜잭션 롤백처리를 적용하고 싶으면 
	   // @Transactional의 rollbackFor 속성을 활용하면 된다

	//회원 정보 수정 서비스
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public int updateInfo(Member updateMember) {
		return dao.updateInfo(updateMember);
	}
	


	

	@Transactional(rollbackFor = {Exception.class})
	@Override
	public int changePw(String currentPw, String newPw, int memberNo) {
		//1. 현재 비밀번호, DB에 저장된 비밀번호 비교
		//1-1) 회원 번호가 일치하는 MEMBER_PW 조회
		String encPw = dao.selectEncPw(memberNo);
		
		//1-2) bcrypt.matches(평문,암호문) 같으면 true-> 이 때 비번 수정
		if(bcrypt.matches(currentPw,encPw)) {
			// 2. 비밀번호 변경 후 결과 반환
			return dao.changePw(bcrypt.encode(newPw),memberNo);
		}
		
		
		//3. 비밀번호가 일치하지 않는 경우 0 반환
		return 0;
	}





	@Override
	public int secession(int memberNo, String memberPw) {
		
		// 1. 로그인한 회원의 비밀번호 조회
		String encPw = dao.selectEncPw(memberNo);
		
		if(bcrypt.matches(memberPw, encPw)) {
			return dao.secession(memberNo);
		}
		return 0;
	}




	
	//프로필 이미지 수정 서비스
	@Override
	public int updateProfile(MultipartFile profileImage, Member loginMember, String webPath, String filePath) throws IllegalStateException, IOException {
		
		// 프로필 이미지 변경 실패 대비
		String prevImg = loginMember.getProfileImage(); //이전 이미지 저장
		
		String rename=null; // 변경명 저장 변수
		// 업로드된 이미지가 있을 경우
		if(profileImage.getSize()>0) {
			//1) 파일 이름 변경
			rename= Util.fileRename(profileImage.getOriginalFilename());
			
			//2) 바뀐 이름을 loginMember에 세팅
			loginMember.setProfileImage(webPath+rename);
			
		}else { // 없을 경우(x버튼 클릭)
			loginMember.setProfileImage(null);
			//이미지를 null로 변경해서 삭제
		}
		
		//프로필 이미지 수정 DAO 메소드 호출
		int result=dao.updateProfileImage(loginMember);
		
		if(result>0) { //이미지 수정 성공 시
			
			//새 이미지가 업로드된 경우
			if(rename!=null) {
				profileImage.transferTo(new File(filePath+rename));
			}
			
		}else { //수정 실패
			//이전 이미지로 프로필 다시 세팅
			loginMember.setProfileImage(prevImg);
		}
			
		
		return result;
	}
	


}
