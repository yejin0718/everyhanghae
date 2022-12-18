import React, { useState } from "react";
import { Link } from "react-router-dom";
import classes from "./SignIn.module.css";
import Card from "../../elements/Card";
//redux
import { useDispatch } from "react-redux";

const SignIn = () => {
  const dispatch = useDispatch();
  //login Input State
  const [loginValue, setLoginValue] = useState({
    email: "",
    pw: "",

    isValidEmail: true,
    isValidPW: true,
  });

  const onChangeHandlerInput = (event) => {
    const { name, value } = event.target;

    //event.target.value값이 빈 값일 때 loginValue Css 변경
    if (name === "email" && value) {
      setLoginValue({ ...loginValue, isValidEmail: true, [name]: value });
    } else if (name === "pw" && value) {
      setLoginValue({ ...loginValue, isValidPW: true, [name]: value });
    } else if (name === "email" && !value) {
      setLoginValue({ ...loginValue, isValidEmail: false, [name]: value });
    } else {
      setLoginValue({ ...loginValue, isValidPW: false, [name]: value });
    }
  };
  //console.log("onChange :", loginValue);

  const onSubminLoginValueHandler = (event) => {
    //새로고침 막음
    event.preventDefault();
    if (loginValue.email === "") {
      setLoginValue({ ...loginValue, isValidEmail: false });
    } else if (loginValue.pw === "") {
      setLoginValue({ ...loginValue, isValidPW: false });
    } else {
      const newLoginValue = {
        id: loginValue.email,
        pw: loginValue.pw,
      };
      //dispatch()
    }
  };
  //console.log("onSubmit :", loginValue);

  return (
    <Card className={classes.wrap}>
      <section className={classes.container}>
        <h1>에브리항해</h1>

        <form onSubmit={onSubminLoginValueHandler}>
          <div className={classes.inputArea}>
            <label
              htmlFor="email"
              className={`${
                loginValue.isValidEmail ? classes.label : classes.warning
              }`}
            >
              Email
            </label>
            <input
              id="email"
              name="email"
              type="text"
              value={loginValue.email}
              onChange={onChangeHandlerInput}
            />
          </div>
          <div className={classes.inputArea}>
            <label
              htmlFor="pw"
              className={`${
                loginValue.isValidPW ? classes.label : classes.warning
              }`}
            >
              Password
            </label>
            <input
              id="pw"
              name="pw"
              type="password"
              value={loginValue.pw}
              onChange={onChangeHandlerInput}
            />
          </div>

          <div className={classes.btnArea}>
            <Link to="/">
              <button>로그인</button>
            </Link>
          </div>
        </form>

        <div className={classes.caption}>
          <Link className={classes.link} to="/register">
            아직 회원이 아니신가요?
          </Link>
        </div>
      </section>
    </Card>
  );
};

export default SignIn;
