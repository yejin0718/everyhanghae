import { createAsyncThunk } from "@reduxjs/toolkit";
import { createSlice } from "@reduxjs/toolkit";
// import { instance } from "./instance/instance";
import axios from "axios";

const initialState = {
  data: [],
};

// 상세페이지 조회
export const __getDetailView = createAsyncThunk(
  "DetailView/getboard",
  async (payload, thunkAPI) => {
    try {
      // const data = await instance.get(`boards/${payload}`);
      // return thunkAPI.fulfillWithValue(data);
      const { data } = await axios.get(`http://localhost:3001/data/${payload}`);
      return thunkAPI.fulfillWithValue(data);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

export const commentReducer = createSlice({
  name: "comment",
  initialState,
  reducers: {},
  extraReducers: {
    // 상세페이지 출력
    [__getDetailView.pending]: (_state) => {},
    [__getDetailView.fulfilled]: (state, action) => {
      state.data = action.payload;
      console.log(state.data);
    },
    [__getDetailView.rejected]: (_state, _action) => {
      alert("연결실패");
    },
  },
});

export default commentReducer.reducer;
