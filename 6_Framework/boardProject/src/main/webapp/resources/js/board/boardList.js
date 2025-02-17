console.log("boardList.js연결");

//글쓰기 버튼 클릭 시

document.getElementById("insertBtn").addEventListener("click",()=>{
    //JS BOM 객체 중 location

    // location.href = '주소'
    // -> 해당 주소로 요청(GET방식)

    location.href = `/board2/${location.pathname.split('/')[2]}/insert`;
                        // /board2/1/insert
    
})