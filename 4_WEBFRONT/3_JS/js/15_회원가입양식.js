// /* 아이디 : 값이 변했을 때 
// 영어 소문자로 시작하고, 
// 영어 대/소문자, 숫자, - , _ 로만 이루어진 6~14 글자 사이 문자열인지 검사
// 아이디 정규표현식 : (각자 작성)
// //^[a-z][a-zA-Z0-9-,_]{6,14}$

// - 형식이 일치할 경우
// 입력창의 배경색을 green 으로 변경

// - 형식이 일치하지 않은 경우
// 입력창의 배경색을 red, 글자색을 white 로 변경*/

document.getElementById("inputId").addEventListener("input",function(){
    const regExp=/^[a-z][a-zA-Z0-9-,_]{5,13}$/;

    if(regExp.test(this.value)){
        this.style.backgroundColor="green";
        this.style.color="black"
    }else{
        this.style.backgroundColor="red";
        this.style.color="white"
    }
    if(this.value==''){
        this.style.backgroundColor="white";
    }
    

   

})

// ------------------------------------------------------------------

/* 비밀번호, 비밀번호 확인 : 키보드가 올라올 때 
"비밀번호" 를 미입력한 상태에서 "비밀번호 확인"을 작성할 경우
"비밀번호 확인"에 작성된 내용을 모두 삭제하고
'비밀번호를 입력해주세요' 경고창 출력 후
focus 를 "비밀번호" 입력창으로 이동
*/

// ------------------------------------------------------------------

/*
- 비밀번호가 일치할 경우
"비밀번호" 입력창 오른쪽에 "비밀번호 일치" 글자를 초록색으로 출력.

- 비밀번호가 일치하지 않을 경우
"비밀번호" 입력창 오른쪽에 "비밀번호가 불일치" 글자를 빨간색으로 출력

- 비밀번호가 작성되지 않은경우 오른쪽에 출력되는 문구 삭제

*/
document.getElementById("inputPw2").addEventListener("keyup",function(){

    inputPw=document.getElementById("inputPw")
    inputPw2=document.getElementById("inputPw2")
    passch=document.getElementById("passch")
  
 

        if(inputPw.value==''){
            inputPw.focus();
            alert("비밀번호를 입력해주세요");
            inputPw2.value='';
            passch.innerText="";
            return;
        }
       
    if(inputPw.value==inputPw2.value){
        
        passch.classList.remove("red");
        passch.classList.add("green");

        passch.innerText="비밀번호 일치";
        
    }else{
        passch.classList.remove("green");
        passch.classList.add("red");
        passch.innerText="비밀번호 불일치"
        
    }
    if(inputPw2.value=='' || inputPw.value==''){
        passch.innerText="";
    }
   

})



// -------------------------------------------------------------

/* 이름 : 값이 변화했을 때 
한글 2~5 글자 사이 문자열인지 검사.
- 형식이 일치할 경우
"이름" 입력창 오른쪽에 "정상입력" 글자를 초록색으로 출력.
- 형식이 일치할 경우
"이름" 입력창 오른쪽에 "한글만 입력하세요" 글자를 빨간색으로 출력.
*/

// -----------------------------------------------------------

document.getElementById("name").addEventListener("input",function(){

    nametext=document.getElementById("nametext");
    const regExp=/^[가-힣]{2,5}$/;
    if(regExp.test(this.value)){
        nametext.innerText="정상입력";
        nametext.classList.add("green")
        nametext.classList.remove("red")
    }else{
        nametext.innerText="한글만 입력하세요";
        nametext.classList.add("red")
        nametext.classList.remove("green")

    }
    if(this.value==''){
        nametext.innerText="";
    }
    
    

})

/* 회원가입 버튼 클릭 시 : validate() 함수를 호출하여 
성별이 선택 되었는지, 전화번호가 형식에 알맞게 작성되었는지 검사 */


document.getElementById("login").addEventListener("click",function validate(){
    gender=document.getElementsByName("gender")
    tel=document.getElementById("tel")
    login=document.getElementById("login")
    const regExp=/010-\d{4}-\d{4}$/;
    let gendercheck=false;

    for(let item of gender){
        if(item.checked){
            gendercheck=true;
            break;
        }
    }

    if(!gendercheck){
        login.type="button"
        alert("성별을 체크하세요")
        return;
    }else{
        login.type="submit"
    }

    

    if(!regExp.test(tel.value)){
        tel.type="button"
        alert("전화번호의 형식이 올바르지 않습니다")
    }else{
        tel.type="submit"

    }
})

    

    /*- 성별이 선택되지 않은 경우 
    "성별을 선택해주세요." 경고창(==대화상자) 출력 후
    submit 기본 이벤트를 제거하여 회원가입이 진행되지 않게 함.*/

    

    /*
    - 전화번호 형식이 올바르지 않을 경우 
    "전화번호의 형식이 올바르지 않습니다" 경고창(==대화상자) 출력 후
    submit 기본 이벤트를 제거하여 회원가입이 진행되지 않게 함.
    */





