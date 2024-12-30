const inputid=document.getElementById("inputid")
const inputpw=document.getElementById("inputpw")



document.getElementById("로그인버튼").addEventListener("click",function(){
    const regExp=/^user01$/;
    const regExp1=/^pass01$/;
    const idvalue=inputid.value;
    const pwvalue=inputpw.value;


    if(regExp.test(idvalue) && regExp1.test(pwvalue)){
        
        location.href="로그인화면.html";
    }else{

        alert("로그인 실패 아이디 /비밀번호를 확인하세요")
    }
})
