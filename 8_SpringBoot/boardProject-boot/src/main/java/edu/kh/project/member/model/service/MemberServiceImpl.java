package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.MemberDAO;
import edu.kh.project.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO dao;

	@Autowired // bean으로 등록된 객체 중 타입이 일치하는 객체 DI
	private BCryptPasswordEncoder bcrypt;

	@Override
	public Member login(Member inputMember) {

		// dao 메소드 호출
		Member loginMember = dao.login(inputMember);

		if(loginMember != null) { // 아이디가 일치하는 회원이 조회된 경우

			// 입력한 pw, 암호화된 pw 같은지 확인

			// 같을 경우
			if(bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {

				// 비밀번호를 유지하지 않기 위해서 로그인 정보에서 제거
				loginMember.setMemberPw(null);

			} else { // 다를 경우
				loginMember = null; // 로그인 실패처럼 만들기
			}

		}
		return loginMember;
	}



}
