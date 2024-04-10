import React from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom'; // Routes와 Route 추가
import './index.css';
import reportWebVitals from './reportWebVitals';
import Main from './Main';
import Login from './Login';
import SignUp from './SignUp'; // SignUp 컴포넌트 추가


const container = document.getElementById('root');
const root = createRoot(container);

root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} /> // SignUp 경로 추가
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
