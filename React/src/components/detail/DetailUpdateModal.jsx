import React from "react";
import classes from "../write/AlertModal.module.css";
import Card from "../elements/Card";
import Button from "../elements/Button";

const AlertModal = (props) => {
  return (
    <div className={classes.backdrop} onClick={props.onAlert}>
      <Card className={classes.modal}>
        <header className={classes.header}>
          <h3>게시글 수정 성공</h3>
        </header>
        <div className={classes.content}>
          <p>게시글이 수정 되었습니다. 🎉</p>
        </div>
        <footer className={classes.actions}>
          <Button onClick={props.onAlert}>Okay</Button>
        </footer>
      </Card>
    </div>
  );
};

export default AlertModal;
