package edu.kh.project.chatting.model.service;

import java.util.List;

import edu.kh.project.chatting.model.dto.ChattingRoom;
import edu.kh.project.member.model.dto.Member;

public interface ChattingService {



	/** 채팅목록조회
	 * @param memberNo
	 * @return roomlist
	 */
	List<ChattingRoom> selectRoomList(int memberNo);

}
