import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { sign_in } from "../../../core/api/LoginAPI";
import google from "../../../img/icon_google.png";
import kakao from "../../../img/icon_kakao.png";
import githup from "../../../img/icon_github.png";
import Card from "../../elements/Card";
import classes from "./SignIn.module.css";

const SignIn = () => {
  const navigate = useNavigate();
  const [loginValue, setLoginValue] = useState({
    email: "",
    pw: "",

    isValidEmail: true,
    isValidPW: true,
  });

  const onChangeInputHandler = (event) => {
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

  const onSubmitLoginValueHandler = (event) => {
    event.preventDefault();
    if (loginValue.email === "") {
      setLoginValue({ ...loginValue, isValidEmail: false });
    } else if (loginValue.pw === "") {
      setLoginValue({ ...loginValue, isValidPW: false });
    } else {
      const newLoginValue = {
        email: loginValue.email,
        password: loginValue.pw,
      };
      sign_in(newLoginValue).then((res) => {
        //console.log("res", res);
        alert(res.data.msg);
        localStorage.setItem("id", res.headers.authorization);
        localStorage.setItem("nickname", res.data.data.nickname);
        localStorage.setItem("generation", res.data.data.generation);
        navigate("/");
      });
    }
  };
  //console.log("onSubmit :", loginValue);

  return (
    <Card className={classes.wrap}>
      <section className={classes.container}>
        <h1>에브리항해</h1>

        <form onSubmit={onSubmitLoginValueHandler}>
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
              onChange={onChangeInputHandler}
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
              onChange={onChangeInputHandler}
            />
          </div>

          <div className={classes.btnLogin}>
            <button>로그인</button>
          </div>

          <div className={classes.snsLogin}>
            <p>SNS 간편 로그인</p>
            <button type="button">
              <img src={google} className={classes.google} />
            </button>
            <button type="button">
              <img src={kakao} className={classes.kakao} />
            </button>
            <button type="button">
              <img src={githup} className={classes.githup} />
            </button>
          </div>
        </form>

        <div type="button" className={classes.caption}>
          <Link className={classes.link} to="/register">
            아직 회원이 아니신가요?
          </Link>
        </div>
      </section>
    </Card>
  );
};

export default SignIn;
