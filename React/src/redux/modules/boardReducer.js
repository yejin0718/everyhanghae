import { createAsyncThunk } from "@reduxjs/toolkit";
import { createSlice } from "@reduxjs/toolkit";
import { instance } from "../../core/api/instance";

const initialState = {
  data: [],
};

// 메인 페이지 조회(완료!)
export const __getMainView = createAsyncThunk(
  "MainView/getboard",
  async (_payload, thunkAPI) => {
    try {
      const data = await instance.get(`boards`);
      return thunkAPI.fulfillWithValue(data.data.data);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

// 상세페이지 수정
export const __patchDetailView = createAsyncThunk(
  "DetailView/patchboard",
  async (payload, thunkAPI) => {
    try {
      const data = await instance.patch(`boards/${payload.id}`, {
        title: payload.title,
        content: payload.content,
        category: payload.category,
      });
      return thunkAPI.fulfillWithValue(data);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);
// 상세페이지 삭제
export const __deleteDetailView = createAsyncThunk(
  "DetailView/deleteboard",
  async (payload, thunkAPI) => {
    try {
      await instance.delete(`boards/${payload}`);
      return thunkAPI.fulfillWithValue(payload);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

// createSlice
export const boardReducer = createSlice({
  name: "board",
  initialState,
  reducers: {},
  extraReducers: {
    // 메인페이지 출력(완료)
    [__getMainView.pending]: (_state) => {},
    [__getMainView.fulfilled]: (state, action) => {
      state.data = action.payload;
    },
    [__getMainView.rejected]: (_state, _action) => {},
    // 상세페이지 수정
    [__patchDetailView.pending]: (_state) => {},
    [__patchDetailView.fulfilled]: (state, action) => {
      state.data = state.data;
    },
    [__patchDetailView.rejected]: (_state, _action) => {},
    // 상세페이지 삭제
    [__deleteDetailView.pending]: (_state) => {},
    [__deleteDetailView.fulfilled]: (state, action) => {
      state.data = state.data.filter(
        (delData) => delData.id !== action.payload
      );
    },
    [__deleteDetailView.rejected]: (state, action) => {},
  },
});

export default boardReducer.reducer;
