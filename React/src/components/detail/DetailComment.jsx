import classes from "./Detail.module.css";
import Button from "../elements/Button";
import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import {
  __getDetailView,
  __deleteComment,
  __postComment,
} from "../../redux/modules/commentReducer";

const DetailComment = () => {
  const commentView = useSelector((state) => state.comment.data);
  const dispatch = useDispatch();
  const { id } = useParams();
  const nickname = localStorage.getItem("nickname");

  const [writeComment, setWriteComment] = useState({
    boardId: parseInt(id),
    comment: "",
    username: nickname,
  });

  useEffect(() => {
    dispatch(__getDetailView(id));
  }, [dispatch, id]);

  const onClickCommentDeleteHandler = (id) => {
    // console.log(id);
    dispatch(__deleteComment([id, commentView.id]));
  };

  const onClickCommentWriteHandler = () => {
    if (writeComment.comment === "") {
      alert("댓글을 입력해 주세요!");
    } else {
      dispatch(__postComment(writeComment));
      setWriteComment({
        ...writeComment,
        comment: "",
      });
    }
  };

  const onChangeWriteComment = (e) => {
    const { name, value } = e.target;
    setWriteComment({ ...writeComment, [name]: value });
  };
  const { comment } = writeComment;

  return (
    <div>
      <div className={classes.commentInputBtn}>
        <input
          onChange={onChangeWriteComment}
          type="text"
          name="comment"
          value={comment}
        ></input>
        <Button onClick={() => onClickCommentWriteHandler(writeComment)}>
          댓글 작성
        </Button>
      </div>
      {commentView.commentList &&
        commentView.commentList?.map((comments, index) => (
          <div className={classes.commentView} key={comments.commentId}>
            <div>{comments.comment}</div>
            <Button>수정</Button>
            <Button
              onClick={() => onClickCommentDeleteHandler(comments.commentId)}
            >
              삭제
            </Button>
          </div>
        ))}
    </div>
  );
};

export default DetailComment;
