    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!--문자열 관련 함수(메소드)제공 JSTL (EL형식으로 작성)-->
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KH커뮤니티</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/myPage-style.css">
    <script src="https://kit.fontawesome.com/0537c4177e.js" crossorigin="anonymous"></script>
    </head>
    <body>


    <main>
        <!-- 헤더 -->
            <jsp:include page="/WEB-INF/views/common/header.jsp"/>
            <!-- 헤더끝 -->
        

        

        <!-- 마이페이지 - 비밀번호 변경 -->
            <section class="myPage-content">

            <!-- 사이드 메뉴 include  -->
                <jsp:include page="/WEB-INF/views/member/sideMenu.jsp"></jsp:include>
            <!-- 왼쪽 사이드 메뉴 -->
            <!-- <section class="left-side">
                <ul class="list-group">
                    <li><a href="">프로필</a></li>
                    <li><a href="">내 정보</a></li>
                    <li><a href="">비밀번호 변경</a></li>
                    <li><a href="">회원 탈퇴</a></li>

                </ul>
            </section> -->

            <!-- 오른쪽 마이페이지 주요 내용 부분 -->
            <section class="myPage-main">
                <h1 class="myPage-title">회원 탈퇴</h1>
                <span class="myPage-explanation">현재 비밀번호가 일치하는 경우 탈퇴할 수 있습니다.</span>

                <form action="secession" method="post" name="myPage-form" onsubmit="return secessionValidate()">
                    <div class="myPage-row">
                        <label for="memberPw">현재 비밀번호</label>
                        <input type="password" name="memberPw" id="memberPw"  maxlength="30">
                    </div>
                    
                    <div class="myPage-row info-title">
                        <label>회원 탈퇴 약관</label>
                        
                    </div>
                    <pre id="secession-terms">
제1조
이 약관은 샘플 약관입니다.
                    
① 약관 내용 1
                    
② 약관 내용 2
                    
③ 약관 내용 3
                    
④ 약관 내용 4
                    
                    
제2조
이 약관은 샘플 약관입니다.
                    
① 약관 내용 1
                    
② 약관 내용 2

③ 약관 내용 3
                    
④ 약관 내용 4
                                        </pre>
                    
                                        <div>
                                            <input type="checkbox" name="agree" id="agree">
                                            <label for="agree">위 약관에 동의합니다.</label>
                                        </div>
                    
                                        <button id="info-update-btn">탈퇴</button>

                    
                    

                </form>

            </section>
            </section>

        
    </main>
    <!-- 푸터 -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        <!-- 푸터끝 -->
        <script src="${contextPath}/resources/js/myPage.js"></script>
    </body>
    </html>