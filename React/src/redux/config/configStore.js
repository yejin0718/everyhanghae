import { configureStore } from "@reduxjs/toolkit";
import login from "../modules/loginSlice";
import board from "../modules/boardReducer";
import comment from "../modules/commentReducer";
import contents from "../modules/addContentsSlice";

const store = configureStore({
  reducer: { login, board, comment, contents },
});

export default store;
