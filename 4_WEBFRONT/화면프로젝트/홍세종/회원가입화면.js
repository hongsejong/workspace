// namespan=document.getElementById("namespan")
// idspan=document.getElementById("idspan")
// passspan=document.getElementById("passspan")
const passinput=document.getElementById("passinput")
const passinput2=document.getElementById("passinput2")
const nameinput=document.getElementById("nameinput")


document.getElementById("nameinput").addEventListener("input",function(){
    const regExp=/^[가-힣]{2,5}$/;

    if(regExp.test(this.value)){
    //   namespan.innerText="정상입니다"
      this.style.background="green"
      
    }else{
        // namespan.innerText="재대로입력하세요"
        this.style.background="red"
        
    }
    if(this.value==''){
        this.style.backgroundColor="white";
    }
})
document.getElementById("idinput").addEventListener("input",function(){
    const regExp=/^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{1,14}$/;

    if(regExp.test(this.value)){
    //   namespan.innerText="정상입니다"
      this.style.background="green"
    }else{
        // namespan.innerText="재대로입력하세요"
        this.style.background="red"
        
    }
    if(this.value==''){
        this.style.backgroundColor="white";
    }
})

document.getElementById("passinput").addEventListener("input",function(){
    const regExp=/^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{1,14}$/;

    if(regExp.test(this.value)){
    //   namespan.innerText="정상입니다"
      this.style.background="green"
    }else{
        // namespan.innerText="재대로입력하세요"
        this.style.background="red"
        
    }
    if(this.value==''){
        this.style.backgroundColor="white";
    }
})
document.getElementById("passinput2").addEventListener("input",function(){

    if(passinput.value==passinput2.value){
        this.style.backgroundColor="green"
    }
    if(passinput.value!=passinput2.value){
        this.style.backgroundColor="red"
    }
})



회원가입=document.getElementById("회원가입")
function validate(){
    gender=document.getElementsByName("gender")
    let gendercheck=false;

    for(let item of gender){
        if(item.checked){
            gendercheck=true;
            break;
        }
    }

    if(!gendercheck){
        회원가입.type="button"
        return;
    }else{
        회원가입.type="submit"
    }

}

const 전체동의 = document.getElementById("전체동의");

const 이용약관동의 = document.getElementById("이용약관동의");
const 광고동의 = document.getElementById("광고동의");
전체동의.addEventListener("click", function () {

    if (전체동의.checked) {
        이용약관동의.checked = true;
        광고동의.checked = true;
    } else {
        이용약관동의.checked = false;
        광고동의.checked = false;
    }
});


document.getElementById("회원가입").addEventListener("click",function(){
    if(nameinput.style.background=="green"
        &&passinput.style.backgroundColor=="green"
        &&passinput2.style.backgroundColor=="green"
        &&emailinput.value!=''
        &&이용약관동의.checked==true
    ){
        alert("가입완료")
    }else{
        alert("재대로 입력하세요")
    }
})


