

const 주소를설정=document.getElementById("주소를설정")
document.getElementById("주소를설정").addEventListener("click",function(){
    window.open("popup.html","_blank","width=350px,height=50px")

})



document.getElementById("롯데리아로고").addEventListener("click",function(){
   const 방식= document.getElementsByName("방식")
   let flag=false;
   
   for(let item of 방식){
    if(item.checked){
        flag=true;
        if(item.value=="배달"){
            const 배달창 =window.open("","_blank","width=350px,height=50px")
            배달창.document.write(" 배달창")
            break;
        }
        if(item.value=="포장"){
            const 포장창 =window.open("","_blank","width=350px,height=50px")
            포장창.document.write(" 포장창")
            break;
        }
   }
}
if(!flag){
 alert("방식을 선택하세요")
}
})
document.getElementById("커피로고").addEventListener("click",function(){
    const 방식= document.getElementsByName("방식")
    let flag=false;
    
    for(let item of 방식){
     if(item.checked){
         flag=true;
         if(item.value=="배달"){
             const 배달창 =window.open("","_blank","width=350px,height=50px")
             배달창.document.write(" 배달창")
             break;
         }
         if(item.value=="포장"){
             const 포장창 =window.open("","_blank","width=350px,height=50px")
             포장창.document.write(" 포장창")
             break;
         }
    }
 }
 if(!flag){
  alert("방식을 선택하세요")
 }
 })
 document.getElementById("도넛로고").addEventListener("click",function(){
    const 방식= document.getElementsByName("방식")
    let flag=false;
    
    for(let item of 방식){
     if(item.checked){
         flag=true;
         if(item.value=="배달"){
             const 배달창 =window.open("","_blank","width=350px,height=50px")
             배달창.document.write(" 배달창")
             break;
         }
         if(item.value=="포장"){
             const 포장창 =window.open("","_blank","width=350px,height=50px")
             포장창.document.write(" 포장창")
             break;
         }
    }
 }
 if(!flag){
  alert("방식을 선택하세요")
 }
 })

document.getElementById("kakao").addEventListener("click",function(){
    window.open("채팅.html","_blank","width=800px,height=500px,left=150,top=150")

    // "width=400,height=700,left=100,top=150")
})

const mpage=document.getElementById("mpagetext")
const mypage=document.getElementById("mypage")


mypage.addEventListener("mouseenter", function() {
    mpage.style.display = "block";  
});

mpage.addEventListener("mouseenter", function() {
    mpage.style.display = "block";  
});


document.body.addEventListener("mouseleave", function(event) {
    if (!mypage.contains(event.relatedTarget) && !mpage.contains(event.relatedTarget)) {
        mpage.style.display = "none"; 
    }
});


mpage.addEventListener("mouseleave", function() {
    mpage.style.display = "none";  
});


document.addEventListener("DOMContentLoaded", function() {
    let currentIndex = 0;
    const slides = document.querySelectorAll('.carousel-item');
    const indicators = document.querySelectorAll('.carousel-indicators button');

    function prevSlide() {
        currentIndex = (currentIndex === 0) ? slides.length - 1 : currentIndex - 1;
        updateCarousel();
    }

    function nextSlide() {
        currentIndex = (currentIndex === slides.length - 1) ? 0 : currentIndex + 1;
        updateCarousel();
    }

    function goToSlide(index) {
        currentIndex = index;
        updateCarousel();
    }

    function updateCarousel() {
        const carouselInner = document.querySelector('.carousel-inner');
        const newTransformValue = `translateX(-${currentIndex * 100}%)`;
        carouselInner.style.transform = newTransformValue;

        indicators.forEach((indicator, i) => {
            if (i === currentIndex) {
                indicator.classList.add('active');
            } else {
                indicator.classList.remove('active');
            }
        });
    }

    setInterval(nextSlide, 3000); 

    document.querySelector('.carousel-control-prev').addEventListener('click', prevSlide);
    document.querySelector('.carousel-control-next').addEventListener('click', nextSlide);

    indicators.forEach((indicator, index) => {
        indicator.addEventListener('click', function() {
            goToSlide(index);
        });
    });



    
});


