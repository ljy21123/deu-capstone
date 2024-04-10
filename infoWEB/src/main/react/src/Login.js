import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // useNavigate 훅 추가
import './Login.css'; // CSS 파일을 임포트합니다.

function Login() {
    const navigate = useNavigate(); // useNavigate 훅 사용
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('로그인 정보:', email, password);
    };

    const handleSignUpClick = (e) => {
        e.preventDefault(); // 이 부분 추가
        console.log('회원가입 페이지로 이동');
        navigate('/signup');
    };
    
    const handleBackClick = (e) => {
        e.preventDefault(); // 기본 동작 방지
        navigate(-1); // 이전 페이지로 이동
    };

    return (
        <div className="auth-container">
            <form onSubmit={handleSubmit}>
                <h2>로그인</h2>
                <input
                    type="email"
                    placeholder="이메일"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="비밀번호"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <button type="submit">로그인</button>
                <div className="navigation-links">
                    <a href="#" onClick={handleBackClick}>돌아가기</a> {/* "돌아가기" 링크 추가 */}
                    <span> | </span> {/* 구분자 추가 */}
                    <a href="#" onClick={handleSignUpClick}>회원가입</a> {/* "회원가입" 링크 유지 */}
                </div>
            </form>
        </div>
    );
}

export default Login;
