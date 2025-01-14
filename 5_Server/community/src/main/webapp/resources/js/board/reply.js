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
                        //삭제 버튼
                        const deleteBtn = document.createElement("button");
                        deleteBtn.innerText="삭제";
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

