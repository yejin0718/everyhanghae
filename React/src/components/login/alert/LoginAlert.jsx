import React from "react";
import classes from "./LoginAlert.module.css";
import Card from "../../elements/Card";
import Button from "../../elements/Button";

const LoginAlert = (props) => {
  return (
    <div className={classes.backdrop} onClick={props.onAlert}>
      <Card className={classes.modal}>
        <div className={classes.content}>
          <p>에러 메세지 띄우기</p>
        </div>
        <footer className={classes.actions}>
          <Button onClick={props.onAlert}>Okay</Button>
        </footer>
      </Card>
    </div>
  );
};

export default LoginAlert;
