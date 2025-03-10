import './App.css';
import React, { useState, createContext } from 'react';
import SignupContainer from './Signup.js';


function App() {
  // 회원가입, 로그인 , 회원의 TodoList 출력 / 추가 / 제거 


  const [signupView,setSignupView] = useState(false);
  return (
    <>
      <button onClick={()=>{setSignupView(!signupView)}}>
        { signupView ?  ('회원 가입 닫기') : ( ' 회원 가입 열기')}
        </button>
        <div className='signup-wrapper'>
          {/* signupView가 true인 경우에만 회원 가입 컴포넌트 렌더링 */}
          {/* 조건식 && (true인 경우) */}
          {signupView === true && (<SignupContainer/>)}

        </div>
    
    </>
  );
}

export default App;
