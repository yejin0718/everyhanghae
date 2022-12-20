import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import classes from "./Header.module.css";
//component
import Button from "../../components/elements/Button";

const Header = () => {
  const [token, setToken] = useState(localStorage.getItem("id"));

  const onClickLogoutButtonHandler = () => {
    setToken(localStorage.removeItem("id"));
  };

  useEffect(() => {}, [token]);

  return (
    <div className={classes.header}>
      <Link to={"/"} className={classes.headerLink}>
        <h2>에브리항해</h2>
      </Link>
      {token ? (
        <Link to={"/"}>
          <Button onClick={onClickLogoutButtonHandler}>로그아웃</Button>
        </Link>
      ) : (
        <Link to={"/login"}>
          <Button>로그인</Button>
        </Link>
      )}
    </div>
  );
};

export default Header;
