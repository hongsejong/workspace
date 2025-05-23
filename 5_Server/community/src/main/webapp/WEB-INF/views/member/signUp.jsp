<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KH커뮤니티</title>

    <link rel="stylesheet" href="../resources/css/main-style.css">
    <link rel="stylesheet" href="../resources/css/signUp-style.css">
    <script src="https://kit.fontawesome.com/0537c4177e.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    
</head>
<body>

    <!-- 
        시맨틱(Semantic, 의미) 태그 (의미있는 태그)

        - 기존 영역 분할에 주로 사용되던 div, span 등의 태그는
        태그 이름만 봤을 때 나눈다는 것 이외의 의미를 파악할 수 없음
        -> 태그만 봤을 때 태그의 목적을 할 수 없어
           id 또는 class를 반드시 추가해야 했다.

        이런 문제점을 해결하고자
        태그 이름만으로 어느정도 어떤 역할을 하는지 알 수 있고
        추가적으로 웹 접근성 향상에 도움이 되는
        시맨틱 태그가 추가됨 (HTML5)

        [제공하는 태그]

        header 태그 : 문서의 제목, 머리말 영역

        footer 태그 : 문서의 하단부분, 꼬리말, 정보 작성 영역

        nav 태그 : 나침반 역할(다른 페이지, 사이트 이동)의 링크 작성 영역

        main 태그 : 현재 문서의 주된 콘텐츠 작성 영역

        section 태그 : 구역 구문을 위한 영역

        article(작은 토막) 태그 : 본문과 독립된 콘텐츠를 작성하는 영역

        aside 태그 : 사이드바(보통 양쪽), 광고 영역

     -->
    <main>
        <!-- header -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

   

        <!-- 회원 가입 -->
        <section class="signUp-content">
        	<!-- 절대 경로 : /community/member/signUp
        	
        		 현재 주소 : /community/member/signUp
        		 상대 경로 : signUp
        		 
        		 회원가입 화면 전환 주소(GET)와 같은 주소로
        		 실제 회원가입 요청(POST)
        		 ->요청 주소가 같아도 데이터 전달 방식이 다르면 중복 허용!
        	 -->
            <form action="signUp" method="post" name="signUp-form" onsubmit="return signUpValidate()">
                <label for="memberEmail">
                    <span class="required">*</span> 아이디(이메일)
                </label>

                <div class="signUp-input-area">
                    <input type="text" id="memberEmail" name="memberEmail" placeholder="아이디(이메일)" maxlength="30" autocomplete="off">


                     <!-- autocomplete='off' : 자동완성 미사용 -->
                     <!-- required : 필수 작성 input 태그 -->


                     <button type="button">인증번호 받기</button>
                </div>

                <span class="signUp-message" id="emailMessage">메일을 받을 수 있는 이메일을 입력해주세요</span>


                <label for="emailCheck">
                    <span class="required">*</span> 인증번호
                </label>

                <div class="signUp-input-area">
                    <input type="text" id="emailCheck"  placeholder="인증번호 입력" maxlength="30" autocomplete="off">


                     <button type="button">인증하기</button>
                </div>

                <span class="signUp-message confirm">인증되었습니다.</span>


                <label for="memberPw">
                    <span class="required">*</span> 비밀번호
                </label>

                <div class="signUp-input-area">
                    <input type="password" id="memberPw" name="memberPw"  placeholder="비밀번호" maxlength="30" autocomplete="off" required>

                    </div>
                
                    <div class="signUp-input-area">
                        <input type="password" id="memberPwConfirm" placeholder="비밀번호 확인" maxlength="30" autocomplete="off" required>
    
                        </div>    

                <span class="signUp-message" id="pwMessage">영어,숫자,특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.</span>



                <label for="memberNickname">
                    <span class="required">*</span> 닉네임
                </label>

                <div class="signUp-input-area">
                    <input type="text" id="memberNickname" name="memberNickname"  placeholder="닉네임" maxlength="10" autocomplete="off" >

                </div>

                <span class="signUp-message " id="nicknameMessage" >영어/숫자/한글2~10글자 사이로 작성해주세요</span>


                <label for="memberTel">
                    <span class="required">*</span> 전화번호
                </label>

                <div class="signUp-input-area">
                    <input type="text" id="memberTel" name="memberTel"  placeholder="(-없이 숫자만 입력)" maxlength="11" autocomplete="off" >

                </div>

                <span class="signUp-message" id="telMessage">전화번호를 입력해주세요.(- 제외)</span>


                <label for="memberAddress">
                    주소
                </label>

                <div class="signUp-input-area">
                    <input type="text" id="memberAddress" name="memberAddress"  placeholder="우편번호" maxlength="6" autocomplete="off" >

                    <button type="button">검색</button>
                </div>
                <div class="signUp-input-area">
                    <input type="text"  name="memberAddress"  placeholder="도로명주소"  >

                </div>
                <div class="signUp-input-area">
                    <input type="text"  name="memberAddress"  placeholder="상세주소"  >

                </div>

                <button id="signUp-btn">가입하기</button>

            </form>
        
            
        </section>
    </main>
   <!-- footer -->
   <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

   <script src="${contextPath}/resources/js/signUp.js"></script>
</body>
</html>