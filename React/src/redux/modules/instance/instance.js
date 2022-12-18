import axios from "axios";

export const BACK_DB = process.env.REACT_APP_BACKAPI;

//CROS 통신 -> headers에 origin을 보내줘야함
export const instance = axios.create({
  baseURL: BACK_DB,
  headers: {
    "Access-Control-Allow-Origin": "*",
  },
});

//request header
// -> axios.interceptors ... //interceptors공부하기
instance.interceptors.request.use((config) => {
  if (config.headers === undefined) return;
  const token = localStorage.getItem("id");
  config.headers["Authorization"] = `${token}`;
  return config;
});
