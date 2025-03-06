import React, {useState} from "react";

// 컴포넌트의 이름은 대문자!
// 리액트는 컴포넌트의 상태가 변할 때 마다 리렌더링을 수행 함
const InputTest = () => {

   // let inputValue='초기값';
   const [inputValue, setInputValue] = useState('초기값');
   //       변수           함수
   // inputValue = 값을 저장할 변수
   // setInputValue : inputValue에 값을 대입하는 setter 역할의 함수
   // useState('초기값') : '초기값'을 inputValue변수에 대입

   const changeInputValue= (e) => {
    console.log(e.target.value)
    setInputValue(e.target.value)

   }
    return(
        <>
            <input value={inputValue} onChange={changeInputValue}/>
            {/* onChange={e=>{setInputValue(e.target.value)}} */}
        </>
        // 첫 레더링 : value = "초기값"
        // -> input의 값을 변경
        // 1)onChange(값이 변했을 때) changeInputValue함수 실행되면서
        //   inputValue에 e.target.value(변경된 값)을 대입

        // 2) 컴포넌트의 상태 변화 -> 리렌더링 진행

        // 리렌더링 -> value= 변경된 inputValue의 값
    );
}

export default InputTest;