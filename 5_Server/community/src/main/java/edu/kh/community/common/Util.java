package edu.kh.community.common;

public class Util {
	
	//개행 문자 -> ><br> 변경 메소드
	public static String newLineHandling(String content) {
		return content.replaceAll("(\r\n|\n\r|\n|\r)", "<br>");
		
		//textarea 의 엔터 : \r\n
		// \r : 캐리지 리턴(첫 번째로 돌아가기)
		// \n : new line(다음줄로 이동)
	}
	
	//XSS(Cross Site Scripting) : 관리자가 아닌 이용자가 악성 스크립트를 삽입해서 공격
	
	//XSS 공격 방지 처리 메소드
	
	public static String XSSHandling(String content) {
		// <,>, &, " 문자를 HTML코드가 아닌 문자 그대로 보이도록 변경
		
		content = content.replaceAll("&", "&amp"); //1빠여야함
		content = content.replaceAll("<", "&lt");
		content = content.replaceAll(">", "&gt");
		content = content.replaceAll("\"", "&quot");
		
		
		return content;
	}
	

}
