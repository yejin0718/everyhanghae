import classes from "./Detail.module.css";
import Button from "../elements/Button";
import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import {
  __getDetailView,
  __deleteComment,
  __postComment,
  __putComment,
} from "../../redux/modules/commentReducer";
import { FiUser } from "react-icons/fi";

const DetailComment = () => {
  const commentView = useSelector((state) => state.comment.data);
  const dispatch = useDispatch();
  const { id } = useParams();
  const nickname = localStorage.getItem("nickname");
  // console.log(commentView.commentList.commentId);
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
  // 댓글 수정 로직

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
        commentView.commentList?.map((comments) => (
          <UpdateChange
            key={comments.commentId}
            commentId={comments.commentId}
            del={onClickCommentDeleteHandler}
            comment={comments.comment}
            nickname={nickname}
            boardId={id}
          ></UpdateChange>
        ))}
    </div>
  );
};

export const UpdateChange = (props) => {
  const [change, setChange] = useState(false);
  const dispatch = useDispatch();
  const [changeComment, setChangeComment] = useState({
    commentId: props.commentId,
    comment: "",
    username: props.nickname,
    boardId: props.boardId,
  });
  const onChangeUpdateComment = (e) => {
    const { name, value } = e.target;
    setChangeComment({ ...changeComment, [name]: value });
  };

  const onClickChangeUpdate = () => {
    dispatch(__putComment(changeComment));
    setChange(false);
  };

  if (change === false) {
    return (
      <div className={classes.commentView} key={props.commentId}>
        <div className={classes.nicknameBox}>
          <FiUser className={classes.mainIcon} />
          {props.nickname}
        </div>
        <div className={classes.commentBox}>{props.comment}</div>
        <Button onClick={() => setChange(true)}>수정</Button>
        <Button onClick={() => props.del(props.commentId)}>삭제</Button>
      </div>
    );
  } else {
    return (
      <div className={classes.commentView} key={props.commentId}>
        <div className={classes.nicknameBox}>
          <FiUser className={classes.mainIcon} />
          {props.nickname}
        </div>
        <input
          type="text"
          name="comment"
          onChange={onChangeUpdateComment}
          className={classes.inputComment}
        />
        <Button
          onClick={() => onClickChangeUpdate(props.commentId)}
          className={classes.changeCom}
        >
          수정완료
        </Button>
      </div>
    );
  }
};

export default DetailComment;
