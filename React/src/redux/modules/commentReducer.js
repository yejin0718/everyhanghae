import { createAsyncThunk } from "@reduxjs/toolkit";
import { createSlice } from "@reduxjs/toolkit";
import { instance } from "../../core/api/instance";
import { current } from "@reduxjs/toolkit";

const initialState = {
  data: [],
};

// 좋아요 기능
export const __postLike = createAsyncThunk(
  "DetailView/postLike",
  async (payload, thunkAPI) => {
    try {
      const data = await instance.post(`boards/${payload}/like`);
      return thunkAPI.fulfillWithValue(data.data);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

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

// 댓글 수정
export const __putComment = createAsyncThunk(
  "DetailView/putComment",
  async (payload, thunkAPI) => {
    try {
      const data = await instance.put(
        `boards/${payload.boardId}/comments/${payload.commentId}`,
        {
          comment: payload.comment,
          username: payload.username,
        }
      );
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
    [__getDetailView.rejected]: (_state, action) => {
      alert(action.payload.response.data.msg);
    },
    // 댓글 삭제
    [__deleteComment.pending]: (_state) => {},
    [__deleteComment.fulfilled]: (state, action) => {
      state.data.commentList = state.data.commentList.filter(
        (del) => del.commentId !== action.payload[0]
      );
    },
    [__deleteComment.rejected]: (_state, action) => {
      alert(action.payload.response.data.msg);
    },
    // 댓글 작성
    [__postComment.pending]: (_state) => {},
    [__postComment.fulfilled]: (state, action) => {
      state.data.commentList = [...state.data.commentList, action.payload];
    },
    [__postComment.rejected]: (_state, action) => {
      alert(action.payload.response.data.msg);
    },
    // 댓글 수정
    [__putComment.pending]: (_state) => {},
    [__putComment.fulfilled]: (state, action) => {
      state.data.commentList = state.data.commentList.map((comment) => {
        if (comment.commentId === action.payload.commentId) {
          return { ...action.payload };
        } else {
          return comment;
        }
      });
    },
    [__putComment.rejected]: (_state, action) => {
      alert(action.payload.response.data.msg);
    },
    // 좋아요 기능
    [__postLike.pending]: (_state) => {},
    [__postLike.fulfilled]: (state, action) => {
      if (action.payload.msg === "게시글 좋아요를 성공했습니다.") {
        state.data.likeCount = state.data.likeCount + 1;
      } else {
        state.data.likeCount = state.data.likeCount - 1;
      }
    },
    [__postLike.rejected]: (_state, action) => {
      alert(action.payload.msg);
    },
  },
});

export default commentReducer.reducer;
