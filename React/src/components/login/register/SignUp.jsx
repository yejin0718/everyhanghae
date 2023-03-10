import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { sign_up, duplicate_check } from "../../../core/api/LoginAPI";
import Card from "../../elements/Card";
import classes from "./SignUp.module.css";

const SignUp = () => {
  const navigate = useNavigate();
  //옵션 태그 배열로 만들기
  let generationOption = [""];
  for (let i = 1; i <= 10; i++) {
    generationOption.push(i + "기");
  }

  //상태값
  const [duplicateCheck, setDuplicateCheck] = useState(false);
  const [loginValue, setLoginValue] = useState({
    //빈값
    email: "",
    pw: "",
    nickname: "",
    generation: "",

    //빈값일 때 상태에 따라 css 처리
    isValidEmail: true,
    isValidPW: true,
    isValidNickname: true,
    isValidGeneration: true,
  });

  //이벤트 핸들러 함수
  const onClickDuplicateCheckHandler = () => {
    const postEmail = {
      email: loginValue.email,
    };
    if (loginValue.email !== "") {
      duplicate_check(postEmail).then((res) => {
        alert(res.data.msg);
      });
      setDuplicateCheck(true);
    }
  };

  const onChangeInputHandler = (event) => {
    const { name, value } = event.target;
    //console.log("event.target :", event.target);

    //event.target.value값이 빈 값일 때 loginValue Css 변경
    const isValidList = {
      email: "isValidEmail",
      pw: "isValidPW",
      nickname: "isValidNickname",
      generation: "isValidGeneration",
    };
    setLoginValue({
      ...loginValue,
      [isValidList[name]]: value ? true : false,
      [name]: value,
    });

    // if (name === "email" && value) {
    //   setLoginValue({ ...loginValue, isValidEmail: true, [name]: value });
    // } else if (name === "email" && !value) {
    //   setLoginValue({ ...loginValue, isValidEmail: false, [name]: value });
    // } else if (name === "pw" && value) {
    //   setLoginValue({ ...loginValue, isValidPW: true, [name]: value });
    // } else if (name === "pw" && !value) {
    //   setLoginValue({ ...loginValue, isValidPW: false, [name]: value });
    // } else if (name === "nickname" && value) {
    //   setLoginValue({ ...loginValue, isValidNickname: true, [name]: value });
    // } else if (name === "nickname" && !value) {
    //   setLoginValue({ ...loginValue, isValidNickname: false, [name]: value });
    // } else if (name === "generation" && value) {
    //   setLoginValue({ ...loginValue, isValidGeneration: true, [name]: value });
    // } else {
    //   setLoginValue({ ...loginValue, isValidGeneration: false, [name]: value });
    // }
  };
  //console.log("onChange :", loginValue);

  const onSubminLoginValueHandler = (event) => {
    event.preventDefault();
    //빈 값 예외처리
    if (loginValue.email === "") {
      setLoginValue({ ...loginValue, isValidEmail: false });
    } else if (loginValue.pw === "") {
      setLoginValue({ ...loginValue, isValidPW: false });
    } else if (loginValue.nickname === "") {
      setLoginValue({ ...loginValue, isValidNickname: false });
    } else if (loginValue.generation === "") {
      setLoginValue({ ...loginValue, isValidGeneration: false });
    } else {
      //모든 input이 빈 값이 아니고 duplicateCheck가 true인 경우
      if (duplicateCheck) {
        const newLoginValue = {
          email: loginValue.email,
          password: loginValue.pw,
          nickname: loginValue.nickname,
          generation: parseInt(loginValue.generation),
        };
        sign_up(newLoginValue).then((res) => {
          setDuplicateCheck(false);
          alert(res.data.msg);
          navigate(`/login`);
        });
      } else {
        alert("이메일 중복 체크해주세요.");
      }
    }
  };
  //console.log("onSubmit 데이터:", loginValue);

  return (
    <Card className={classes.wrap}>
      <section className={classes.container}>
        <h1>회원가입</h1>
        <form onSubmit={onSubminLoginValueHandler}>
          <div className={classes.box}>
            <div className={classes.inputArea}>
              <label
                htmlFor="email"
                className={`${
                  loginValue.isValidEmail ? classes.label : classes.warning
                }`}
              >
                이메일
              </label>
              <input
                className={classes.input}
                id="email"
                name="email"
                type="text"
                autoComplete="off"
                value={loginValue.email}
                onChange={onChangeInputHandler}
              />
            </div>
            <button
              type="button"
              className={classes.idCheckBtn}
              onClick={onClickDuplicateCheckHandler}
            >
              중복체크
            </button>
          </div>
          <div className={classes.inputArea}>
            <label
              htmlFor="pw"
              className={`${
                loginValue.isValidPW ? classes.label : classes.warning
              }`}
            >
              패스워드
            </label>
            <input
              className={classes.input}
              id="pw"
              name="pw"
              type="password"
              value={loginValue.pw}
              onChange={onChangeInputHandler}
            />
          </div>
          <div className={classes.inputArea}>
            <label
              htmlFor="nickname"
              className={`${
                loginValue.isValidNickname ? classes.label : classes.warning
              }`}
            >
              닉네임
            </label>
            <input
              className={classes.input}
              id="nickname"
              name="nickname"
              type="text"
              autoComplete="off"
              value={loginValue.nickname}
              onChange={onChangeInputHandler}
            />
          </div>
          <div className={classes.inputArea}>
            <label
              htmlFor="generation"
              className={`${
                loginValue.isValidGeneration ? classes.label : classes.warning
              }`}
            >
              항해 기수
            </label>
            <select
              className={classes.input}
              id="generation"
              name="generation"
              onChange={onChangeInputHandler}
            >
              {generationOption.map((item, index) => (
                <option key={index} value={item}>
                  {item}
                </option>
              ))}
            </select>
          </div>

          <div className={classes.btnArea}>
            <Link to="/login" className={classes.cancleBtn}>
              취소
            </Link>
            <button className={classes.button}>가입 완료</button>
          </div>
        </form>

        <div className={classes.caption}>
          <p className={classes.info}>
            가입하면 <span>이용약관</span>과 <span>개인정보취급방침</span>에
            동의한 것으로 간주됩니다.
          </p>
        </div>
      </section>
    </Card>
  );
};

export default SignUp;
