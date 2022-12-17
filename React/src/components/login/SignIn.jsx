import React, { useState } from "react";
import { Link } from "react-router-dom";
import classes from "./SignIn.module.css";
import Card from "../elements/Card";

const SignIn = () => {
  //login Input State
  const [loginValue, setLoginValue] = useState({
    id: "",
    pw: "",
    isValidID: true,
    isValidPW: true,
  });

  const onChangeHandlerInput = (event) => {
    const { name, value } = event.target;

    //event.target.value값이 빈 값일 때 isVailid Css 변경
    if (name === "id" && value) {
      setLoginValue({ ...loginValue, isValidID: true, [name]: value });
    } else if (name === "pw" && value) {
      setLoginValue({ ...loginValue, isValidID: true, [name]: value });
    } else if (name === "id" && !value) {
      setLoginValue({ ...loginValue, isValidPW: false, [name]: value });
    } else {
      setLoginValue({ ...loginValue, isValidPW: false, [name]: value });
    }
  };
  console.log("onChange :", loginValue);

  const onSubminLoginValueHandler = (event) => {
    //새로고침 막음
    event.preventDefault();
    if (loginValue.id === "") {
      setLoginValue({ ...loginValue, isValidID: false });
    } else if (loginValue.pw === "") {
      setLoginValue({ ...loginValue, isValidPW: false });
    } else {
      const newLoginValue = {
        id: loginValue.id,
        pw: loginValue.pw,
      };
    }
  };
  console.log("onSubmit :", loginValue);

  return (
    <Card className={classes.wrap}>
      <section className={classes.container}>
        <h1>애브리항해</h1>

        <form onSubmit={onSubminLoginValueHandler}>
          <div className={classes.inputArea}>
            <label
              htmlFor="id"
              className={`${
                loginValue.isValidID ? classes.label : classes.warning
              }`}
            >
              ID
            </label>
            <input
              id="id"
              name="id"
              type="text"
              value={loginValue.id}
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
              PASSWORD
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
            <button>로그인</button>
          </div>
        </form>

        <div className={classes.caption}>
          <Link className={classes.link} to="/signup">
            아직 회원이 아니신가요?
          </Link>
        </div>
      </section>
    </Card>
  );
};

export default SignIn;
