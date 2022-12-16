import React from "react";
import Button from "../components/elements/Button";
import classes from "./Main.module.css";
import Marquee from "react-fast-marquee";

const Main = () => {
  return (
    <div>
      <div className={classes.mainTop}>
        <div className={classes.mainTopNumber}>항해99 기수</div>
        <Button className={classes.mainTopButton}>글쓰기</Button>
      </div>
      <>
        <div className={classes.mainMidMarquee}>
          <Marquee gradientWidth={0}>
            에브리 항해에서 항해 개발자들과 이야기를 나누어 보세요!!
          </Marquee>
        </div>
        <div className={classes.mainMiddleBtn}>
          <Button>전체</Button>
          <Button>FE</Button>
          <Button>BE</Button>
        </div>
        <hr></hr>
        <div className={classes.mainBoards}>
          <></>
          <div className={classes.mainBoardView}>글</div>
          <div className={classes.likeAndCommentCount}>💜 111</div>
          <div className={classes.likeAndCommentCount}>💬 111</div>
        </div>
        <div className={classes.mainBoards}>
          <></>
          <div className={classes.mainBoardView}>글</div>
          <div className={classes.likeAndCommentCount}>💜 111</div>
          <div className={classes.likeAndCommentCount}>💬 111</div>
        </div>
        <div className={classes.mainBoards}>
          <></>
          <div className={classes.mainBoardView}>글</div>
          <div className={classes.likeAndCommentCount}>💜 111</div>
          <div className={classes.likeAndCommentCount}>💬 111</div>
        </div>
        <div className={classes.mainBoards}>
          <></>
          <div className={classes.mainBoardView}>글</div>
          <div className={classes.likeAndCommentCount}>💜 111</div>
          <div className={classes.likeAndCommentCount}>💬 111</div>
        </div>
        <div className={classes.mainBoards}>
          <></>
          <div className={classes.mainBoardView}>글</div>
          <div className={classes.likeAndCommentCount}>💜 111</div>
          <div className={classes.likeAndCommentCount}>💬 111</div>
        </div>
        <div className={classes.mainBoards}>
          <></>
          <div className={classes.mainBoardView}>글</div>
          <div className={classes.likeAndCommentCount}>💜 111</div>
          <div className={classes.likeAndCommentCount}>💬 111</div>
        </div>
        <div className={classes.mainBoards}>
          <></>
          <div className={classes.mainBoardView}>글</div>
          <div className={classes.likeAndCommentCount}>💜 111</div>
          <div className={classes.likeAndCommentCount}>💬 111</div>
        </div>
      </>
    </div>
  );
};

export default Main;
