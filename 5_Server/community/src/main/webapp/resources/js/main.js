console.log("main.js loaded")

//로그인 시 이메일(아이디)/비밀번호 입력 확인

//->미작성 시 alter() 이용해서 메시지 출력
//  로그인 form태그 제출을 막는 기본 이벤트 제거 진행

function loginValidate(){ //로그인 유효성 검사

    //이메일 입력 input 요소
    const inputEmail = document.getElementsByName("inputEmail")[0];

    //비밀번호 입력 input 요소
    const inputPw= document.getElementsByName("inputPw")[0];
    console.log(inputEmail);
    console.log(inputPw);

    //이메일이 입력되지 않은경우
    if(inputEmail.value.trim()==""){
        //문자열.trim() :문자열 양쪽 공백 제거
        alert("이메일을 입력해주세요.")
        inputEmail.focus(); // 이메일 input에 초점 맞추기
        inputEmail.value="";
        return false; //기본 이벤트 제거를 위해 false 반환

    }

    //비밀번호가 입력되지 않은 경우
    if(inputPw.value.trim().length==0){
        alert("비밀번호를 입력해주세요.")
        inputPw.focus();
        inputPw.value=""
        return false;
    }

}

//화면에 아이디 저장 체크박스가 있는 경우
const saveId=document.getElementsByName("saveId")[0];
if(saveId !=null){

    //아이디 저장 체크박스가 체크되었을 때 이벤트 처리
    document.getElementsByName("saveId")[0].addEventListener("change",function(){
        //체크 여부 확인
        console.log(this.checked)
        //this : 이벤트가 발생한 요소
        //체크박스요소.checked : 체크 여부 반환(t/f)
    
        if(this.checked){ //체크박스가 체크된 경우 
            const str="개인 정보 보호를 위해 개인 PC에서의 사용을 권장합니다. 개인 PC가 아닌 경우 취소를 눌러주세요"
            if(!confirm(str)){ //취소를 눌렀을 때
                //체크 해제
                this.checked=false;
            }
        }
     
    })
}

//회원 정보 조회 비동기 통신 (ajax)
document.getElementById("selectBtn").addEventListener("click",()=>{
    const input = document.getElementById("inEmail");
    const div = document.getElementById("result1");
    const memberList=document.getElementById("memberList");

    $.ajax({
        // /community/member/selectOne
        url : "member/selectOne",

        data :{"inputEmail" : input.value},
        type : "POST",
        dataType :"JSON", // dataType : 응답데이터 형식을 지정
                          // -> JSON으로 지정 시 자동으로 JS 객체로 변환
        success : function(member){
           console.log(member); // JS 객체 형태 문자열(dateType 미지정 시)


            // JSON.parse(문자열) : 문자열 -> JS 객체로 변환
           // console.log(JSON.parse(member));

           //div에 작성된 내용 모두 삭제
           div.innerText="";

           if(member !=null){ // 회원 정보 존재 O

                // ul 요소 생성
                const tr = document.createElement("tr");

                // li 요소 생성 * 5 + 내용 추가
                
                const td1 = document.createElement("td");
                li1.innerText=`이메일 : ${member.memberEmail}`; 
                const td2 = document.createElement("td");
                li2.innerText=`닉네임 : ${member.memberNickname}`; 
                const td3 = document.createElement("td");
                li3.innerText=`전화번호 : ${member.memberTel}`; 

                div.append(li1,li2,li3,li4,li5);

                // div($result1)에 ul 추가
                div.append(ul);

           }else{//회원정보가 존재하지 않을때
                const h4 = document.createElement("h4")
                h4.innerText="일치하는 회원이 없습니다."
                //h4.style.color ="red";
                h4.setAttribute("style","color : red");
                div.append(h4);
           }

        },
        error : function(request,status,error){
            console.log("ajax 에러 발생")

            console.log("상태코드 : " + request.status)

            console.log(request.responseText);//에러 메세지

            console.log(error); // 에러 객체 출력
        }
    })
})

// ** 일정 시간마다 회원 목록 조회 (ajax) **
function selectAll(){ //회원 전체 조회 함수

    $.ajax({
        url : "member/selectAll",
        type : "GET",
        dataType :"JSON",
        success : function(memList){
            console.log(memList)
            memberList.innerText="";
           

            if(memList.length !=0){ // 회원 목록이 존재한다면

                // memLsit에 담긴 내용 출력
                for(let i=0; i<memList.length; i++){
                    const tr = document.createElement("tr");
                    const td1 = document.createElement("td");
                    td1.innerText=` ${memList[i].memberNo}`; 
                    const td2 = document.createElement("td");
                    td2.innerText=` ${memList[i].memberEmail}`; 
                    const td3 = document.createElement("td");
                    td3.innerText=` ${memList[i].memberNickname}`; 
                 
    
                    tr.append(td1,td2,td3);
    
                    memberList.append(tr);
                }
            }else{ //회원 목록이 존재하지 않는경우
                const tr = document.createElement("tr");
                const td = document.createElement("td");

                td.innerText="회원 목록이 존재하지 않습니다."
                td.setAttribute("colspan","3");

                tr.append(td);
                memberList.append(tr);

            }


     

        },
        error : function(request,status,error){
            console.log("ajax 에러 발생")

            console.log("상태코드 : " + request.status)

            console.log(request.responseText);//에러 메세지

            console.log(error); // 에러 객체 출력
        }
    })

}

// 즉시 실행 함수
(function(){

    selectAll(); // 함수 호출 -> 회원 목록을 먼저 조회

    //window.setInterval(함수,딜레이(ms))
    //->딜레이시간만큼 먼저 지연 후 함수 실행 -> 지연 -> 실행 .. 반복

    window.setInterval(selectAll,10000); //10초
    //함수 이름만 작성 -> 함수 코드 대입
    //-> 10초마다 selectAll(회원 전체 조회 함수) 실행


})();