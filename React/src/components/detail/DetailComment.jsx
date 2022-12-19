import classes from "./Detail.module.css";
import Button from "../elements/Button";
import React from "react";

const DetailComment = () => {
  return (
    <div>
      <div className={classes.commentInputBtn}>
        <input></input>
        <Button>댓글 작성</Button>
      </div>
      {/* {detailView.commentList.map((comments) => (
          <div className={classes.commentView}>
            <div>{comments.content}</div>
            <Button>수정</Button>
            <Button>삭제</Button>
          </div>
        ))} */}
    </div>
  );
};

export default DetailComment;
