//제목 클릭 시
//alert(000은 xxx원 입니다.) 출력

console.log("book.js연결");

const bookTitleList = document.querySelectorAll(".book-title");
//bookTitleList == 배열
//-> 요소를 하나씩 꺼낸 경우 == 제목 td요소
//-> 요소를 하나씩 꺼내서 클릭된 경우라는 이벤트 리스너 추가
for(let item of  bookTitleList){
    item.addEventListener("click",e=>{
        //제목
        const t=e.target.innerText;

        //가격
        // const p=e.target.nextElementSibling.nextElementSibling.innerText;


        // data-price 속성 값 얻어오기
        const p=e.target.getAttribute("data-price")

        // alert(t+ "은/는 " +p+"원 입니다.");

        //`${백틱}`
        alert(`${t} 은/는 ${p}원 입니다.`)

    })

}
