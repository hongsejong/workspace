
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




// box-color 클래스 요소의 입력된 값이 변했을 때
// 위에있는 box 클래스 요소의 배경색을 변경
// + 입력된 input요소 글씨색도 변경

// change : 포커스를 잃고나서 또는 엔터 입력 시
//          작성된 값이 이전과 다를 경우

//input : 입력과 관련된 모든 행위

//blur : 포커스를 잃었을 때

function colorChange(item){
    //글자색 변경
    item.style.color=item.value

    item.previousElementSibling.style.backgroundColor=item.value;

}



// transition-duration 변경 버튼 클릭 시
// #input1에 작성된 값 만큼의 transition-duration을
// 모든 box 요소에 추가

function changeTd(){
    const input1 = document.getElementById("input1").value; // #input1의 값
    //input1에 작성된 값으로 transition-duration 변경
    for(let item of box){
        item.style.transitionDuration = (input1 + 's');
        
    }
    // + #print1 요소의 내용을 #input1에 작성된 값으로 변경
    document.getElementById("print1").innerText= input1;
}

// hint.  transition-duration에 세팅되는 값은 초단위(s) 입니다.



// #clearBtn 클릭 시
// .box의 모든 배경색을 없애고
// .box-color에 작성된 값도 없애기

function resetBtn(){
    //방법 1 ) 향상된 for문
    for(let item of box){ //배경색 삭제
        item.style.backgroundColor='';
    }
    for(let item of boxColor){
        item.value='';
        item.style.color='';
    }

    // 방법 2 ) 일반 for문
    // for(let i =0; i<box.length; i++){
    //     box[i].style.backgroundColor='';
    //     boxColor[i].value='';

    // }
    
}