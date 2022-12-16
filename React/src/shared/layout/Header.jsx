import React from "react";
import classes from "./Header.module.css";
//component
import Button from "../../components/elements/Button";

const Header = () => {
  return (
    <div className={classes.header}>
      <h2>애브리항해</h2>
      <Button>로그인</Button>
    </div>
  );
};

export default Header;
