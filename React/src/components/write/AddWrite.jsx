import React, { useState } from "react";
import classes from "./AddWrite.module.css";
import Card from "../elements/Card";
import Button from "../elements/Button";
//redux
import { useDispatch } from "react-redux";
import { __addContents } from "../../redux/modules/addWriteSlice";

const AddWrite = () => {
  const dispatch = useDispatch();

  //Input 값 State
  const [contents, setContents] = useState({
    title: "",
    contents: "",
    category: "",
  });

  const onChangeInputValueHandler = (event) => {
    const { name, value } = event.target;
    setContents({ ...contents, [name]: value });
  };
  //console.log("contentsInput :", contents);

  const onSubmitInputValueHandler = async (event) => {
    event.preventDefault();

    //POST할 데이터 (Request)
    const newContents = {
      title: contents.title,
      writer: "이현정",
      content: contents.contents,
      category: contents.category,
    };
    dispatch(__addContents(newContents));
  };

  return (
    <form className={classes.wrap} onSubmit={onSubmitInputValueHandler}>
      <Card className={classes.box}>
        <header className={classes.header}>
          <h3>게시글 작성</h3>
        </header>
        <div className={classes.inputBox}>
          <label htmlFor="category">카테고리</label>
          <select
            name="category"
            className={classes.select}
            value={contents.category}
            onChange={onChangeInputValueHandler}
          >
            <option value="total">전체</option>
            <option value="backend">백엔드</option>
            <option value="frontend">프론트엔드</option>
            <option value="etc">기타</option>
          </select>
          <label htmlFor="title">제목</label>
          <input
            className={classes.input}
            id="title"
            name="title"
            type="text"
            maxLength="30"
            value={contents.title}
            onChange={onChangeInputValueHandler}
          />
          <label htmlFor="contents">내용</label>
          <textarea
            className={classes.textarea}
            id="contents"
            name="contents"
            type="text"
            value={contents.contents}
            onChange={onChangeInputValueHandler}
          />
        </div>
        <footer className={classes.footer}>
          <Button className={classes.addBtn}>등록</Button>
        </footer>
      </Card>
    </form>
  );
};

export default AddWrite;
