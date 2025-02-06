console.log("myPage.js연결");

//내 정보(수정) 페이지
const memberNickname = document.getElementById("memberNickname");
const memberTel = document.getElementById("memberTel");
const updateInfo = document.getElementById("updateInfo");


// 내 정보 수정 form 태그가 존재할 때 ()== 내 정보 페이지)
if(updateInfo != null){

    //전역변수로 수정 전 닉네임/전화번호를 저장
    initNickname = memberNickname.value;
    initTel = memberTel.value;
    //닉네임 유효성 검사
    memberNickname.addEventListener('input',()=>{
        //변경 전 닉네임과 입력한 값이 같은 경우
        if(initNickname == memberNickname.value){
            memberNickname.removeAttribute("style");
            return;
        }
  

        //정규 표현식으로 유효성 검사
        const regEx=/^[a-zA-z가-힣0-9]{2,10}$/;
        if(regEx.test(memberNickname.value)){
            memberNickname.style.color='green';
            // memberNickname.setAttribute( "style",  "color:green;" );
        }else{
            memberNickname.style.color='red';
            // memberNickname.setAttribute( "style",  "color:red;" );
        }
    })
    //전화번호 유효성 검사
        memberTel.addEventListener('input',()=>{
            if(initTel == memberTel.value){
                memberTel.removeAttribute("style");
                return;
            }
      
    
            const regEx=/^0(1[01]|2|[3-6][1-5]|70)\d{7,8}$/;
            if(regEx.test(memberTel.value)){
                memberTel.style.color='green';
            }else{
                memberTel.style.color='red';
            }
    
        })
        //form 태그 제출 시 유효하지 않는 값이 있을 경우 제출 x

        updateInfo.addEventListener("submit",e=>{

            // 닉네임이 유효하지 않은 경우
            if(memberNickname.style.color == "red"){
                alert("닉네임이 유효하지 않습니다.")
                memberNickname.focus();
                e.preventDefault();
                return;
            }

            // 전화번호가 유효하지 않은 경우
            if(memberTel.style.color == "red"){
                alert("전화번호가 유효하지 않습니다.")
                memberTel.focus();
                e.preventDefault();
                return;
            }
        })
    
}


//비밀번호 변경 제출 시

//비밀번호 변경 페이지인 경우
const changePwFrm = document.getElementById("changePwFrm");
const currentPw = document.getElementById("currentPw");
const newPw = document.getElementById("newPw");
const newPwConfirm = document.getElementById("newPwConfirm");

if(changePwFrm!=null){
    //현재 비밀번호 미작성 시
    //알림창, 기본이벤트제거, 초점,값 지우기

    changePwFrm.addEventListener("submit",e=>{

        if(currentPw.value==""){
            alert("현재 비밀번호를 입력하세요");
            currentPw.focus();
            e.preventDefault();
            currentPw.value="";
            return;
        }
        //새비밀번호 작성 시
        const regEx = /^[A-Za-z0-9!@#\-\_]{6,20}$/;
        if(!regEx.test(newPw.value)){
           alert("비밀번호가 유효하지 않습니다.");
           e.preventDefault();
           newPw.focus();
           newPw.value='';
           return;
        }

        if(newPw.value != newPwConfirm.value){
            alert("비밀번호가 일치하지 않습니다.");
            e.preventDefault();
            newPwConfirm.focus();
            newPwConfirm.value='';
            return;
        }
        //유효성 검사 진행 -> 유효하지 않은 경우 "비밀번호가 유효하지 않습니다."
        //비밀번호와 비밀번호 확인이 일치하지 않는 경우 "비밀번호가 일치하지 않습니다."
        
        //전부 유효한 경우 -> 제출
    
    })


}


//회원 탈퇴 페이지인 경우
const secessionFrm = document.getElementById("secessionFrm");
const memberPw = document.getElementById("memberPw");
const agree = document.getElementById("agree");
// console.log(agree.checked)  true false

//비밀번호 미작성

//약관동의 체크되지 않은 경우

//탈퇴 버튼 클릭 시 "정말로 탈퇴하시겠습니까?"

//이 때, 취소 클릭 시 "탈퇴 취소" 알림창
if(secessionFrm!=null){

    secessionFrm.addEventListener("submit",e=>{
        if(memberPw.value==""){
            alert("현재 비밀번호를 입력하세요");
            memberPw.focus();
            e.preventDefault();
            memberPw.value="";
            return;
        }
        if(agree.checked==false){
            alert("약관동의 체크하세요");
            e.preventDefault();
            return;
        }
        if(!confirm("정말로 탈퇴하시겠습니까?")){
            alert("탈퇴 취소")
            e.preventDefault();
        }

    })

}

    