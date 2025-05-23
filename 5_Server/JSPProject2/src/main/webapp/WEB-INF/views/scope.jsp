<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet/JSP 내장 객체와 범위</title>
</head>
<body>

    <h1>Servlet/SJP 내장 객체와 범위</h1>

    <pre>
        Servlet/JSP에는 4종류 범위를 나타내는 내장 객체가 존재한다!
        -> 각 종류마다 영향을 끼치는 범위가 달라짐

        <h4>1. page (pageContext) : 현재 페이지</h4>
        ->현재 JSP 에서만 사용 가능한 객체 (Servlet X )

        <h4>2. request (요청)</h4>
        ->요청 받은 페이지(Servlet/JSP)와
          위임 받은 페이지(Servlet/JSP)에서 유지되는 객체

        <h4>3. session (접속)</h4>
        -session : 서버에 접속한 클라이언트를 나타내거나, 관련 정보를 get/set 할 수 있는 객체
                    (session 객체는 서버에서 관리함)

        -[중요!] session은 브라우저 마다 하나씩 생성된다!

        -[유지 범위] : 사이트 접속~브라우저 종료 | 세션 만료

        <h4>4. application (ServletContext)</h4>
        -하나의 웹 애플리케이션 마다 1개만 생성되는 객체
         (Server를 키면 1개만 생성되는 객체)

        -application 객체는 어디서든 사용 가능

        -[유지 범위] : 서버 실행 ~ 서버 종료

    </pre>

    <hr><hr>
    <h1>내장 객체 확인</h1>
    <% //스크립틀릿 : JSP에서 Java 코드를 작성하는 영역
    //	String pageMessage = "page scope 입니다."; <<이렇게 쓰면 안됨
    
    	//pageContext == page 범위(scope)객체
    	pageContext.setAttribute("pageMessage","page scope 입니다.");
    %>
    
    <ul>
        <li>page : ${pageMessage }</li>
        <li>request : ${requestMessage}</li>
        <li>session : ${sessionMessage}</li>
        <li>application : ${applicationMessage} </li>
        <li><a href="scopeCheck">scope 확인하기</a></li>
    </ul>
    
    <hr><hr>
    
    <h1>우선 순위 확인</h1>
    <% //page scope
    pageContext.setAttribute("str", "page scope 에 세팅된 문자열");
    %>
    
    <h2>\${key } : 4종류의 scope 객체를 범위가 좁은 순서대로 탐색해서
    				key가 일치하는 속성이 존재하면 출력</h2>
	<h2>${str }</h2>
	
	<hr>
	<h2>\${XXXScope.key} : XXX 자리에 scope를 작성하면
		해당 scope에서 key가 일치하는 속성을 찾아서 출력
	</h2>
	
	<h4>${pageScope.str }</h4>
	<h4>${requestScope.str }</h4>
	<h4>${sessionScope.str }</h4>
	<h4>${applicationScope.str }</h4>
</body>
</html>