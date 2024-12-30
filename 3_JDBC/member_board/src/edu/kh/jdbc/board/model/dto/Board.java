package edu.kh.jdbc.board.model.dto;

import java.sql.Date;
import java.util.List;

public class Board {
   // * 꼭 테이블과 같은 모양일 필요 X
   //->어떤 데이터를 저장하고 옮기고 싶은지에 따라 필드 구성 달라짐
   
   
   //게시글 목록 조회
   private int boardNo;
   private String boardTitle;
   private Date createDate;
   private int readCount;
   private String memberName;
   private int replyCount;

   
   //게시글 상세조회
   private String boardContent;
   private List<Reply> replyList;
   
   // 게시글 수정,삭제
   private int memberNo;
   
   public Board() {}

   
   public Board(int boardNo, String boardTitle, Date createDate, int readCount, String memberName, int replyCount) {
      super();
      this.boardNo = boardNo;
      this.boardTitle = boardTitle;
      this.createDate = createDate;
      this.readCount = readCount;
      this.memberName = memberName;
      this.replyCount = replyCount;
   }





//   @Override
//   public String toString() {
//      return String.format("%2d   %15s[%d] %8s %2s %4d", boardNo,boardTitle,replyCount,memberName,createDate,readCount) ;
//   }
   



   @Override
   public String toString() {
	   return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", createDate=" + createDate + ", readCount="
			   + readCount + ", memberName=" + memberName + ", replyCount=" + replyCount + ", boardContent=" + boardContent
			   + ", replyList=" + replyList + ", memberNo=" + memberNo + "]";
   }


   public int getBoardNo() {
      return boardNo;
   }



public void setBoardNo(int boardNo) {
      this.boardNo = boardNo;
   }

   public String getBoardTitle() {
      return boardTitle;
   }

   public void setBoardTitle(String boardTitle) {
      this.boardTitle = boardTitle;
   }

   public Date getCreateDate() {
      return createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public int getReadCount() {
      return readCount;
   }

   public void setReadCount(int readCount) {
      this.readCount = readCount;
   }

   public String getMemberName() {
      return memberName;
   }

   public void setMemberName(String memberName) {
      this.memberName = memberName;
   }

   public int getReplyCount() {
      return replyCount;
   }

   public void setReplyCount(int replyCount) {
      this.replyCount = replyCount;
   }

   public String getBoardContent() {
      return boardContent;
   }

   public void setBoardContent(String boardContent) {
      this.boardContent = boardContent;
   }

   public List<Reply> getReplyList() {
      return replyList;
   }

   public void setReplyList(List<Reply> replyList) {
      this.replyList = replyList;
   }

   public int getMemberNo() {
      return memberNo;
   }

   public void setMemberNo(int memberNo) {
      this.memberNo = memberNo;
   }


}
