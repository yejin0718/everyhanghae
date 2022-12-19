import React, { useState } from "react";
import classes from "../write/AddContents.module.css";
import Card from "../elements/Card";
import Button from "../elements/Button";
//redux
import { useDispatch } from "react-redux";
import { __patchDetailView } from "../../redux/modules/boardReducer";
import { useNavigate, useParams } from "react-router-dom";

const DetailUpdate = () => {
  //옵션 map 배열
  const categoryOption = ["", "BE", "FE", "FREE", "SECRET"];
  const { id } = useParams();
  const dispatch = useDispatch();
  const navigate = useNavigate();
  //Input 값 State
  const [contents, setContents] = useState({
    id: parseInt(id),
    title: "",
    contents: "",
    category: "",

    isValidTitle: true,
    isValidContnts: true,
  });

  const onChangeInputValueHandler = (event) => {
    const { name, value } = event.target;

    ////event.target.value값이 빈 값일 때 loginValue Css 변경
    if (name === "title" && value) {
      setContents({ ...contents, isValidTitle: true, [name]: value });
    } else if (name === "contents" && value) {
      setContents({ ...contents, isValidContnts: true, [name]: value });
    } else if (name === "title" && !value) {
      setContents({ ...contents, isValidTitle: false, [name]: value });
    } else {
      setContents({ ...contents, isValidContnts: false, [name]: value });
    }
  };

  const onSubmitInputValueHandler = async (event) => {
    event.preventDefault();

    //보낼 데이터
    const newContents = {
      id: contents.id,
      title: contents.title,
      writer: "이현정",
      content: contents.contents,
      category: contents.category,
    };

    if (contents.title === "") {
      setContents({ ...contents, isValidTitle: false });
    } else if (contents.contents === "") {
      setContents({ ...contents, isValidContnts: false });
    } else {
      dispatch(__patchDetailView(newContents));

      navigate(`/detail/${id}`);
      //POST 후 빈값으로 변경
      // setContents({
      //   title: "",
      //   contents: "",
      //   category: "",
      // });
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
            value={contents.category}
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
              contents.isValidTitle ? classes.input : classes.input_warning
            }`}
            id="title"
            name="title"
            type="text"
            maxLength="30"
            value={contents.title}
            onChange={onChangeInputValueHandler}
          />
          <label htmlFor="contents">내용</label>
          <textarea
            className={`${
              contents.isValidContnts
                ? classes.textarea
                : classes.textarea_warning
            }`}
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

export default DetailUpdate;
