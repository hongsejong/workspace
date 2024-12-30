
// 색 출력 영역

// 요소를 얻어와서 변수에 저장

//클래스명,태그명,name속성값, querySelectorAll()같은 경우
//요소를 얻어올 때 배열 형식으로 반환!!!!
const container = document.getElementsByClassName("container");

const area = document.getElementsByClassName("area");
const box =  document.getElementsByClassName("box");
const boxColor =document.getElementsByClassName("box-color");



// JS로 CSS 추가하기


// container 클래스 요소에 display : flex 추가
for(let i=0; i<container.length; i++){
    container[i].style.display = "flex"; 
}



// area 클래스 요소에
// 높이 170px, 너비 150px, 테두리 1px 검정색 실선
// display : flex, main-axis 방향 : 열(세로)

//일반 for문 || for of
for(let item of area){
    //item == area 배열에 담긴 요소를 순차적으로 하나씩 꺼내서 저장하는 변수
    item.style.height = "170px";
    item.style.width = "150px";
    item.style.border = "1px solid black";
    item.style.display = "flex";
    item.style.flexDirection = "column";
}


// box 클래스 요소에 높이 150px, 아랫쪽 테두리 1px 실선 검정색
// for(let i=0; i<box.length; i++){
//     box[i].style.height= "150px";
//      box[i].style.borderBottom = "1px solid black"
// }
for(let item of box){
    item.style.height="150px"
    item.style.borderBottom= '1px solid black';
}


// box-color 클래스 요소의 테두리와 outline을 없애기

// for(let i=0; i<box.length; i++){
//     box[i].style.border="none";
//     box[i].style.outline="none";
// }
for(let item of boxColor){
    item.style.border="none";
    item.style.outline=0;
}


// document.getElementById("input1").addEventListener("input",function(){
//     this.previousElementSibling.style.backgroundColor=this.value;
//     this.style.color=this.value;
// })
// document.getElementById("input2").addEventListener("input",function(){
//     this.previousElementSibling.style.backgroundColor=this.value;
//     this.style.color=this.value;
// })
// document.getElementById("input3").addEventListener("input",function(){
//     this.previousElementSibling.style.backgroundColor=this.value;
//     this.style.color=this.value;
// })
// document.getElementById("input4").addEventListener("input",function(){
//     this.previousElementSibling.style.backgroundColor=this.value;
//     this.style.color=this.value;
// })
// document.getElementById("input5").addEventListener("input",function(){
//     this.previousElementSibling.style.backgroundColor=this.value;
//     this.style.color=this.value;
// })

// const boxColor =document.getElementsByClassName("box-color");

for(let item of boxColor){
    item.addEventListener("input",function(){
        item.previousElementSibling.style.backgroundColor=item.value;
        item.style.color=item.value;

    })

}






document.getElementById("btn1").addEventListener("click",function(){
    const input1 = document.getElementById("inputtd").value; // 
    for(let item of box){
        item.style.transitionDuration = (input1 + 's');
    }
    document.getElementById("print1").innerText= input1;
})

document.getElementById("clearBtn").addEventListener("click",function(){
for(let item of box){ 
    item.style.backgroundColor='';
}
for(let item of boxColor){
    item.value='';
    item.style.color='';
}
document.getElementById("print1").innerText="0"
document.getElementById("inputtd").value='';
})
