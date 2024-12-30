// html() 버튼

$('#btn1').on("click",function(){
    //아이디가 area인 요소의 내용을 모두 삭제
    $('#area').html("");

    //아이디가 area인 요소에 내용 출력(태그 인식o)

    $('#area').html('<p class="cls1">html() 메소드로 작성</p>')
})

//text() 버튼

$('#btn2').on("click",()=>{
    //아이디가 area인 요소의 내용을 모두 삭제
    $('#area').text("");

    //아이디가 area인 요소에 내용 출력(태그 인식X)

    $('#area').text('<p class="cls1">html() 메소드로 작성</p>')
})


$('#btn3').on("click",()=>{
    $('#inputId').val("");
    $('#inputId').val("tomato");
})

$(()=>{
    let count = 1;

    //append() : 마지막 자식 요소로 추가
    $("#list2").append("<li>리스트" + count++)
    $("#list2").append("<li>리스트" + count++)
    $("#list2").append("<li>리스트" + count++)

    //prepend() : 첫 번째 자식 요소로 추가
    $("#list2").prepend("<li>리스트" + count++)
    $("#list2").prepend("<li>리스트" + count++)
    $("#list2").prepend("<li>리스트" + count++)

    for(let i=0; i<3; i++){
        const el = '<p>새로 추가된 요소' + i +'</p>'

        //before() : 바로 이전 형제요소 추가
        $("#list2").before(el);

        //after() : 바로 다음 형제요소 추가
        $('#list2').after(el)
    }
})

//empty() 버튼

$('#btn2-1').on("click",()=>{
    //empty() : 자식 요소들을 모두 제거하는 메소드
    //          ->특정 요소 내부를 비움

    $('#area3').empty()
})

// id가 div3인 요소에 마우스가 들어왔다 나갔다 할 때의 동작

// $("#div").hover(function(){},function(){})
                 //들어왔을 때 , 나갔을 때

$("#div3").hover(function(){
    //마우스가 들어왔을 때 현재 요소에 sky 클래스 추가
    $(this).addClass("sky")

},function(){
    //마우스가 나갔을 때 현재 요소에서 sky 클래스 제거
    $(this).removeClass("sky")
})


//remove() 버튼
$('#btn2-2').on("click",function(){
    //remove : 요소 잘라내기 , 관련 이벤트 삭제
    const el = $("#div3").remove();
    console.log(el)

    $('#area4').append(el)
})

//detach()
$('#btn2-3').on("click",function(){
    //detach : 요소 잘라내기 , 관련 이벤트도 같이 잘라냄
    const el = $("#div3").detach();
    console.log(el)

    $('#area4').append(el)
})

