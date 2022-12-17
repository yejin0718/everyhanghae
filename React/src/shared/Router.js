import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layout/Header";
import Layout from "./layout/Layout";
import Footer from "./layout/Footer";
import Login from "../pages/Login";
import Main from "../pages/Main";
import Write from "../pages/Write";

const Router = () => {
  return (
    <BrowserRouter>
      <Header />
      <Layout>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/" element={<Main />} />
          <Route path="/write" element={<Write />} />
        </Routes>
      </Layout>
      <Footer />
    </BrowserRouter>
  );
};

export default Router;
