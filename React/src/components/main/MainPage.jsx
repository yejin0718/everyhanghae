import React, { useEffect, useState } from "react";
import Button from "../elements/Button";
import classes from "../main/MainPage.module.css";
import Marquee from "react-fast-marquee";
import { useDispatch } from "react-redux";
import { __getMainView } from "../../redux/modules/boardReducer";
import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";

const MainPage = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  useEffect(() => {
    dispatch(__getMainView());
  }, [dispatch]);
  const onClickWriteHandler = () => {
    navigate("/write");
  };
  const mainView = useSelector((state) => state.board.data);

  const [a, setA] = useState(mainView);
  // useEffect(() => {
  //   return () => setA(TOTAL);
  // });
  const TOTAL = mainView;
  const FE = mainView.filter((a) => a.category === "FE");
  const BE = mainView.filter((a) => a.category === "BE");
  const FREE = mainView.filter((a) => a.category === "FREE");
  const SECRET = mainView.filter((a) => a.category === "SCRECT");

  const onClickFEHandler = () => {
    setA(FE);
  };

  const onClickFreeHandler = () => {
    setA(FREE);
  };

  const onClickTotalHandler = () => {
    setA(TOTAL);
  };

  const onClickBEHandler = () => {
    setA(BE);
  };

  const onClickSecretHandler = () => {
    setA(SECRET);
  };

  return (
    <div>
      <div className={classes.mainTop}>
        <div>항해99 기수</div>
        <Button onClick={onClickWriteHandler}>글쓰기</Button>
      </div>
      <>
        <div className={classes.mainMidMarquee}>
          <Marquee gradientWidth={0}>
            에브리 항해에서 항해 개발자들과 이야기를 나누어 보세요!!
          </Marquee>
        </div>
        <div className={classes.mainMiddleBtn}>
          <Button onClick={onClickTotalHandler}>전체</Button>
          <Button onClick={onClickFEHandler}>FE</Button>
          <Button onClick={onClickBEHandler}>BE</Button>
          <Button onClick={onClickFreeHandler}>자유</Button>
          <Button onClick={onClickSecretHandler}>비밀</Button>
        </div>
        <hr></hr>
        {a.map((mainView) => {
          return (
            <Link to={`/detail/${mainView.id}`} key={mainView.id}>
              <div className={classes.mainBoards}>
                <div className={classes.mainWriter}>👤 {mainView.writer}</div>
                <div className={classes.mainBoardView}>{mainView.title}</div>
                <div className={classes.likeAndCommentCount}>
                  💜 {mainView.likeCount}
                </div>
                <div className={classes.likeAndCommentCount}>
                  💬 {mainView.commentCount}
                </div>
              </div>
            </Link>
          );
        })}
      </>
    </div>
  );
};

export default MainPage;
