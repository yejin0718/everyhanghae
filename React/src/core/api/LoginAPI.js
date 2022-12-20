import { instance } from "./instance";

//duplicate_check : 중복처리
export const duplicate_check = async (post) => {
  try {
    const data = await instance.post(`users/duplicate`, post);
    console.log("data :", data);
    return data;
  } catch (error) {
    alert(error.response.data.msg);
  }
};
//signup : 회원가입
export const sign_up = async (post) => {
  try {
    const data = await instance.post(`users/signup`, post);
    console.log("data :", data.data);
    return data;
  } catch (error) {
    alert(error.response.data.msg);
  }
};
//singin : 로그인
export const sign_in = async (post) => {
  try {
    const data = await instance.post(`users/login`, post);
    return data;
  } catch (error) {
    alert(error.response.data.msg);
  }
};
