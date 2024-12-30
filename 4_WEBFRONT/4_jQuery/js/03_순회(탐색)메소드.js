$(function(){
    // parent() : / 선택된 요소의 바로 상위 요소(부모) 선택
    // span 태그의 부모요소의 테두리, 글자색 변경

    $("span").parent().css("border","2px solid red").css("color","red")


    // $('요소명').parents([매개변수])
    // - 선택된 요소의 모든 상위 요소 리턴
    // - 매개변수가 있으면 매개변수와 일치하는 부모만 리턴


    //li태그의 모든 상위 요소의 글자색을 파란색으로 변경
    $("li").parents().css("color","blue")


    //li 태그의 상위 요소 중 div만 선택하여 테두리 변경

    $("li").parents("div").css("border", "2px dashed lightCoral")


    //span 태그부터 상위 요소 중
    //div 태그를 만나기 이전까지 요소를 선택하여 배경색 변경

    $("span").parentsUntil("div").css("backgroundColor","yellow")

    // $('요소명').parentsUntil(매개변수)
    // - 선택된 요소부터 매개변수 요소 전까지 범위의 요소 리턴

})
