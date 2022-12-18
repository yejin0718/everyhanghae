import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { instance } from "./instance/instance";

//초기값
const initialState = {
  login: [],
  isLoading: false,
  error: null,
};

//__singin : 로그인

//__signup : 회원가입
export const __signup = createAsyncThunk(
  "__signup",
  async (payload, thunkAPI) => {
    console.log(payload);
    try {
      const data = await instance.post(`users/signup`, payload);
      //console.log("data :", data.data);
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
    //__singup : 로그인 정보 보내기
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
  },
});

export const {} = loginSlice.actions;
export default loginSlice.reducer;
