import { configureStore } from "@reduxjs/toolkit";
import board from "../modules/boardReducer";
import comment from "../modules/commentReducer";

const store = configureStore({
  reducer: { board, comment },
});

export default store;
