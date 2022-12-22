import React, { useState, useEffect } from "react";
import classes from "../write/AddContents.module.css";
import Card from "../elements/Card";
import Button from "../elements/Button";
//redux
import { useDispatch, useSelector } from "react-redux";
import { __patchDetailView } from "../../redux/modules/boardReducer";
import { __getDetailView } from "../../redux/modules/commentReducer";
import AlertModal from "./DetailUpdateModal";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const DetailUpdate = () => {
  const categoryOption = ["", "BE", "FE", "FREE", "SECRET"];
  const dispatch = useDispatch();
  const isSuccess = useSelector((state) => state.board.isSuccess);
  const { id } = useParams();
  const navigate = useNavigate();
  //모달창 State
  const [modal, setModal] = useState(false);
  useEffect(() => {
    dispatch(__getDetailView(id));
  }, [dispatch, id]);

  const updateView = useSelector((state) => state.comment.data);
  //Input value State
  const [contentsValue, setContentsValue] = useState({
    title: updateView.title,
    contents: updateView.content,
    category: updateView.category,

    isValidTitle: true,
    isValidContnts: true,
  });

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

  const onSubmitInputValueHandler = (event) => {
    event.preventDefault();
    //보낼 데이터
    const newContents = {
      id: parseInt(id),
      title: contentsValue.title,
      content: contentsValue.contents,
      category: contentsValue.category,
    };
    if (contentsValue.title === "") {
      setContentsValue({ ...contentsValue, isValidTitle: false });
    } else if (contentsValue.contents === "") {
      setContentsValue({ ...contentsValue, isValidContnts: false });
    } else {
      dispatch(__patchDetailView(newContents));
      setModal(true);
      //POST 후 빈값으로 변경
    }
  };
  const alertHandler = () => {
    setModal(false);
    navigate(`/detail/${id}`);
  };

  return (
    <div>
      {isSuccess && modal ? (
        <AlertModal onAlert={alertHandler} />
      ) : (
        <form className={classes.wrap} onSubmit={onSubmitInputValueHandler}>
          <Card className={classes.box}>
            <header className={classes.header}>
              <h3>게시글 수정</h3>
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
      )}
    </div>
  );
};

export default DetailUpdate;
