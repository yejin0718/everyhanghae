import React from "react";
import classes from "./Header.module.css";
//component
import Button from "../../components/elements/Button";
import { Link } from "react-router-dom";

const Header = () => {
  return (
    <div className={classes.header}>
      <Link to={"/"} className={classes.headerLink}>
        <h2>에브리항해</h2>
      </Link>

      <Button>로그인</Button>
    </div>
  );
};

export default Header;
