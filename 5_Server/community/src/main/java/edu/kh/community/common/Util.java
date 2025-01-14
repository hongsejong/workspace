package edu.kh.community.common;

public class Util {
	
	//개행 문자 -> ><br> 변경 메소드
	public static String newLineHandling(String content) {
		return content.replaceAll("(\n|\r|\r\n|\n\r)", "<br>");
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
