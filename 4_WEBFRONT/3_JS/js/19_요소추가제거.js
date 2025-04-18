//추가 버튼이 클릭 되었을 때
document.getElementById("add").addEventListener("click",function(){

    //div 요소 생성
    const div = document.createElement("div")

    //div에 row 클래스 추가
    div.classList.add("row")

    //-----------------------------------

    //input 요소 생성
    const input = document.createElement("input")

    //input에 in 클래스 추가
    input.classList.add("in")

    //input에 "type" 속성, "number" 속성값 추가 (type="number")
    // -요소.setAttribute("속성","속성값") : 요소에 속성/속성값 추가
    // input.type="number" // <이것도 됨( 기존에 있는 속성이라면)
    input.setAttribute("type","number"); //< 없는 속성도 만들어줌


    //-----------------------------------
    //span 요소 생성
    const span = document.createElement("span")
    //span의 내용으로 X 추가
    span.innerText= "X"

    //span에 remove 클래스 추가

    span.classList.add("remove")

    //span이 클릭 되었을 때에 대한 이벤트 동작 추가

    span.addEventListener("click",function(){
        this.parentElement.remove();
    })


    //----------------------------------------------

    //div내부에(자식으로) input,span 순서대로 추가
    
    div.append(input)
    div.append(span)
    


    //#container에 div를 마지막 자식으로 추가
    con=document.getElementById("container").append(div)

})

//계산 버튼 클릭 시 이벤트 동작
let sum=0;
document.getElementById("calc").addEventListener("click",function(){
    
    const list=document.getElementsByClassName("in")

    //배열용 향상된 for문(for of)
    for(let input of list){

        //sum에 입력한 값 누적
        //input에 작성된 값은 모두 string->형변환 필요
        sum +=Number(input.value);

        //Number("")==0 // 빈칸은 0으로 변환되기 때문에 신경쓰지 말자
    }
    alert(sum);
})