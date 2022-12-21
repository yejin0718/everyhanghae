import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Card from "../elements/Card";
import Button from "../elements/Button";
import classes from "./AddContents.module.css";
//redux
import { useDispatch, useSelector } from "react-redux";
import { __addContents } from "../../redux/modules/addContentsSlice";
import AlertModal from "./AlertModal";

const AddContents = () => {
  const categoryOption = ["", "BE", "FE", "FREE", "SECRET"];
  const navigate = useNavigate();

  const dispatch = useDispatch();
  const { isSuccess } = useSelector((state) => state.contents);
  //console.log("isSuccess :", isSuccess);

  const [modal, setModal] = useState(false);
  const [contentsValue, setContentsValue] = useState({
    title: "",
    contents: "",
    category: "",

    isValidTitle: true,
    isValidContents: true,
  });
  //console.log("contentsValue :", contentsValue);

  //상태값과 데이터를 읽어오는 타이밍이 다를 수 있음 -> useEffect를 통해서 재렌더링함
  // useEffect(() => {
  //   if (contents.contents === "" || contents.contents === undefined) {
  //     return;
  //   }
  //   console.log(contents.contents);
  // }, [contents.contents]);

  const onChangeInputValueHandler = (event) => {
    const { name, value } = event.target;

    ////event.target.value값이 빈 값일 때 loginValue Css 변경
    const isValidList = {
      title: "isValidTitle",
      contents: "isValidContents",
    };
    setContentsValue({
      ...contentsValue,
      [isValidList[name]]: value ? true : false,
      [name]: value,
    });

    //   if (name === "category" && value) {
    //     setContentsValue({ ...contentsValue, [name]: value });
    //   } else if (name === "title" && value) {
    //     setContentsValue({ ...contentsValue, isValidTitle: true, [name]: value });
    //   } else if (name === "contents" && value) {
    //     setContentsValue({
    //       ...contentsValue,
    //       isValidContents: true,
    //       [name]: value,
    //     });
    //   } else if (name === "title" && !value) {
    //     setContentsValue({
    //       ...contentsValue,
    //       isValidTitle: false,
    //       [name]: value,
    //     });
    //   } else {
    //     setContentsValue({
    //       ...contentsValue,
    //       isValidContents: false,
    //       [name]: value,
    //     });
    //   }
  };

  const onSubmitInputValueHandler = (event) => {
    event.preventDefault();
    const newContents = {
      title: contentsValue.title,
      content: contentsValue.contents,
      category: contentsValue.category,
    };
    if (contentsValue.title === "") {
      setContentsValue({ ...contentsValue, isValidTitle: false });
    } else if (contentsValue.contents === "") {
      setContentsValue({ ...contentsValue, isValidContents: false });
    } else {
      dispatch(__addContents(newContents));
      setModal(true);
      //POST 후 빈값으로 변경
      setContentsValue({
        title: "",
        contents: "",
        category: "",
        isValidTitle: true,
        isValidContents: true,
      });
    }
  };
  const alertHandler = () => {
    setModal(false);
    isSuccess ? navigate("/") : navigate("/login");
  };

  return (
    <div>
      {isSuccess && modal ? (
        <AlertModal
          onAlert={alertHandler}
          text={"게시글이 등록되었습니다. 🎉"}
        />
      ) : isSuccess === false && modal ? (
        <AlertModal onAlert={alertHandler} text={"로그인을 해주세요."} />
      ) : (
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
              </select>
              <label htmlFor="title">제목</label>
              <input
                className={`${
                  contentsValue.isValidTitle
                    ? classes.input
                    : classes.input_warning
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
                  contentsValue.isValidContents
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
      )}
    </div>
  );
};

export default AddContents;
