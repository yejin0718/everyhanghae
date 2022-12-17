import { configureStore } from "@reduxjs/toolkit";
import board from "../modules/boardReducer";
import comment from "../modules/commentReducer";
import contents from "../modules/addWriteSlice";

const store = configureStore({
  reducer: { board, comment, contents },
});

export default store;
