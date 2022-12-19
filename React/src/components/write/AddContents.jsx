import React, { useState, useEffect } from "react";
import classes from "./AddContents.module.css";
import Card from "../elements/Card";
import Button from "../elements/Button";
//redux
import { useDispatch, useSelector } from "react-redux";
import { __addContents } from "../../redux/modules/addContentsSlice";

const AddWrite = () => {
  //옵션 map 배열
  const categoryOption = ["", "BE", "FE", "FREE", "SECRET"];

  const dispatch = useDispatch();
  const { contents } = useSelector((state) => state.contents);
  console.log("확인", contents.contents);

  //Input 값 State
  const [contentsValue, setContentsValue] = useState({
    title: "",
    contents: "",
    category: "",

    isValidTitle: true,
    isValidContnts: true,
  });

  useEffect(() => {
    if (contents.contents === "" || contents.contents === undefined) {
      return;
    }
    console.log(contents.contents);
  }, [contents.contents]);

  const onChangeInputValueHandler = (event) => {
    const { name, value } = event.target;

    ////event.target.value값이 빈 값일 때 loginValue Css 변경
    if (name === "category" && value) {
      setContentsValue({ ...contentsValue, [name]: value });
    } else if (name === "title" && value) {
      setContentsValue({ ...contentsValue, isValidTitle: true, [name]: value });
    } else if (name === "contents" && value) {
      setContentsValue({
        ...contentsValue,
        isValidContnts: true,
        [name]: value,
      });
    } else if (name === "title" && !value) {
      setContentsValue({
        ...contentsValue,
        isValidTitle: false,
        [name]: value,
      });
    } else {
      setContentsValue({
        ...contentsValue,
        isValidContnts: false,
        [name]: value,
      });
    }
  };
  //console.log("contentsInput :", contents);

  const onSubmitInputValueHandler = (event) => {
    event.preventDefault();

    //보낼 데이터
    const newContents = {
      writer: "이현정",
      title: contentsValue.title,
      content: contentsValue.contents,
      category: contentsValue.category,
    };

    if (contentsValue.title === "") {
      setContentsValue({ ...contentsValue, isValidTitle: false });
    } else if (contentsValue.contents === "") {
      setContentsValue({ ...contentsValue, isValidContnts: false });
    } else {
      dispatch(__addContents(newContents));
      alert(contents.contents);

      //POST 후 빈값으로 변경
      setContentsValue({
        title: "",
        contents: "",
        category: "",

        isValidTitle: true,
        isValidContnts: true,
      });
    }
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
            value={contentsValue.category}
            onChange={onChangeInputValueHandler}
          >
            {categoryOption.map((item, index) => (
              <option value={item} key={index}>
                {item}
              </option>
            ))}
            {/* <option value=""></option>
            <option value="BE">백엔드</option>
            <option value="FE">프론트엔드</option>
            <option value="FREE">자유</option>
            <option value="SECRET">비밀 (로그인 유저용)</option> */}
          </select>
          <label htmlFor="title">제목</label>
          <input
            className={`${
              contentsValue.isValidTitle ? classes.input : classes.input_warning
            }`}
            id="title"
            name="title"
            type="text"
            maxLength="30"
            value={contentsValue.title}
            onChange={onChangeInputValueHandler}
          />
          <label htmlFor="contents">내용</label>
          <textarea
            className={`${
              contentsValue.isValidContnts
                ? classes.textarea
                : classes.textarea_warning
            }`}
            id="contents"
            name="contents"
            type="text"
            value={contentsValue.contents}
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
