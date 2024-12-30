document.getElementById("kakao").addEventListener("click",function(){
    alert("로그인 후 이용가능합니다")
})
document.getElementById("롯데리아로고").addEventListener("click",function(){
    alert("로그인 후 이용가능합니다")
})
document.getElementById("커피로고").addEventListener("click",function(){
    alert("로그인 후 이용가능합니다")
})
document.getElementById("도넛로고").addEventListener("click",function(){
    alert("로그인 후 이용가능합니다")
})

document.addEventListener("DOMContentLoaded", function() { // 페이지 다 열리면 아래 함수 실행
    let currentIndex = 0;
    const slides = document.querySelectorAll('.carousel-item'); // 클래스 carousel-item 다 불러옴
    const indicators = document.querySelectorAll('.carousel-indicators button'); // 클래스 다 불러오기

    function prevSlide() {
        if (currentIndex === 0) {
            currentIndex = slides.length - 1; // 첫 번째 슬라이드일 경우, 마지막 슬라이드로 이동 ( 전체사진갯수 -1이 0일떄)
        } else {
            currentIndex = currentIndex - 1; // 그렇지 않으면 이전 슬라이드로 이동
        }
        updateCarousel();
    }

    function nextSlide() {
        currentIndex = (currentIndex === slides.length - 1) ? 0 : currentIndex + 1; //현재인덱스가 전체 인덱스보다 1 작으면 ( 마지막 사진일떄) 0번인덱스 사진 돌아가고/ 아니면 다음인덱스로
        updateCarousel();
    }

    function goToSlide(index) {  //html에서 버튼 클릭하면 인덱스 번호 전달후 함수실행 / 현재 인덱스를 전달받은걸로 변경함
        currentIndex = index;
        updateCarousel();
    }

    function updateCarousel() { // 캐러셀 업데이트하는거
        const carouselInner = document.querySelector('.carousel-inner'); // 캐러샐 div안에 들어있는 내용물 다 불러옴
        const newTransformValue = `translateX(-${currentIndex * 100}%)`; // translateX << 가로이동 / - (인덱스번호)*100해서 다음장으로 넘어가는거처럼 보이게함 ex)1번이면 -100% 2번이면 -200%
        carouselInner.style.transform = newTransformValue;

        // indicators.forEach((indicator, i) => {
        //     if (i === currentIndex) {
        //         indicator.classList.add('active');
        //     } else {
        //         indicator.classList.remove('active');
        //     }
        // });
        for (let i = 0; i < indicators.length; i++) { // 화면 바뀔떄마다 밑에 점 버튼 색 바꾸는거
            if (i === currentIndex) {
                indicators[i].classList.add('active');
            } else {
                indicators[i].classList.remove('active');
            }
        }
    }

    setInterval(nextSlide, 3000);  //3초마다 다음장으로

    document.querySelector('.carousel-control-prev').addEventListener('click', prevSlide); // 이전버튼 누르면 함수실행
    document.querySelector('.carousel-control-next').addEventListener('click', nextSlide); // 다음 버튼 누르면 실행

    // indicators.forEach((indicator, index) => {
    //     indicator.addEventListener('click', function() {
    //         goToSlide(index);
    //     });
    // });
    for (let i = 0; i < indicators.length; i++) {
        indicators[i].addEventListener('click', function() {
            goToSlide(i);
        });
    }
});
