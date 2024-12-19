// window.setTimeout(함수,지연시간(ms))
document.getElementById("btn1").addEventListener("click",function(){

    setTimeout(function(){
        alert("3초후 출력문구 확인")
    },3000);
})

//setInterval(함수,지연시간(ms))

let interval; // setInterval을 저장하기 위한 전역 변수

function clockFn(){
    const clock=document.getElementById("clock");
    clock.innerText = currentTime(); 
                      // 현재 시간 화면에 출력하는 함수 호출

    //지연 시간마다 반복(첫 반복도 지연시간 후에 시작)
    //->페이지 로딩 후 1초 후 부터 반복(지연->함수->지연->함수)
    interval=setInterval(function(){
        clock.innerText = currentTime();
    },1000)

    
}

//현재 시간을 문자열로 반환하는 함수
function currentTime(){
    const now = new Date();

    let hour = now.getHours();
    let min = now.getMinutes();
    let sec=now.getSeconds();

    if(hour<10) hour ="0"+ hour;
    if(min<10) min ="0"+ min;
    if(sec<10) sec ="0"+ sec;
    return hour+":"+min+":"+sec;

}

clockFn(); //함수 호출

//clearInterval
document.getElementById("stop").addEventListener("click",function(){

    clearInterval(interval)
})


// STOPWATCH 만들기

const stopwatch = document.getElementById("stopwatch")
const startBtn = document.getElementById("startBtn")
const stopBtn = document.getElementById("stopBtn")

//START 버튼 클릭 시
let stopwatchInterval; // start 버튼 클릭 시 생성된 setInterval을 저장할 변수
startBtn.addEventListener("click",function(){

    //이전 START 버튼 클릭으로 생성된 setInterval 지움
    clearInterval(stopwatchInterval)

    let count=0; // 클릭 후 지난 시간을 세기 위한 변수
    // count == 1 => 0.01초
    // count ==100 => 1초

    stopwatchInterval=setInterval(function(){
        count++; // 0.01초 지남
        

        let min=Math.floor(count /100 /60);
        let sec=Math.floor(count /100 % 60);
        let ms=Math.floor(count % 100);
        
        //한 자리인 경우
        if(min<10) min ="0"+ min;
        if(sec<10) sec ="0"+ sec;
        if(ms<10) ms ="0"+ ms;

        stopwatch.innerText = min + ":" + sec + ":" + ms ;

    },10) // 0.01초 마다 동작


})

stopBtn.addEventListener("click",function(){
    clearInterval(stopwatchInterval);
})

//window.open()

const open1 = document.getElementById("open1");
const open2 = document.getElementById("open2");
const open3 = document.getElementById("open3");

//새 탭으로 열기
open1.addEventListener("click",function(){
    window.open("https://www.kh-academy.co.kr")
})

//팝업창 열기
open2.addEventListener("click",function(){
    window.open("https://www.kh-academy.co.kr",
                "_blank",
                "popup"
    )
})

//팝업창 옵션 적용
open3.addEventListener("click",function(){
    window.open("https://www.kh-academy.co.kr",
        "_blank",
        "width=400,height=700,left=100,top=150")

    /* 주의 사항! */
    //top.left는 여러 모니터를 사용하는 환경에선
    //주모니터(main)에서만 적용 된다.
})


//팝업창이 부모창의 값 get/set 

const open4 = document.getElementById("open4");
open4.addEventListener("click",function(){
    window.open("popup.html","_blank","width=500,height=300")

})
