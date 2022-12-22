import React, { useEffect, useState } from "react";
import classes from "./Detail.module.css";
import Button from "../elements/Button";
import { useDispatch, useSelector } from "react-redux";
import {
  __deleteDetailView,
  __getMainView,
} from "../../redux/modules/boardReducer";
import {
  __getDetailView,
  __postLike,
} from "../../redux/modules/commentReducer";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { AiFillHeart } from "react-icons/ai";

const DetailBoard = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(__getDetailView(id));
  }, [dispatch, id]);

  const detailView = useSelector((state) => state.comment.data);
  const likeView = useSelector((state) => state.comment.data.likeCount);
  // console.log(likeView);
  const onClickBoardUpdateHandler = () => {
    navigate(`update`);
  };

  // console.log(like);

  const onClickBoardDeleteHandler = async (id) => {
    dispatch(__deleteDetailView(id));
    dispatch(__getMainView());
    navigate("/");
  };

  // 좋아요 기능

  const [like, setLike] = useState(likeView);
  const onClickLikeHandler = async (id) => {
    dispatch(__postLike(id));
    if (like === 1) {
      return setLike(like - 1);
    }
    setLike(like + 1);
  };
  return (
    <div>
      <>
        <div className={classes.detailTop}>
          <div className={classes.detailTitleAndButton}>
            <div>작성자 : {detailView.writer}</div>
            <Button onClick={() => onClickBoardUpdateHandler(detailView.id)}>
              수정
            </Button>
            <Button onClick={() => onClickBoardDeleteHandler(detailView.id)}>
              삭제
            </Button>
          </div>
          <div className={classes.detailCategory}>
            <div>카테고리 : {detailView.category}</div>
            <div>작성일 : {detailView.createdAt}</div>
          </div>
          <div className={classes.likeBox}>
            <div className={classes.detalTitle}>제목 : {detailView.title}</div>
            <button onClick={() => onClickLikeHandler(detailView.id)}>
              <AiFillHeart color="red" />
            </button>
            <div className={classes.likeCountNum}>{detailView.likeCount}</div>
          </div>
          <div className={classes.detailContentIn}>
            <div>{detailView.content}</div>
          </div>
        </div>
        <hr className={classes.commentHr} />
      </>
    </div>
  );
};

export default DetailBoard;
