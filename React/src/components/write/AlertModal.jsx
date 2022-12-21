import React from "react";
import classes from "./AlertModal.module.css";
import Card from "../elements/Card";
import Button from "../elements/Button";

const AlertModal = ({ onAlert, text }) => {
  console.log(onAlert);
  console.log(text);
  return (
    <div className={classes.backdrop} onClick={onAlert}>
      <Card className={classes.modal}>
        <header className={classes.header}>
          <h3>게시글 등록</h3>
        </header>
        <div className={classes.content}>
          <p>{text}</p>
        </div>
        <footer className={classes.actions}>
          <Button onClick={onAlert}>Okay</Button>
        </footer>
      </Card>
    </div>
  );
};

export default AlertModal;
