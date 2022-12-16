import React from "react";
import Card from "../elements/Card";
import classes from "./AddWrite.module.css";
import Button from "../elements/Button";

const AddWrite = () => {
  return (
    <form className={classes.wrap}>
      <Card className={classes.box}>
        <header className={classes.header}>
          <h3>게시글 작성</h3>
        </header>
        <div className={classes.inputBox}>
          <label htmlFor="category">카테고리</label>
          <select name="category" className={classes.select}>
            <option value="total">전체</option>
            <option value="backend">백엔드</option>
            <option value="frontend">프로트엔드</option>
            <option value="etc">기타</option>
          </select>
          <label htmlFor="title">제목</label>
          <input
            className={classes.input}
            id="title"
            name="title"
            type="text"
          />
          <label htmlFor="content">내용</label>
          <textarea
            className={classes.textarea}
            id="content"
            name="content"
            type="text"
          />

          {/* <label htmlFor="writer">작성자</label>
          <input
            className={classes.input}
            id="writer"
            name="writer"
            type="text"
          /> */}
        </div>
        <footer className={classes.footer}>
          <Button className={classes.addBtn}>등록</Button>
        </footer>
      </Card>
    </form>
  );
};

export default AddWrite;
