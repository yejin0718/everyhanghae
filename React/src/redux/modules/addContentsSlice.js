import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { instance } from "../../core/api/instance";

//초기값
const initialState = {
  contents: "",
  isLoading: false,
  error: null,
};

//createAsyncThunk를 통해서 thunk함수 생성
//__addContents : 게시글 추가
export const __addContents = createAsyncThunk(
  //첫번째 인자 : action value
  "__addContents",
  //두번째 인자 : 콜백함수
  async (payload, thunkAPI) => {
    try {
      const data = await instance.post(`boards`, payload);
      return thunkAPI.fulfillWithValue(data.data.msg); //fulfillWithValue : 네트워크 요청이 성공한 경우, dispatch함. 인자로 payload를 넘겨줄 수 있음
    } catch (error) {
      return thunkAPI.rejectWithValue(error); //rejectWithValue : 네트워크 요청이 실패한 경우, dispatch함. 인자로 payload를 넘겨줄 수 있음
    }
  }
);

//addContents
export const addContentsSlice = createSlice({
  name: "contents", //모듈이름
  initialState,
  reducers: {},
  extraReducers: {
    // __addComment : 댓글 추가
    [__addContents.pending]: (state) => {
      state.isLoading = true; // 네트워크 요청이 시작되면 로딩상태를 true로 변경합니다.
    },
    [__addContents.fulfilled]: (state, action) => {
      //console.log("msg", action.payload);
      state.isLoading = false; // 네트워크 요청이 끝났으니, false로 변경합니다.
      state.contents = { ...state, contents: action.payload }; // Store에 있는 contents에 서버에서 가져온 contents를 넣습니다.
      //alert(action.payload);
    },
    [__addContents.rejected]: (state, action) => {
      state.isLoading = false; // 에러가 발생했지만, 네트워크 요청이 끝났으니, false로 변경합니다.
      state.error = action.payload; // catch 된 error 객체를 state.error에 넣습니다.
    },
  },
});

export const {} = addContentsSlice.actions;
export default addContentsSlice.reducer;
