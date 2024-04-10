import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // useNavigate 훅을 임포트합니다.
import './SignUp.css';

function SignUp() {
    const navigate = useNavigate(); // useNavigate 훅을 사용하여 navigate 함수를 정의합니다.
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const handleSubmit = (e) => { // 이벤트 객체 e를 매개변수로 받습니다.
        e.preventDefault();
        if (password !== confirmPassword) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }
        console.log('회원가입 정보:', email, password);
        // 여기에 회원가입 로직 추가
    };

    const handleLoginClick = (e) => { // 이벤트 객체 e를 매개변수로 받습니다.
        e.preventDefault(); // 기본 이벤트를 방지합니다.
        navigate('/login'); // 로그인 페이지로 이동합니다.
    };

    return (
        <div className="auth-container">
            <form onSubmit={handleSubmit}>
                <h2>회원가입</h2>
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
                <input
                    type="password"
                    placeholder="비밀번호 확인"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                />
                <button type="submit">회원가입</button>
                <div className="signin-text">
                    <a href="#" onClick={handleLoginClick}>로그인</a>
                </div>
            </form>
        </div>
    );
}

export default SignUp;
