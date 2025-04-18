package edu.kh.project.myPage.model.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;

public interface MyPageService {


	/** 회원 정보 수정 서비스
	 * @param updateMember
	 * @return result
	 */
	int updateInfo(Member updateMember);

	/** 비밀번호 변경 서비스
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 */
	int changePw(String currentPw, String newPw, int memberNo);


	
	
	/** 회원 탈퇴 서비스
	 * @param memberNo
	 * @param memberPw
	 * @return
	 */
	int secession(int memberNo, String memberPw);

	/** 프로필 이미지 수정
	 * @param profileImage
	 * @param loginMember
	 * @param webPath
	 * @param filePath
	 * @return result
	 */
	int updateProfile(MultipartFile profileImage, Member loginMember, String webPath, String filePath) throws IllegalStateException, IOException;



}
