package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.AjaxDAO;

@Service // 서비스 + bean등록(IOC)
public class AjaxServiceImpl implements AjaxService{
	
	
	@Autowired // DI 
	private AjaxDAO dao;

	
	//이메일로 닉네임 조회
	@Override
	public String selectNickname(String email) {
		return dao.selectNickname(email);
	}


	@Override
	public String selectTel(String nickname) {
		return dao.selectTel(nickname);
	}

}
