import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { instance } from "./instance/instance";

//초기값
const initialState = {
  login: [],
  isLoading: false,
  error: null,
};

//__signup : 회원가입
export const __signup = createAsyncThunk(
  "__signup",
  async (payload, thunkAPI) => {
    console.log("__signup payload :", payload);
    try {
      const data = await instance.post(`users/signup`, payload);
      //console.log("data :", data.data);
      return thunkAPI.fulfillWithValue(data.data);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);
//__singin : 로그인
export const __signin = createAsyncThunk(
  "__signin",
  async (payload, thunkAPI) => {
    console.log("__signin payload :", payload);
    try {
      const data = await instance.post(`users/login`, payload);
      console.log("data", data);
      localStorage.setItem("id", data.headers.authorization);
      //localStorage.setItem("d", data.headers.authorization);
      return thunkAPI.fulfillWithValue(data.data);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

export const loginSlice = createSlice({
  name: "login",
  initialState,
  reducers: {},
  extraReducers: {
    //__singup : 회원가입 정보 보내기
    [__signup.fulfilled]: (state) => {
      state.isLoading = true;
    },
    [__signup.fulfilled]: (state, action) => {
      state.isLoading = false;
      state.login = [...state.login, action.payload];
      alert(action.payload.msg);
    },
    [__signup.rejected]: (state, action) => {
      state.isLoading = false;
      state.error = action.payload;
    },

    //__signin : 로그인 정보 보내기
    [__signin.fulfilled]: (state) => {
      state.isLoading = true;
    },
    [__signin.fulfilled]: (state, action) => {
      state.isLoading = false;
      state.login = [...state.login, action.payload];
      //localStorage.setItem("key") -> 키에 데이터 쓰기
      console.log("체크", action);
      // localStorage.setItem("id", action.payload.headers.authorization);
      alert(action.payload.msg);
    },
    [__signin.rejected]: (state, action) => {
      state.isLoading = false;
      state.error = action.payload;
    },
  },
});

export const {} = loginSlice.actions;
export default loginSlice.reducer;
