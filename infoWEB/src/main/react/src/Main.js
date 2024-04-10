import React from 'react';
import "./Main.css";
import { useNavigate } from 'react-router-dom'; // useNavigate 훅 임포트
import Login from './Login'; // 경로에 맞게 import 조정


function Main(){
  let navigate = useNavigate(); // navigate 함수 생성

  const handleSignUpClick = () => {
    navigate('/login'); // '/login' 경로로 이동
  };
  
  return (


<div key="1" data-bs-theme="auto" lang="en">
  <head>
    <script src="../assets/js/color-modes.js" />
    <meta charSet="utf-8" />
    <meta
      content="width=device-width, initial-scale=1 maximum-scale=1" 
      name="viewport"
    />
    <meta
      content=""
      name="description"
    />
    <meta
      content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
      name="author"
    />
    <meta
      content="Hugo 0.122.0"
      name="generator"
    />
    <title>
      Blog Template · Bootstrap v5.3
    </title>
    <link
      href="https://getbootstrap.com/docs/5.3/examples/blog/"
      rel="canonical"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/@docsearch/css@3"
      rel="stylesheet"
    />
    <link
      href="../assets/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <style
      dangerouslySetInnerHTML={{
        __html: '      .bd-placeholder-img {        font-size: 1.125rem;        text-anchor: middle;        -webkit-user-select: none;        -moz-user-select: none;        user-select: none;      }      @media (min-width: 768px) {        .bd-placeholder-img-lg {          font-size: 3.5rem;        }      }      .b-example-divider {        width: 100%;        height: 3rem;        background-color: rgba(0, 0, 0, .1);        border: solid rgba(0, 0, 0, .15);        border-width: 1px 0;        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);      }      .b-example-vr {        flex-shrink: 0;        width: 1.5rem;        height: 100vh;      }      .bi {        vertical-align: -.125em;        fill: currentColor;      }      .nav-scroller {        position: relative;        z-index: 2;        height: 2.75rem;        overflow-y: hidden;      }      .nav-scroller .nav {        display: flex;        flex-wrap: nowrap;        padding-bottom: 1rem;        margin-top: -1px;        overflow-x: auto;        text-align: center;        white-space: nowrap;        -webkit-overflow-scrolling: touch;      }      .btn-bd-primary {        --bd-violet-bg: #712cf9;        --bd-violet-rgb: 112.520718, 44.062154, 249.437846;        --bs-btn-font-weight: 600;        --bs-btn-color: var(--bs-white);        --bs-btn-bg: var(--bd-violet-bg);        --bs-btn-border-color: var(--bd-violet-bg);        --bs-btn-hover-color: var(--bs-white);        --bs-btn-hover-bg: #6528e0;        --bs-btn-hover-border-color: #6528e0;        --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);        --bs-btn-active-color: var(--bs-btn-hover-color);        --bs-btn-active-bg: #5a23c8;        --bs-btn-active-border-color: #5a23c8;      }      .bd-mode-toggle {        z-index: 1500;      }      .bd-mode-toggle .dropdown-menu .active .bi {        display: block !important;      }    '
      }}
     />
    <link
      href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900&display=swap"
      rel="stylesheet"
    />
    <link
      href="blog.css"
      rel="stylesheet"
    />
  </head>
  
    <svg
      className="d-none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <symbol
        id="check2"
        viewBox="0 0 16 16"
      >
        <path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z" />
      </symbol>
      <symbol
        id="circle-half"
        viewBox="0 0 16 16"
      >
        <path d="M8 15A7 7 0 1 0 8 1v14zm0 1A8 8 0 1 1 8 0a8 8 0 0 1 0 16z" />
      </symbol>
      <symbol
        id="moon-stars-fill"
        viewBox="0 0 16 16"
      >
        <path d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z" />
        <path d="M10.794 3.148a.217.217 0 0 1 .412 0l.387 1.162c.173.518.579.924 1.097 1.097l1.162.387a.217.217 0 0 1 0 .412l-1.162.387a1.734 1.734 0 0 0-1.097 1.097l-.387 1.162a.217.217 0 0 1-.412 0l-.387-1.162A1.734 1.734 0 0 0 9.31 6.593l-1.162-.387a.217.217 0 0 1 0-.412l1.162-.387a1.734 1.734 0 0 0 1.097-1.097l.387-1.162zM13.863.099a.145.145 0 0 1 .274 0l.258.774c.115.346.386.617.732.732l.774.258a.145.145 0 0 1 0 .274l-.774.258a1.156 1.156 0 0 0-.732.732l-.258.774a.145.145 0 0 1-.274 0l-.258-.774a1.156 1.156 0 0 0-.732-.732l-.774-.258a.145.145 0 0 1 0-.274l.774-.258c.346-.115.617-.386.732-.732L13.863.1z" />
      </symbol>
      <symbol
        id="sun-fill"
        viewBox="0 0 16 16"
      >
        <path d="M8 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z" />
      </symbol>
    </svg>
    <div className="dropdown position-fixed bottom-0 end-0 mb-3 me-3 bd-mode-toggle">
      <button
        aria-expanded="false"
        aria-label="Toggle theme (auto)"
        className="btn btn-bd-primary py-2 dropdown-toggle d-flex align-items-center"
        data-bs-toggle="dropdown"
        id="bd-theme"
        type="button"
      >
        <svg
          className="bi my-1 theme-icon-active"
          height="1em"
          width="1em"
        >
          <use href="#circle-half" />
        </svg>
        <span
          className="visually-hidden"
          id="bd-theme-text"
        >
          Toggle theme
        </span>
      </button>
      <ul
        aria-labelledby="bd-theme-text"
        className="dropdown-menu dropdown-menu-end shadow"
      >
        <li>
          <button
            aria-pressed="false"
            className="dropdown-item d-flex align-items-center"
            data-bs-theme-value="light"
            type="button"
          >
            <svg
              className="bi me-2 opacity-50"
              height="1em"
              width="1em"
            >
              <use href="#sun-fill" />
            </svg>
            Light
            <svg
              className="bi ms-auto d-none"
              height="1em"
              width="1em"
            >
              <use href="#check2" />
            </svg>
          </button>
        </li>
        <li>
          <button
            aria-pressed="false"
            className="dropdown-item d-flex align-items-center"
            data-bs-theme-value="dark"
            type="button"
          >
            <svg
              className="bi me-2 opacity-50"
              height="1em"
              width="1em"
            >
              <use href="#moon-stars-fill" />
            </svg>
            Dark
            <svg
              className="bi ms-auto d-none"
              height="1em"
              width="1em"
            >
              <use href="#check2" />
            </svg>
          </button>
        </li>
        <li>
          <button
            aria-pressed="true"
            className="dropdown-item d-flex align-items-center active"
            data-bs-theme-value="auto"
            type="button"
          >
            <svg
              className="bi me-2 opacity-50"
              height="1em"
              width="1em"
            >
              <use href="#circle-half" />
            </svg>
            Auto
            <svg
              className="bi ms-auto d-none"
              height="1em"
              width="1em"
            >
              <use href="#check2" />
            </svg>
          </button>
        </li>
      </ul>
    </div>
    <svg
      className="d-none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <symbol
        fill="none"
        id="aperture"
        stroke="currentColor"
        strokeLinecap="round"
        strokeLinejoin="round"
        strokeWidth="2"
        viewBox="0 0 24 24"
      >
        <circle
          cx="12"
          cy="12"
          r="10"
        />
        <path d="M14.31 8l5.74 9.94M9.69 8h11.48M7.38 12l5.74-9.94M9.69 16L3.95 6.06M14.31 16H2.83m13.79-4l-5.74 9.94" />
      </symbol>
      <symbol
        id="cart"
        viewBox="0 0 16 16"
      >
        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
      </symbol>
      <symbol
        id="chevron-right"
        viewBox="0 0 16 16"
      >
        <path
          d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"
          fillRule="evenodd"
        />
      </symbol>
    </svg>
    <div className="container">
      <header className="border-bottom lh-1 py-3">
        <div className="row flex-nowrap justify-content-between align-items-center">
          <div className="col-4 pt-1">
            <a
              className="link-secondary"
              href="#"
            >
              정보요약시스템
            </a>
          </div>
          <div className="col-4 text-center">
            <a
              className="blog-header-logo text-body-emphasis text-decoration-none"
              href="#"
            >
              ClearSum
            </a>
          </div>
          <div className="col-4 d-flex justify-content-end align-items-center">
            <a
              aria-label="Search"
              className="link-secondary"
              href="#"
            >
              <svg
                className="mx-3"
                fill="none"
                height="20"
                role="img"
                stroke="currentColor"
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                viewBox="0 0 24 24"
                width="20"
                xmlns="http://www.w3.org/2000/svg"
              >
                <title>
                  Search
                </title>
                <circle
                  cx="10.5"
                  cy="10.5"
                  r="7.5"
                />
                <path d="M21 21l-5.2-5.2" />
              </svg>
            </a>
            <div className="col-4 d-flex justify-content-end align-items-center">
      <button className="btn btn-sm btn-outline-secondary" onClick={handleSignUpClick}>
        Login
      </button>
    </div>
          </div>
        </div>
      </header>
      <div className="nav-scroller py-1 mb-3 border-bottom">
  <nav className="nav nav-underline justify-content-between">
    <a className="nav-item nav-link link-body-emphasis active" href="#">
      카테고리
    </a>
    <a className="nav-item nav-link link-body-emphasis" href="#">
      정치
    </a>
    <a className="nav-item nav-link link-body-emphasis" href="#">
      경제
    </a>
    <a className="nav-item nav-link link-body-emphasis" href="#">
      사회
    </a>
    <a className="nav-item nav-link link-body-emphasis" href="#">
      생활
    </a>
    <a className="nav-item nav-link link-body-emphasis" href="#">
      과학
    </a>
    <a className="nav-item nav-link link-body-emphasis" href="#">
      세계
    </a>
    <a className="nav-item nav-link link-body-emphasis" href="#">
      주식
    </a>
  </nav>
</div>

    </div>
    <main className="container">
    <div className="p-2 p-md-4 mb-3 rounded text-body-emphasis bg-body-secondary d-flex align-items-center justify-content-center" style={{ maxWidth: '10000px', maxHeight: '1000px' }}>
  <div className="col-lg-10 px-0"> {/* 수정된 부분 */}
    <h1 className="display-10 font-malgun text-center">
      
    </h1>
    <div>
    <div className="text-center"> {/* 수정된 부분 */}
      <h4 className="fst-italic">
        Today's topic
      </h4>
    </div> {/* 수정된 부분 */}
      <div className="row">
        {/* 왼쪽에 포스트 */}
        <div className="col-lg-6">
          <ul className="list-unstyled">
            <li>
              <a
                className="d-flex flex-column flex-lg-row gap-3 align-items-start align-items-lg-center py-3 link-body-emphasis text-decoration-none border-top"
                href="#"
              >
                <svg
                  aria-hidden="true"
                  className="bd-placeholder-img"
                  focusable="false"
                  height="96"
                  preserveAspectRatio="xMidYMid slice"
                  width="100%"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <rect
                    fill="#777"
                    height="100%"
                    width="100%"
                  />
                </svg>
                <div className="col-lg-8">
                  <h6 className="mb-0">
                    Example blog post title 1
                  </h6>
                  <small className="text-body-secondary">
                    January 15, 2024
                  </small>
                </div>
              </a>
            </li>
            <li>
              <a
                className="d-flex flex-column flex-lg-row gap-3 align-items-start align-items-lg-center py-3 link-body-emphasis text-decoration-none border-top"
                href="#"
              >
                <svg
                  aria-hidden="true"
                  className="bd-placeholder-img"
                  focusable="false"
                  height="96"
                  preserveAspectRatio="xMidYMid slice"
                  width="100%"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <rect
                    fill="#777"
                    height="100%"
                    width="100%"
                  />
                </svg>
                <div className="col-lg-8">
                  <h6 className="mb-0">
                    Example blog post title 2
                  </h6>
                  <small className="text-body-secondary">
                    January 14, 2024
                  </small>
                </div>
              </a>
            </li>
            <li>
              <a
                className="d-flex flex-column flex-lg-row gap-3 align-items-start align-items-lg-center py-3 link-body-emphasis text-decoration-none border-top"
                href="#"
              >
                <svg
                  aria-hidden="true"
                  className="bd-placeholder-img"
                  focusable="false"
                  height="96"
                  preserveAspectRatio="xMidYMid slice"
                  width="100%"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <rect
                    fill="#777"
                    height="100%"
                    width="100%"
                  />
                </svg>
                <div className="col-lg-8">
                  <h6 className="mb-0">
                    Example blog post title 3
                  </h6>
                  <small className="text-body-secondary">
                    January 12, 2024
                  </small>
                </div>
              </a>
            </li>
          </ul>
        </div>
        {/* 오른쪽에 포스트 */}
        <div className="col-lg-6">
          <ul className="list-unstyled">
            <li>
              <a
                className="d-flex flex-column flex-lg-row gap-3 align-items-start align-items-lg-center py-3 link-body-emphasis text-decoration-none border-top"
                href="#"
              >
                <svg
                  aria-hidden="true"
                  className="bd-placeholder-img"
                  focusable="false"
                  height="96"
                  preserveAspectRatio="xMidYMid slice"
                  width="100%"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <rect
                    fill="#777"
                    height="100%"
                    width="100%"
                  />
                </svg>
                <div className="col-lg-8">
                  <h6 className="mb-0">
                    Example blog post title 4
                  </h6>
                  <small className="text-body-secondary">
                    January 13, 2024
                  </small>
                </div>
              </a>
            </li>
            <li>
              <a
                className="d-flex flex-column flex-lg-row gap-3 align-items-start align-items-lg-center py-3 link-body-emphasis text-decoration-none border-top"
                href="#"
              >
                <svg
                  aria-hidden="true"
                  className="bd-placeholder-img"
                  focusable="false"
                  height="96"
                  preserveAspectRatio="xMidYMid slice"
                  width="100%"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <rect
                    fill="#777"
                    height="100%"
                    width="100%"
                  />
                </svg>
                <div className="col-lg-8">
                  <h6 className="mb-0">
                    Example blog post title 5
                  </h6>
                  <small className="text-body-secondary">
                    January 12, 2024
                  </small>
                </div>
              </a>
            </li>
            <li>
              <a
                className="d-flex flex-column flex-lg-row gap-3 align-items-start align-items-lg-center py-3 link-body-emphasis text-decoration-none border-top"
                href="#"
              >
                <svg
                  aria-hidden="true"
                  className="bd-placeholder-img"
                  focusable="false"
                  height="96"
                  preserveAspectRatio="xMidYMid slice"
                  width="100%"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <rect
                    fill="#777"
                    height="100%"
                    width="100%"
                  />
                </svg>
                <div className="col-lg-8">
                  <h6 className="mb-0">
                    Example blog post title 6
                  </h6>
                  <small className="text-body-secondary">
                    January 11, 2024
                  </small>
                </div>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>

<h3 className="pb-0 mb-3">
      <nav aria-label="Pagination" className="blog-pagination">
        <a className="btn btn-outline-primary rounded-pill" href="#">
          #키워드
        </a>
        <a className="btn btn-outline-primary rounded-pill" href="#">
        #키워드
        </a>
        <a className="btn btn-outline-primary rounded-pill" href="#">
        #키워드
        </a>
        <a className="btn btn-outline-primary rounded-pill" href="#">
        #키워드
        </a>
        <a className="btn btn-outline-primary rounded-pill" href="#">
        #키워드
        </a>
        <a className="btn btn-outline-primary rounded-pill" href="#">
        #키워드
        </a>
        <a className="btn btn-outline-primary rounded-pill" href="#">
        #키워드
        </a>
        <a className="btn btn-outline-primary rounded-pill" href="#">
        #키워드
        </a>
        <a className="btn btn-outline-primary rounded-pill" href="#">
        #키워드
        </a><a className="btn btn-outline-primary rounded-pill" href="#">
        #키워드
        </a><a className="btn btn-outline-primary rounded-pill" href="#">
        #키워드
        </a><a className="btn btn-outline-primary rounded-pill" href="#">
        #키워드
        </a><a className="btn btn-outline-primary rounded-pill" href="#">
        #키워드
        </a>
        {/*<a
          aria-disabled="true"
          className="btn btn-outline-secondary rounded-pill disabled"
        >
          Newer
  </a> */}
      </nav>
    </h3>
    <h3 className="pb-0 mb-3">
  <nav aria-label="Pagination" className="blog-pagination">
    {/* 페이지네이션 버튼들 */}
  </nav>
</h3>
<div className="text-center">
  <div className="mx-auto" style={{ maxWidth: '500px' }}>
    <img src="Cloud.jpg" className="img-fluid rounded" alt="Your Image" /> {/* 수정된 부분 */}
  </div>
</div>

<div className="container mt-3 mb-3">
  <div className="row">
    <div className="col-md-6 border-end">
      <h3 className="text-center font-malgun">헤드라인</h3>
    </div>
    <div className="col-md-6">
      <h3 className="text-center font-malgun">실시간 뉴스</h3>
    </div>
  </div>
</div>




      <div className="row mb-2">
        <div className="col-md-6">
          <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div className="col p-4 d-flex flex-column position-static">
              <strong className="d-inline-block mb-2 text-primary-emphasis">
                정치
              </strong>
              <h3 className="mb-0">
                Featured post
              </h3>
              <div className="mb-1 text-body-secondary">
                Nov 12
              </div>
              <p className="card-text mb-auto">
                This is a wider card with supporting text below as a natural lead-in to additional content.
              </p>
              <a
                className="icon-link gap-1 icon-link-hover stretched-link"
                href="#"
              >
                Continue reading
                <svg className="bi">
                  <use xlinkHref="#chevron-right" />
                </svg>
              </a>
            </div>
            <div className="col-auto d-none d-lg-block">
              <svg
                aria-label="Placeholder: Thumbnail"
                className="bd-placeholder-img"
                focusable="false"
                height="250"
                preserveAspectRatio="xMidYMid slice"
                role="img"
                width="200"
                xmlns="http://www.w3.org/2000/svg"
              >
                <title>
                  Placeholder
                </title>
                <rect
                  fill="#55595c"
                  height="100%"
                  width="100%"
                />
                <text
                  dy=".3em"
                  fill="#eceeef"
                  x="50%"
                  y="50%"
                >
                  Thumbnail
                </text>
              </svg>
            </div>
          </div>
        </div>
        

        
        <div className="col-md-6">
          <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div className="col p-4 d-flex flex-column position-static">
              <strong className="d-inline-block mb-2 text-success-emphasis">
                경제
              </strong>
              <h3 className="mb-0">
                Featured post
              </h3>
              <div className="mb-1 text-body-secondary">
                Nov 11
              </div>
              <p className="mb-auto">
                This is a wider card with supporting text below as a natural lead-in to additional content.
              </p>
              <a
                className="icon-link gap-1 icon-link-hover stretched-link"
                href="#"
              >
                Continue reading
                <svg className="bi">
                  <use xlinkHref="#chevron-right" />
                </svg>
              </a>
            </div>
            <div className="col-auto d-none d-lg-block">
              <svg
                aria-label="Placeholder: Thumbnail"
                className="bd-placeholder-img"
                focusable="false"
                height="250"
                preserveAspectRatio="xMidYMid slice"
                role="img"
                width="200"
                xmlns="http://www.w3.org/2000/svg"
              >
                <title>
                  Placeholder
                </title>
                <rect
                  fill="#55595c"
                  height="100%"
                  width="100%"
                />
                <text
                  dy=".3em"
                  fill="#eceeef"
                  x="50%"
                  y="50%"
                >
                  Thumbnail
                </text>
              </svg>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div className="col p-4 d-flex flex-column position-static">
              <strong className="d-inline-block mb-2 text-primary-emphasis">
                사회
              </strong>
              <h3 className="mb-0">
                Featured post
              </h3>
              <div className="mb-1 text-body-secondary">
                Nov 12
              </div>
              <p className="card-text mb-auto">
                This is a wider card with supporting text below as a natural lead-in to additional content.
              </p>
              <a
                className="icon-link gap-1 icon-link-hover stretched-link"
                href="#"
              >
                Continue reading
                <svg className="bi">
                  <use xlinkHref="#chevron-right" />
                </svg>
              </a>
            </div>
            <div className="col-auto d-none d-lg-block">
              <svg
                aria-label="Placeholder: Thumbnail"
                className="bd-placeholder-img"
                focusable="false"
                height="250"
                preserveAspectRatio="xMidYMid slice"
                role="img"
                width="200"
                xmlns="http://www.w3.org/2000/svg"
              >
                <title>
                  Placeholder
                </title>
                <rect
                  fill="#55595c"
                  height="100%"
                  width="100%"
                />
                <text
                  dy=".3em"
                  fill="#eceeef"
                  x="50%"
                  y="50%"
                >
                  Thumbnail
                </text>
              </svg>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div className="col p-4 d-flex flex-column position-static">
              <strong className="d-inline-block mb-2 text-primary-emphasis">
                생활
              </strong>
              <h3 className="mb-0">
                Featured post
              </h3>
              <div className="mb-1 text-body-secondary">
                Nov 12
              </div>
              <p className="card-text mb-auto">
                This is a wider card with supporting text below as a natural lead-in to additional content.
              </p>
              <a
                className="icon-link gap-1 icon-link-hover stretched-link"
                href="#"
              >
                Continue reading
                <svg className="bi">
                  <use xlinkHref="#chevron-right" />
                </svg>
              </a>
            </div>
            <div className="col-auto d-none d-lg-block">
              <svg
                aria-label="Placeholder: Thumbnail"
                className="bd-placeholder-img"
                focusable="false"
                height="250"
                preserveAspectRatio="xMidYMid slice"
                role="img"
                width="200"
                xmlns="http://www.w3.org/2000/svg"
              >
                <title>
                  Placeholder
                </title>
                <rect
                  fill="#55595c"
                  height="100%"
                  width="100%"
                />
                <text
                  dy=".3em"
                  fill="#eceeef"
                  x="50%"
                  y="50%"
                >
                  Thumbnail
                </text>
              </svg>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div className="col p-4 d-flex flex-column position-static">
              <strong className="d-inline-block mb-2 text-primary-emphasis">
                과학
              </strong>
              <h3 className="mb-0">
                Featured post
              </h3>
              <div className="mb-1 text-body-secondary">
                Nov 12
              </div>
              <p className="card-text mb-auto">
                This is a wider card with supporting text below as a natural lead-in to additional content.
              </p>
              <a
                className="icon-link gap-1 icon-link-hover stretched-link"
                href="#"
              >
                Continue reading
                <svg className="bi">
                  <use xlinkHref="#chevron-right" />
                </svg>
              </a>
            </div>
            <div className="col-auto d-none d-lg-block">
              <svg
                aria-label="Placeholder: Thumbnail"
                className="bd-placeholder-img"
                focusable="false"
                height="250"
                preserveAspectRatio="xMidYMid slice"
                role="img"
                width="200"
                xmlns="http://www.w3.org/2000/svg"
              >
                <title>
                  Placeholder
                </title>
                <rect
                  fill="#55595c"
                  height="100%"
                  width="100%"
                />
                <text
                  dy=".3em"
                  fill="#eceeef"
                  x="50%"
                  y="50%"
                >
                  Thumbnail
                </text>
              </svg>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div className="col p-4 d-flex flex-column position-static">
              <strong className="d-inline-block mb-2 text-primary-emphasis">
                과학
              </strong>
              <h3 className="mb-0">
                Featured post
              </h3>
              <div className="mb-1 text-body-secondary">
                Nov 12
              </div>
              <p className="card-text mb-auto">
                This is a wider card with supporting text below as a natural lead-in to additional content.
              </p>
              <a
                className="icon-link gap-1 icon-link-hover stretched-link"
                href="#"
              >
                Continue reading
                <svg className="bi">
                  <use xlinkHref="#chevron-right" />
                </svg>
              </a>
            </div>
            <div className="col-auto d-none d-lg-block">
              <svg
                aria-label="Placeholder: Thumbnail"
                className="bd-placeholder-img"
                focusable="false"
                height="250"
                preserveAspectRatio="xMidYMid slice"
                role="img"
                width="200"
                xmlns="http://www.w3.org/2000/svg"
              >
                <title>
                  Placeholder
                </title>
                <rect
                  fill="#55595c"
                  height="100%"
                  width="100%"
                />
                <text
                  dy=".3em"
                  fill="#eceeef"
                  x="50%"
                  y="50%"
                >
                  Thumbnail
                </text>
              </svg>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div className="col p-4 d-flex flex-column position-static">
              <strong className="d-inline-block mb-2 text-primary-emphasis">
                과학
              </strong>
              <h3 className="mb-0">
                Featured post
              </h3>
              <div className="mb-1 text-body-secondary">
                Nov 12
              </div>
              <p className="card-text mb-auto">
                This is a wider card with supporting text below as a natural lead-in to additional content.
              </p>
              <a
                className="icon-link gap-1 icon-link-hover stretched-link"
                href="#"
              >
                Continue reading
                <svg className="bi">
                  <use xlinkHref="#chevron-right" />
                </svg>
              </a>
            </div>
            <div className="col-auto d-none d-lg-block">
              <svg
                aria-label="Placeholder: Thumbnail"
                className="bd-placeholder-img"
                focusable="false"
                height="250"
                preserveAspectRatio="xMidYMid slice"
                role="img"
                width="200"
                xmlns="http://www.w3.org/2000/svg"
              >
                <title>
                  Placeholder
                </title>
                <rect
                  fill="#55595c"
                  height="100%"
                  width="100%"
                />
                <text
                  dy=".3em"
                  fill="#eceeef"
                  x="50%"
                  y="50%"
                >
                  Thumbnail
                </text>
              </svg>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div className="col p-4 d-flex flex-column position-static">
              <strong className="d-inline-block mb-2 text-primary-emphasis">
                세계
              </strong>
              <h3 className="mb-0">
                Featured post
              </h3>
              <div className="mb-1 text-body-secondary">
                Nov 12
              </div>
              <p className="card-text mb-auto">
                This is a wider card with supporting text below as a natural lead-in to additional content.
              </p>
              <a
                className="icon-link gap-1 icon-link-hover stretched-link"
                href="#"
              >
                Continue reading
                <svg className="bi">
                  <use xlinkHref="#chevron-right" />
                </svg>
              </a>
            </div>
            <div className="col-auto d-none d-lg-block">
              <svg
                aria-label="Placeholder: Thumbnail"
                className="bd-placeholder-img"
                focusable="false"
                height="250"
                preserveAspectRatio="xMidYMid slice"
                role="img"
                width="200"
                xmlns="http://www.w3.org/2000/svg"
              >
                <title>
                  Placeholder
                </title>
                <rect
                  fill="#55595c"
                  height="100%"
                  width="100%"
                />
                <text
                  dy=".3em"
                  fill="#eceeef"
                  x="50%"
                  y="50%"
                >
                  Thumbnail
                </text>
              </svg>
            </div>
          </div>
        </div>
      </div>
      
      
    </main>
    <footer className="py-5 text-center text-body-secondary bg-body-tertiary">
      <p>
        Blog template built for{' '}
        <a href="https://getbootstrap.com/">
          Bootstrap
        </a>
        {' '}by{' '}
        <a href="https://twitter.com/mdo">
          @mdo
        </a>
        .
      </p>
      <p className="mb-0">
        <a href="#">
          Back to top
        </a>
      </p>
    </footer>
    <script src="../assets/dist/js/bootstrap.bundle.min.js" />
  
</div>

    );
}

export default Main;