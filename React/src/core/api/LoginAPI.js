import { instance } from "./instance";

//__signup : 회원가입
export const sign_up = async (post) => {
  try {
    const data = await instance.post(`users/signup`, post);
    //console.log("data :", data.data);
    return data;
  } catch (error) {
    console.log(error);
  }
};
//__singin : 로그인
export const sign_in = async (post) => {
  try {
    const data = await instance.post(`users/login`, post);
    //console.log("data", data);
    return data;
  } catch (error) {
    console.log(error);
  }
};
