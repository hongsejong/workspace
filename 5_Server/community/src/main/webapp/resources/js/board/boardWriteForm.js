console.log("boardWriteForm.js연결")

//미리보기 관련 요소 모두 얻어오기

const inputImage = document.getElementsByClassName('inputImage');
const deleteImage= document.getElementsByClassName("delete-image");
const preview = document.getElementsByClassName("preview");

for(let i=0; i<inputImage.length; i++){
    // 파일이 선택되었을 때

    inputImage[i].addEventListener("change",e=>{
        

        if(e.target.files[0] != undefined){
            const reader = new FileReader(); //선택된 파일을 읽을 객체 생성

            reader.readAsDataURL(e.target.files[0]);
            // 지정된 파일을 읽음 -> result에 저장( URL)포함 
            // -> URL을 이용해서 이미지 보기 가능

            reader.onload = function(e){ //reader가 파일을 다 읽어온 경우
                
                preview[i].setAttribute("src", e.target.result)
                // e.target == reader
                // e.target.result == 읽어들인 이미지의 URL
                // preview[i] == 파일이 선택된 input태그와 인접한 preview 이미지 태그
            }
        }else{ //파일이 선택되지 않았을 때 (취소)
            preview[i].removeAttribute("src"); //src 속성 제거
        }
    })
  
}


//미리보기 삭제 버튼(x)이 클릭 되었을 때

for(let i=0; i<deleteImage.length;i++){

    deleteImage[i].addEventListener("click",()=>{

        //미리보기가 존재하는 경우에만 x버튼 동작
        if(preview[i].getAttribute("src")!=''){

            //미리보기 삭제
            preview[i].removeAttribute("src");
    
            //input의 값을 ''변경
            inputImage[i].value = '';
        }

    })

}

//게시글 작성 유효성 검사

function writeValidate(){

    //제목 미 작성 시 : 제목을 입력해주세요. 알림창
    const boardTitle=document.getElementsByName("boardTitle")[0];
    const boardContent=document.getElementsByName("boardContent")[0];

    if(boardTitle.value.trim()==''){
        alert("제목을 입력해주세요");
        //                 제목에 포커스
        boardTitle.focus();
        //                 제출 X
        return false;
    }

    if(boardContent.value.trim()==''){
        alert("내용을 입력해주세요");
        //                 내용에 포커스
        boardContent.focus();
        //                 제출 X
        return false;
    }



    
}