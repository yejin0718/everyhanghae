import React from "react";
// import { useNavigate } from "react-router-dom";
import { instance } from "../../../core/api/instance";

const Oauth2RedirectHandeler = () => {
  //   const navigate = useNavigate();

  let code = new URL(window.location.href).searchParams.get("code"); //searchParams 파라미터 값 확인
  console.log(code);

  const Kakao = async () => {
    await instance
      .post(`users/login/kakao`, code)
      .then((res) => {
        //console.log(res);
        //localStorage.setItem("id", res.headers.authorization);
        //navigate("/")
      })
      .catch((error) => {
        console.log(error);
      });
  };
  Kakao();

  return <div>test</div>;
};

export default Oauth2RedirectHandeler;
