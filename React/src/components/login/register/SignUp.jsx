import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import classes from "./SignUp.module.css";
import Card from "../../elements/Card";
import Button from "../../elements/Button";

const SignUp = () => {
  const navigate = useNavigate();

  //항해기수 옵션 map 배열
  const generationOption = [
    "--선택해주세요--",
    "1기",
    "2기",
    "3기",
    "4기",
    "5기",
    "6기",
    "7기",
    "8기",
    "9기",
    "10기",
  ];

  //login Input State
  const [loginValue, setLoginValue] = useState({
    //빈값 처리
    email: "",
    pw: "",
    nickname: "",
    generation: "",

    //상태에 따라 alert 대신 css 처리
    isValidEmail: true,
    isValidPW: true,
    isValidNickname: true,
    isValidGeneration: true,
  });

  const onChangeHandlerInput = (event) => {
    const { name, value } = event.target;
    //console.log("event.target :", event.target);

    //event.target.value값이 빈 값일 때 loginValue Css 변경
    if (name === "email" && value) {
      setLoginValue({ ...loginValue, isValidEmail: true, [name]: value });
    } else if (name === "email" && !value) {
      setLoginValue({ ...loginValue, isValidEmail: false, [name]: value });
    } else if (name === "pw" && value) {
      setLoginValue({ ...loginValue, isValidPW: true, [name]: value });
    } else if (name === "pw" && !value) {
      setLoginValue({ ...loginValue, isValidPW: false, [name]: value });
    } else if (name === "nickname" && value) {
      setLoginValue({ ...loginValue, isValidNickname: true, [name]: value });
    } else if (name === "nickname" && !value) {
      setLoginValue({ ...loginValue, isValidNickname: false, [name]: value });
    } else if (name === "generation" && value) {
      setLoginValue({ ...loginValue, isValidGeneration: true, [name]: value });
    } else {
      setLoginValue({ ...loginValue, isValidGeneration: false, [name]: value });
    }
  };
  //console.log("onChange :", loginValue);

  const onSubminLoginValueHandler = (event) => {
    //새로고침 막음
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
      //모든 input이 빈 값이 아닌 경우 POST
      const newLoginValue = {
        email: loginValue.email,
        pw: loginValue.pw,
        nickname: loginValue.nickname,
        generation: loginValue.generation,
      };
      navigate(`/login`);
    }
  };
  //console.log("onSubmit :", loginValue);

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
                value={loginValue.email}
                onChange={onChangeHandlerInput}
              />
            </div>
            <Button className={classes.pwCheckBtn}>중복체크</Button>
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
              onChange={onChangeHandlerInput}
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
              value={loginValue.nickname}
              onChange={onChangeHandlerInput}
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
              onChange={onChangeHandlerInput}
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
