import React from "react";
import classes from "./Detail.module.css";
import Button from "../components/elements/Button";

const Detail = () => {
  return (
    <div>
      <div className={classes.detailTop}>
        <div className={classes.detailTitleAndButton}>
          <div>작성자 : </div>
          <Button>수정</Button>
          <Button>삭제</Button>
        </div>
        <div className={classes.detailContent}>내용</div>
        <div className={classes.detailContentIn}>
          <div>내용입니다!!</div>
        </div>
        <div className={classes.detailLike}>
          <button>💜</button>
        </div>
      </div>
      <hr className={classes.commentHr} />
      <div className={classes.commentInputBtn}>
        <input></input>
        <Button>댓글 작성</Button>
      </div>
      <div className={classes.commentView}>
        <div>1122222222333323132132131312</div>
        <Button>수정</Button>
        <Button>삭제</Button>
      </div>
      <></>
      <div className={classes.commentView}>
        <div>1122222222333323132132131312</div>
        <Button>수정</Button>
        <Button>삭제</Button>
      </div>
      <div className={classes.commentView}>
        <div>1122222222333323132132131312</div>
        <Button>수정</Button>
        <Button>삭제</Button>
      </div>
      <div className={classes.commentView}>
        <div>1122222222333323132132131312</div>
        <Button>수정</Button>
        <Button>삭제</Button>
      </div>
      <div className={classes.commentView}>
        <div>1122222222333323132132131312</div>
        <Button>수정</Button>
        <Button>삭제</Button>
      </div>
      <div className={classes.commentView}>
        <div>1122222222333323132132131312</div>
        <Button>수정</Button>
        <Button>삭제</Button>
      </div>
      <div className={classes.commentView}>
        <div>1122222222333323132132131312</div>
        <Button>수정</Button>
        <Button>삭제</Button>
      </div>
      <div className={classes.commentView}>
        <div>1122222222333323132132131312</div>
        <Button>수정</Button>
        <Button>삭제</Button>
      </div>
      <div className={classes.commentView}>
        <div>1122222222333323132132131312</div>
        <Button>수정</Button>
        <Button>삭제</Button>
      </div>
      <div className={classes.commentView}>
        <div>1122222222333323132132131312</div>
        <Button>수정</Button>
        <Button>삭제</Button>
      </div>
    </div>
  );
};

export default Detail;
