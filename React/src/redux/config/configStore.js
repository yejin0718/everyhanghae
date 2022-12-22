import { configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
// import login from "../modules/loginSlice";
import board from "../modules/boardReducer";
import comment from "../modules/commentReducer";
import contents from "../modules/addContentsSlice";

const store = configureStore({
  reducer: { board, comment, contents },
  middleware: getDefaultMiddleware({
    serializableCheck: false,
  }),
});

export default store;
