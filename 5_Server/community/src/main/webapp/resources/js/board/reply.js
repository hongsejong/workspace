//댓글 목록 조회(ajax)
function selectReplyList(){
    // contextPath, boardNo, memberNo 전역 변수 사용 (boardDetail.jsp 작성되있음)
    $.ajax({
        url : contextPath + "/reply/selectReplyList",
        data : {"boardNo" : boardNo},
        dataType : "JSON", // JSON 형태의 문자열 응답 데이터를 JS객체로 자동 변환
        success : function(rList){
            console.log(rList)

            // 화면에 출력되어있는 댓글 목록 삭제
            const replyList=document.getElementById("reply-list")
            replyList.innerText="";
                for(let reply of rList){

                  
                    // 행
                    const replyRow = document.createElement("li");
                    replyRow.classList.add("reply-row");
                    // 작성자
                    const replyWriter = document.createElement("p");
                    replyWriter.classList.add("reply-writer");

                    // 프로필 이미지
                    const profileImage = document.createElement("img");
                    console.log(reply)
                    if(reply.profileImage!=null){  //프로필 이미지가 있는 경우
                        profileImage.setAttribute("src",contextPath +reply.profileImage);
                            
                    }else{ //프로필 이미지가 없는 경우

                        profileImage.setAttribute("src",contextPath +"/resources/images/user.png");
                                                               
                    }

                    // 작성자 닉네임

                    const memberNickname = document.createElement("span");
                    memberNickname.innerText =reply.memberNickname;

                    // 작성일
                    const replyDate = document.createElement("span");
                    replyDate.classList.add("reply-date");
                    replyDate.innerText =reply.createDate;

                    //작성자 영역(p)에 프로필,닉네임,작성일을 마지막 자식으로 추가

                    replyWriter.append(profileImage,memberNickname,replyDate);

                    // 댓글 내용

                    const replyContent = document.createElement("p");
                    replyContent.classList.add("reply-content");
                    replyContent.innerHTML = reply.replyContent;

                     //행에 작성자,댓글내용 추가
                     replyRow.append(replyWriter,replyContent)

                    
                    // 로그인한 회원 번호와 댓글 작성자의 회원번호가 같은 경우
                    if(loginMemberNo==reply.memberNo){
                        //버튼 영역
                        const replyBtnArea = document.createElement("div");
                        replyBtnArea.classList.add("reply-btn-area");

                        //수정 버튼
                        const updateBtn = document.createElement("button");
                        updateBtn.innerText="수정";
                        updateBtn.setAttribute("onclick",`showUpdateReply(${reply.replyNo},this)`)
                        //삭제 버튼
                        const deleteBtn = document.createElement("button");
                        deleteBtn.innerText="삭제";
                        deleteBtn.setAttribute("onclick",`deleteReply(${reply.replyNo})`)
                        //버튼 영역에 수정,삭제 버튼을 마지막 자식으로 추가
                        replyBtnArea.append(updateBtn,deleteBtn);

                        //행에 버튼 영역 추가
                        replyRow.append(replyBtnArea);
                    }


              
                    
                    //댓글 목록(#reply-list)에 행(li) 추가
                    // const replyList= document.getElementById("reply-list")
                    // replyList.append(replyRow)
                    document.getElementById("reply-list").append(replyRow);
                }


        },
        error : function(){
            console.log("에러발생")
        }
    })
}

//--------------------------------------------------

// 댓글 등록

const replyContent=document.getElementById("replyContent");
const addReply = document.getElementById("addReply");

addReply.addEventListener("click", ()=>{ //댓글 등록 버튼이 클릭 되었을 때

    // 1) 로그인 여부 확인
    if(loginMemberNo==""){ //로그인X
        alert("로그인 후 이용해주세요.");
        return;
    }

    // 2) 댓글 내용 작성 여부 확인
    if(replyContent.value.trim()==""){ //댓글 미작성 시
        alert("댓글을 작성한 후 버튼을 클릭해주세요.")
        replyContent.focus();
        return;

    }


    // 3) AJAX를 이용해서 댓글 내용 DB에 저장

    $.ajax({
        url : contextPath + "/reply/insert",
        data : {"replyContent": replyContent.value,
                "memberNo" : loginMemberNo,
                "boardNo" : boardNo
        },
        type : "POST",
        success : function(result){
            if(result!=0){
                selectReplyList() //비동기 댓글 목록 조회 함수 호출
                console.log("댓글 등록 성공")
                alert("등록 성공")
                replyContent.value=""; //작성했던 댓글 내용 삭제
            }else{
                alert("댓글 등록 실패")
            }
        },
        error : function(req,status,error){
            console.log("댓글 등록 실패");
            console.log(req.responseText);
            alert("등록 실패")

        }
    })
})

//--------------------------------------------------
//댓글 삭제
 // 요청주소 : /community/reply/delete
        // 파라미터 : key : "replyNo", value : 매개변수 replyNo
        // 전달방식 : "GET"
        // success : 삭제 성공 시 -> "삭제되었습니다." alert로 출력 후
        //                          댓글 목록 비동기 조회 함수 호출 

        //          삭제 실패 시 -> "삭제 실패" alert로 출력

        // error : 앞 error 코드 참고
function deleteReply(replyNo){

    if(confirm("정말로 삭제하시겠습니까?")){
       
        $.ajax({
            url : contextPath + "/reply/delete",
            data : {"replyNo": replyNo
},
            success : function(result){
                if(result!=0){
                    alert("삭제되었습니다.")
                    selectReplyList() //비동기 댓글 목록 조회 함수 호출
                   
                }else{
                    alert("삭제 실패")
                }
            },
            error : function(req,status,error){
                console.log("댓글 등록 실패");
                console.log(req.responseText);
                alert("등록 실패")
    
            }
        })
    
    }

}


//-----------------------------------

//댓글 수정 화면 전환

let beforeReplyRow; //수정 전 원래 행의 상태를 저장할 변수
function showUpdateReply(replyNo,btn){
                    //댓글 번호, 이벤트발생요소(수정버튼)
                    
    // ** 댓글 수정이 한 개만 열릴 수 있도록 만들기 **
    const updateTA = document.getElementsByClassName("update-textarea");
    
    if(updateTA.length >0){ //수정이 한 개 이상 열려있는 경우
        if(confirm("다른 댓글이 수정 중 입니다. 현재 댓글을 수정하시겠습니까?")){

            updateTA[0].parentElement.innerHTML = beforeReplyRow;
            //reply-row                             //백업한 댓글
            //-> 백업 내용으로 덮어 씌워지면서 textarea 사라짐
        }else{ //취소
            return;
        }

    }

    //1. 댓글 수정이 클릭된 행을 선택
    const replyRow=btn.parentElement.parentElement; //수정버튼의 부모의 부모

    //2. 행 내용 삭제 전 현재 상태를 저장(백업)
    //-> 전역변수 (beforeReplyRow) 이용

    beforeReplyRow= replyRow.innerHTML;
    // console.log(beforeReplyRow);

    // 3. 댓글에 작성되어 있던 내용만 얻어오기
    // -> 새롭게 생성된 textarea에 추가 예정

    let beforeContent = replyRow.children[1].innerHTML;
    // console.log(beforeContent)

    //이것도 가능
    // console.log(btn.parentElement.previousElementSibling.innerHtml);

    //4. 댓글 행 내부 내용을 모두 삭제

    replyRow.innerHTML="";

    //5. textarea요소 생성+ 클래스 추가 + ** 내용 추가 **
    const textarea = document.createElement("textarea");
    textarea.classList.add("update-textarea");

    // *****************************************
    // XSS 방지 처리 해제
    beforeContent = beforeContent.replaceAll("&amp","&"); //1빠여야함
    beforeContent = beforeContent.replaceAll("&lt","<");
    beforeContent = beforeContent.replaceAll("&gt",">");
    beforeContent = beforeContent.replaceAll("&quot","\"");

    // 개행 문자 처리 해제
    beforeContent = beforeContent.replaceAll("<br>","\n");

    // *****************************************
    textarea.value = beforeContent;

    //6. replyRow에 생성된 textarea 추가
    replyRow.append(textarea);


    //7. 버튼영역 + 수정 +취소버튼 생성
    const div = document.createElement("div");
    div.classList.add("reply-btn-area");
    const btn1 = document.createElement("button");
    btn1.innerText="수정"
    btn1.setAttribute("onclick", `updateReply(${replyNo},this)`);
    const btn2 = document.createElement("button");
    btn2.innerText="취소"
    btn2.setAttribute("onclick", 'updateCancel(this)');

    div.append(btn1,btn2);

    //8. replyRow에 버튼 영역 추가
    replyRow.append(div);


}

//----------------------------
// 댓글 수정 취소
function updateCancel(btn){
                //클릭된 취소 버튼(btn)
    if(confirm("댓글 수정을 취소하시겠습니까?")){
        btn.parentElement.parentElement.innerHTML= beforeReplyRow;
        // beforeReplyRow(전역변수)
    }
}



//----------------------------------
//댓글 수정(ajax)

//댓글 수정 성공 시 : 댓글이 수정되었습니다. 알림창 출력 후 댓글 목록 조회
//댓글 수정 실패 시 : 댓글 수정 실패 알림창
function updateReply(replyNo,btn){
    console.log(replyNo)
    console.log(btn.parentElement.parentElement.children[0].value)
    // const updateContent=btn.parentElement.parentElement.children[0].value;
    //새로 작성된 댓글 내용 얻어오기
    // const replyContent = btn.parentElement.previousElementSibling.value;
    const updateContent=btn.parentElement.previousElementSibling.value;

    $.ajax({
        url : contextPath + "/reply/update",
        data : {"replyNo": replyNo,"updateContent" : updateContent},
        type : "POST",
        success : function(result){
            if(result!=0){
                alert("수정되었습니다.")
                selectReplyList() //비동기 댓글 목록 조회 함수 호출
               
            }else{
                alert("수정 실패")
            }
        },
        error : function(req,status,error){
            console.log("댓글 수정 실패");
            console.log(req.responseText);
            alert("수정 실패")

        }
    })
    

}