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
                <h1 class="myPage-title">비밀번호 변경</h1>
                <span class="myPage-explanation">현재 비밀번호가 일치하는 경우 새 비밀번호로 변경할 수 있습니다.</span>

                <form action="changePw" method="post" name="myPage-form" onsubmit="return changePwValidate()">
                    <div class="myPage-row">
                        <label for="currentPw">현재 비밀번호</label>
                        <input type="password" name="currentPw" id="currentPw"  maxlength="30">
                    </div>
                    
                     <div class="myPage-row">
                        <label for="newPw">새 비밀번호</label>
                        <input type="password" name="newPw" id="newPw"  maxlength="30">
                    </div>
                      <div class="myPage-row">
                        <label for="newPwConfirm">새 비밀번호 확인</label>
                        <input type="password" name="newPwConfirm" id="newPwConfirm"  maxlength="30">
                    </div>

                   
                    
                    <button id="info-update-btn">수정하기</button>

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