import React, { useEffect } from "react";
import classes from "./Detail.module.css";
import Button from "../elements/Button";
import { useDispatch, useSelector } from "react-redux";
import { __deleteDetailView } from "../../redux/modules/boardReducer";
import { __getDetailView } from "../../redux/modules/commentReducer";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const DetailBoard = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(__getDetailView(id));
  }, [dispatch, id]);
  const detailView = useSelector((state) => state.comment.data);
  console.log(detailView);
  const onClickBoardUpdateHandler = () => {
    navigate(`update`);
  };

  const onClickBoardDeleteHandler = (id) => {
    dispatch(__deleteDetailView(id));
    navigate("/");
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
          <div className={classes.detalTitle}>제목 : {detailView.title}</div>
          <div className={classes.detailCategory}>
            카테고리 : {detailView.category}
            <div>작성일 : {detailView.createdAt}</div>
          </div>
          <div className={classes.detailContent}>내용</div>
          <div className={classes.detailContentIn}>
            <div>{detailView.content}</div>
          </div>
          <div className={classes.detailLike}>
            <button>💜</button>
          </div>
        </div>
        <hr className={classes.commentHr} />
      </>
    </div>
  );
};

export default DetailBoard;
