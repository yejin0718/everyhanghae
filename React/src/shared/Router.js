import { BrowserRouter, Route, Routes } from "react-router-dom";
import Layout from "./layout/Layout";
import Login from "../pages/Login";
import Register from "../pages/Register";
import Main from "../pages/Main";
import Write from "../pages/Write";
import Detail from "../pages/Detail";
import Update from "../pages/Update";

const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
        </Route>
        <Route element={<Layout />}>
          <Route path="/" element={<Main />} />
          <Route path="/write" element={<Write />} />
          <Route path="/detail/:id" element={<Detail />} />
          <Route path="/detail/:id/update" element={<Update />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default Router;
