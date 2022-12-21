import { createAsyncThunk } from "@reduxjs/toolkit";
import { createSlice } from "@reduxjs/toolkit";
import { instance } from "../../core/api/instance";
import { current } from "@reduxjs/toolkit";

const initialState = {
  data: [],
};

// 상세페이지 조회
export const __getDetailView = createAsyncThunk(
  "DetailView/getBoard",
  async (payload, thunkAPI) => {
    try {
      const data = await instance.get(`boards/${payload}`);
      return thunkAPI.fulfillWithValue(data.data.data);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

// 댓글 삭제
export const __deleteComment = createAsyncThunk(
  "DetailView/deleteComment",
  async (payload, thunkAPI) => {
    try {
      await instance.delete(`boards/${payload[1]}/comments/${payload[0]}`);
      return thunkAPI.fulfillWithValue(payload);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

// 댓글 작성
export const __postComment = createAsyncThunk(
  "DetailView/postComment",
  async (payload, thunkAPI) => {
    try {
      const data = await instance.post(`boards/${payload.boardId}/comments`, {
        comment: payload.comment,
        username: payload.username,
      });
      return thunkAPI.fulfillWithValue(data.data.data);
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
    },
    [__getDetailView.rejected]: (_state, _action) => {
      alert("연결실패");
    },
    // 댓글 삭제
    [__deleteComment.pending]: (_state) => {},
    [__deleteComment.fulfilled]: (state, action) => {
      console.log(current(state.data.commentList));
      console.log(action.payload[0]);
      state.data.commentList = state.data.commentList.filter(
        (del) => del.commentId !== action.payload[0]
      );
    },
    [__deleteComment.rejected]: (_state, _action) => {
      alert("연결실패");
    },
    // 댓글 작성
    [__postComment.pending]: (_state) => {},
    [__postComment.fulfilled]: (state, action) => {
      state.data.commentList = [...state.data.commentList, action.payload];
    },
    [__postComment.rejected]: (_state, _action) => {
      alert("연결실패");
    },
  },
});

export default commentReducer.reducer;
